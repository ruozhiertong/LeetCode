import sun.jvm.hotspot.oops.CompiledICHolder;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *大顶堆，小顶堆。
 *
 * 堆排序的实现.
 * 完全二叉树。
 *
 * 操作：create， add ， delete， siftdown， siftup。
 *
 * java中的堆，是优先队列Priority。
 *
 *
 * 使用数组。Java 的 Priority 也是用数组的。 会方便  高效的多。简单的多。
 * 使用链表。 因为使用链表的话，调整时会很麻烦。要处理各个指针，比较麻烦。 要处理很多细节。要维护root end， 交换， 指针等等。
 *
 * 这里自己的实现挑战自己，用链表去实现。
 *
 *
 *
 * =========================================
 *
 *
 * 对某个节点的调整：递归向下。 递归向上。
 *                如果是操作的节点和其子节点交换，就要递归向下操作其子节点。
 *                如果操作的节点是和其父亲做交换，那么就要递归向上操作父亲节点。
 *
 * 数组实现：
 *          create：
 *              方式1：边插入边调整(插入的都在最后节点，那么就要从最后节点做递归向上调整。如果插入在根结点，类似删除时的操作，在根结点做递归向下调整)。
 *              方式2：直接对整个数组调整(从最后一个非叶子节点开始向上遍历每一个节点。对每个节点都做递归向下调整)。
 *          删除： 将根结点和 最后节点交换，然后删除最后节点并调整根结点 对根结点做递归向下调整。
 *          添加： 就是create的方式1。可以插入在根，也可以插入在最后节点。
 *          调整： 对某个节点调整，因为调整后，会改变与该节点交换的节点，因此与需要递归处理被交换的节点。有递归向上调整，递归向下调整。
 *              （调整是针对某个节点的操作，只是会副作用影响其他节点，因此要递归操作影响的节点）
 *
 * 链表实现：
 *          和数组实现类似。 只是更麻烦些。
 *          在create时，用方式1更好。
 *          因为方式2麻烦，先构建一般的完全二叉树，然后找到非叶子节点，从这个节点开始循环往上进行调整。这个麻烦在于，要查找上一个节点(不是指父亲节点)。也能处理就是麻烦。数组处理简单是因为完全二叉树的索引下标有公式，可以直接计算。
 *
 *          //查找完全二叉树的上一个节点。
 *          Node findPre(Node n){
 *              Node parent = n.parent;
 *              if (parent == null){ //说明n是root节点了
 *                  return null;
 *              }
 *              if(n == parent.right){ //n是在右节点
 *                  return parent.left;
 *               }
 *               //n是左节点。 多种情况。
 *               if(parent == root)
 *                     return root;
 *
 *                Node grandParent = parent.parent;
 *
 *                if(parent == grandParent.right)
 *                     return grantParent.left.right;
 *
 *                //如果是在该层最左侧的节点，比较复杂，和层高有关系。
 *               int level = 0;
 *               Node tmp = n;
 *               while(tmp != null){
 *                  level++;
 *                  tmp = tmp.parent;
 *               }
 *               tmp = root;
 *               while(level != 2){
 *                   tmp = tmp.right;
 *                   level--;
 *               }
 *               return tmp;
 *          }
 *
 * @author rzet
 * @date 2021/11/30 15:10
 */
public class MyHeapSort {
    //使用二叉树来实现堆。
    private HeapNode root;
    private HeapNode end;
    private int count;// 加入count和level是为了删除时要更新end节点。
    private int level;//用于维护层数。 完全二叉树中，level和count的关系，level = [log(count+1)] 向上取整。
    //默认是小顶堆
    private boolean minHeap;

    //保存可以添加孩子的节点。 (按层次) 。 注意在调整的时候要处理队列中的节点。
    //双端队列。
    private Deque<HeapNode> addQueue; //队列是为了维护当前 所在操作的 节点。 即要在哪个节点加入孩子。


    public MyHeapSort(boolean minHeap){
        minHeap = minHeap;
        root = null;
        end = null;
        count = 0;
        level = 0;

        addQueue = new ArrayDeque<HeapNode>();
    }

    public void createHeap(int[] arr){
        for (int e: arr)
            add(new HeapNode(e));
    }

    public HeapNode peek(){
        return root;
    }

    //1。加入到root。 这个不方便，不好操作。
    //或2。加入到最后一个，最好保存parent指针。要siftUp
    public void add(HeapNode node){
        //更新count 和level。
        count++;
        if (Math.pow(2,level) - 1 < count)
            level++;


        if (root == null){
            root = node;
            end = root;
            addQueue.add(node);
            return;
        }


        HeapNode parent = addQueue.peek();

        node.parent = parent;

        if (parent.leftChild == null){
            parent.leftChild = node;
        }else if (parent.rightChild == null){
            parent.rightChild = node;
        }

        end = node;

        //调整。
        siftUp(node);


        //add队列要处理。
        addQueue.add(end);
        if (end.parent != parent){
            addQueue.remove();
            addQueue.addFirst(end.parent);
        }

        //调整之后，队列中的节点可能会变化
        parent = addQueue.peek();
        if (parent.leftChild != null && parent.rightChild != null){
            addQueue.remove();
        }

    }

