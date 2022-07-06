import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * B树：多路平衡查找树。 2-3树也是一种B树。 3阶B树。 节点中最多的孩子的个数，为B树的阶
 *
 *
 * 操作： 增删改查
 *      建立。
 *      添加。
 *      删除。
 *      遍历。
 *      查找。
 *
 * @author rzet
 * @date 2022/7/5 14:00
 */
public class BTree {

    private BTreeNode root = null;
    private int rank;//阶数

    public BTree(int []arr, int rank){
        this.rank = rank;
        root = new BTreeNode(rank -1);
        for (int val : arr){
            addNode(val);
        }
    }

    private List<Integer> midTrace(BTreeNode node){
        if (node == null)
            return null;

        int valNum = node.getNum();
        List<Integer> nodeVals = node.getVals();
        List<BTreeNode> nodeChilds = node.getChilds();

        List<Integer> result = new ArrayList<Integer>();
        int i;
        for(i = 0; i< valNum; i++){
            List<Integer> tmp = midTrace(nodeChilds.get(i));
            if (tmp != null)
                result.addAll(tmp);
            result.add(nodeVals.get(i));
        }
        List<Integer> tmp = midTrace(nodeChilds.get(i));
        if(tmp != null)
            result.addAll(tmp);
        return result;
    }
    public List<Integer> midTrace(){
        return midTrace(root);
    }
    public void addNode(int val){
        add2LeafNode(val);
    }

    private void splitNode(BTreeNode node){
        if (node == null || !node.isOverNode())
            return;

        BTreeNode parent = node.getParent();
        if (parent == null){
            parent = new BTreeNode(rank - 1);
            root = parent;
        }

        //分两个node。
        List<BTreeNode> nodeChilds = node.getChilds();
        List<Integer> nodeVals = node.getVals();
        int nodeNum = node.getNum();
        BTreeNode newNode = new BTreeNode(rank - 1);
        int midIdx = nodeNum / 2 ;
        int midVal = nodeVals.get(midIdx);

        int[] newNodeVals = new int[nodeNum - midIdx - 1];
        BTreeNode[] newNodeChilds = new BTreeNode[nodeNum - midIdx];

        int i;
        int idx = 0;
        for (i = midIdx + 1; i < nodeNum; i++){
            newNodeVals[idx] = nodeVals.get(i);
            newNodeChilds[idx] = nodeChilds.get(i);
            idx++;
        }
        newNodeChilds[idx] = nodeChilds.get(i);
        newNode.setNode(newNodeVals, newNodeChilds, newNodeVals.length);

        node.setNum(midIdx);
        node.setParent(parent);
        newNode.setParent(parent);

        //将mid的值 往parent中加。 并且将parent的child指针指向两个parent。
        i = insertValToNode(parent, midVal, node, newNode);
        splitNode(parent);
    }


    //如果BTreeNode 自适应或者想让BTreeNode数据保持内部操作，应该将操作放在BTreeNode。
    //如果BTreeNode只是单纯数据存储，那么操作可以放在高层。
    private int insertValToNode(BTreeNode node, int val, BTreeNode child1, BTreeNode child2){
        int i;
        List<Integer> nodeVals = node.getVals();
        List<BTreeNode> nodeChilds = node.getChilds();
        int valNum = node.getNum();
        for(i = valNum - 1; i >= 0; i--){
            if (nodeVals.get(i) < val)
                break;
        }
        nodeVals.add(i + 1, val);
        if (nodeChilds.size() == i+1)
            nodeChilds.add(i + 1, child1);
        else
            nodeChilds.set(i+1, child1);
        nodeChilds.add(i + 2, child2);
        node.setNum(valNum+1);
        return i+1;
    }
    //往该叶子node添加。
    private void add2LeafNode(int val){
        //找到要插入的node。该node是叶子node。 即找到要插入的叶子节点。
        BTreeNode tmp = root;
        int i;
        while(!tmp.isLeafNode()) {
            List<BTreeNode> tmpChilds = tmp.getChilds();
            List<Integer> tmpVals = tmp.getVals();
            int valNum = tmp.getNum();
            for (i = valNum - 1; i >= 0; i--) {
                if (tmpVals.get(i) < val)
                    break;
            }
            tmp = tmpChilds.get(i + 1);
        }

        //这里tmp是叶子节点
        insertValToNode(tmp, val, null, null);
        splitNode(tmp);
    }