    public void deleteRoot(){
        if (root == null)
            return;
        count--;
        if (Math.pow(2,level-1) - 1 == count)
            level = level -1;


        if (end == root){
            end = null;
            root = null;
            return;
        }


        //将最后的节点调到root。
        switchHeapNode(root, end);

        HeapNode tmp;
        //删除掉最后的节点。
        //并且更新end节点
        if (end.parent != null){
            if(end.parent.leftChild == end){
                end.parent.leftChild = null;
                //如果是这层最后一个，那么end要跳回上一层的最后一个。
                if (Math.pow(2,level) - 1 == count){
                    tmp =root;
                    for (int i = 2; i <= level; i++){
                        tmp = tmp.rightChild;
                    }
                    end = tmp;
                }else{
                    end = end.parent.parent.leftChild.rightChild;
                }
            }else if (end.parent.rightChild == end){
                end.parent.rightChild = null;
                end = end.parent.leftChild; // 堆是完全二叉树结构，因此删掉右孩子，那么做孩子就是end。
            }
        }

        //对根调整。
        siftDown(root);


    }

    private void siftDown(HeapNode node){
        if (node == null || (node.leftChild == null && node.rightChild == null))
            return;

        //取三者最小/大。
        HeapNode left = node.leftChild;
        HeapNode right = node.rightChild;
        HeapNode tmp = node;
        if (left != null && (minHeap? left.val < tmp.val:left.val > tmp.val)){
            tmp = left;
        }
        if (right != null && (minHeap? right.val < tmp.val:right.val > tmp.val)){
            tmp = right;
        }
        if (tmp != node){
            switchHeapNode(node, tmp);
            siftDown(node);
        }

    }

    //注意节点之间各个指向的改变。
    //每个节点都有最有孩子，父亲指针，因此相关节点也要处理他们的指向。
    //注意如果两个节点 是父子关系，要特别处理。
    //两种情况，1。父子关系的两节点。 2。非父子关系的两节点。
    private void switchHeapNode(HeapNode node1, HeapNode node2){

        if (node1.parent == node2 || node2.parent == node1){ //case1
            HeapNode child, parent;
            if (node1.parent == node2)
                child = node1;
            else
                child = node2;
            parent = child.parent;

            //处理child节点
            HeapNode childLeftChild = child.leftChild;
            HeapNode childRightChild = child.rightChild;

            child.parent = parent.parent;
            if (parent.parent != null){
                if (parent.parent.leftChild == parent)
                    parent.parent.leftChild = child;
                else if (parent.parent.rightChild == parent)
                    parent.parent.rightChild = child;
            }
            if (parent.leftChild == child){
                child.leftChild = parent;
                child.rightChild = parent.rightChild;
                if (parent.rightChild != null)
                    parent.rightChild.parent = child;
            }else if (parent.rightChild == child){
                child.rightChild = parent;
                child.leftChild = parent.leftChild;
                if (parent.leftChild != null)
                    parent.leftChild.parent = child;
            }

            //处理parent
            parent.parent = child;
            parent.leftChild = childLeftChild;
            if (childLeftChild != null)
                childLeftChild.parent = parent;
            parent.rightChild = childRightChild;
            if (childRightChild != null)
                childRightChild.parent = parent;

        } else { //case2
            HeapNode node1LeftChild = node1.leftChild;
            HeapNode node1RightChild = node1.rightChild;
            HeapNode node1Parent = node1.parent;

            HeapNode node2LeftChild = node2.leftChild;
            HeapNode node2RightChild = node2.rightChild;
            HeapNode node2Parent = node2.parent;

            //处理node1
            node1.parent = node2Parent;
            if (node2Parent != null){
                if (node2Parent.leftChild == node2)
                    node2Parent.leftChild = node1;
                else if (node2Parent.rightChild == node2)
                    node2Parent.rightChild = node1;
            }
            node1.leftChild = node2LeftChild;
            if (node2LeftChild != null)
                node2LeftChild.parent = node1;
            node1.rightChild = node2RightChild;
            if (node2RightChild != null)
                node2RightChild.parent = node1;

            //处理node2
            node2.parent = node1Parent;
            if (node1Parent != null){
                if (node1Parent.leftChild == node1)
                    node1Parent.leftChild = node2;
                else if (node1Parent.rightChild == node1)
                    node1Parent.rightChild = node2;
            }
            node2.leftChild = node1LeftChild;
            if (node1LeftChild != null)
                node1LeftChild.parent = node2;
            node2.rightChild = node1RightChild;
            if (node1RightChild != null)
                node1RightChild.parent = node2;

        }

        //交换后，要注意更新root和end。
        if (root == node1)
            root = node2;
        else if (root == node2)
            root = node1;

        if (end == node1)
            end = node2;
        else if (end == node2)
            end = node1;
    }

    private void siftUp(HeapNode node){
        if (node == null || node.parent == null){
            return;
        }

        HeapNode parent = node.parent;
        if (minHeap?node.val < parent.val:node.val > parent.val) { //进行调整
            switchHeapNode(node, parent);
            siftUp(node);
        }
    }


    //按层次打印
    public void printHeap(){
        if (root == null)
            return;

        Queue<HeapNode> queue = new LinkedList<HeapNode>();
        queue.add(root);

        while (queue.size() != 0){
            HeapNode node = queue.poll();
            System.out.print(node.val + " ");
            if(node.leftChild != null)
                queue.add(node.leftChild);
            if (node.rightChild != null)
                queue.add(node.rightChild);
        }
        System.out.println();

    }


    public static void main(String[] args) {
        MyHeapSort mhs = new MyHeapSort(false);
        mhs.createHeap(new int[]{9,3,6,7,5,8,1,0,4,2});
        mhs.printHeap(); // 0 1 3 4 2 8 6 9 5 7

        //mhs.add(new HeapNode(-1));

        mhs.deleteRoot();
        mhs.printHeap();//1 2 3 4 7 8 6 9 5

        mhs.deleteRoot();
        mhs.printHeap();//2 4 3 5 7 8 6 9


    }
}

class HeapNode{
    int val;
    HeapNode leftChild;
    HeapNode rightChild;
    HeapNode parent;

    public HeapNode(int val){
        this.val = val;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

}