    public void deletNode(int val){

    }

    private boolean midSearch(BTreeNode node, int val){
        if (node == null || node.getNum() == 0)
            return false;

        List<Integer> nodeVals = node.getVals();
        List<BTreeNode> nodeChilds = node.getChilds();
        int valNum = node.getNum();
        int childIdx = 0;
        for (int i = 0; i < valNum; i++){
            if (nodeVals.get(i) == val)
                return true;
            if (nodeVals.get(i) < val)
                childIdx = i + 1;
            else if (nodeVals.get(i) > val)
                break;
        }
        return midSearch(nodeChilds.get(childIdx), val);

    }


    public boolean hasNode(int val){
        if (root == null)
            return false;
        else
            return midSearch(root, val);
    }

    public static void main(String[] args) {

        BTree bt = new BTree(new int[]{10,5,2,14,7,11,6,9,15,3,8,4,12,13,16} , 5); //
        List<Integer> l = bt.midTrace();
        System.out.println(l);


        System.out.println(bt.hasNode(1));
        System.out.println(bt.hasNode(15));

        bt.addNode(10);
        bt.addNode(14);
        bt.addNode(12);

        l = bt.midTrace();
        System.out.println(l);

        bt.deletNode(8);
        bt.deletNode(14);
        bt.deletNode(5);
        bt.deletNode(4);

        l = bt.midTrace();
        System.out.println(l);




    }



//要实现一个算法，数据结构也很重要。 组织数据结构直接的关系，想想为什么这么设计，为什么将操作放在这个数据结构中等等。

    //BTreeNode 简单的数据结构。 复杂的操作应该给更高层次的BTree操作,涉及node下的孩子操作，应该交由高层BTree来做。或者说
//BTreeNode只是简单的数据载体。
//BTreeNode 通用的结构，而非特殊化。
    private class BTreeNode{
        private int maxNum;
        private int num; //节点的值的个数。 孩子的个数=节点值的个数 + 1； 阶数=最大的孩子个个数。
        private List<Integer> vals; //  val是按顺序排列好的。 用链表更好。数组其实有点麻烦，增删要移动。用链表的话增删方便，也不需要处理索引。
        private List<BTreeNode> childs;
        //改用List替换int[],能避免我们自己手动移动。
        //List<Integer> vals;
        //List<BTreeNode> childs = new ArrayList<>();

        //或使用链表。

        private BTreeNode parent;


        public BTreeNode(int maxNum){
            this.maxNum = maxNum;
            this.num = 0;
            this.vals = new ArrayList<Integer>();
            this.childs = new ArrayList<BTreeNode>();
            this.parent = null;
        }

        public boolean isOverNode(){
            return this.num > this.maxNum;
        }
        public boolean isLeafNode(){
            return this.childs.size() == 0 || this.childs.get(0) == null;
        }
        public boolean isRootNode(){
            return this.parent == null;
        }
        public List<Integer> getVals(){
            return vals;
        }
        public List<BTreeNode> getChilds(){
            return this.childs;
        }
        public int getNum(){
            return this.num;
        }
        public void setNum(int num){
            this.num = num;
        }
        public BTreeNode getParent(){
            return this.parent;
        }
        public void setParent(BTreeNode parent){
            this.parent = parent;
        }
        public boolean setNode(int[] vals, BTreeNode[] childs, int len){
            if (len > vals.length || len + 1 > childs.length)
                return false;
            int i;
            for (i = 0; i < len; i++){
                this.vals.add(vals[i]);
                this.childs.add(childs[i]);
            }
            this.childs.add(childs[i]);
            this.num = len;
            return true;
        }
    }

}


class Test{


    public static void main(String[] args) {
        List<Integer> l = new ArrayList<Integer>();
        l.add(1);
        l.add(2);

        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(3);
        l2.add(0);

        l.addAll(l2);

        int[] a = new int[]{9,8,7};

//        l.addAll(a);

//        l.addAll(null);
        System.out.println(l);



        int []arr1 = new int[10];
        int []arr2 = new int[]{1,2,4};
        System.out.println(arr1.length+" "+arr2.length);

        arr1 = arr2;
        System.out.println(arr1.length+" "+arr2.length);


        ArrayList<Integer> sa = new ArrayList<>();
        LinkedList<Integer> ll = new LinkedList<Integer>();

//        System.out.println(sa.get(0));

    }
}