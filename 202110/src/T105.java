import java.text.FieldPosition;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * [3,9,6,8,20,15,7]
 * [6,9,8,3,15,20,7]
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/25 22:38
 */
public class T105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 1)
            return new TreeNode(preorder[0]);

        int mid = preorder[0];
        int idx = 0;

        TreeNode node = new TreeNode(mid);

        for (int i =0; i < inorder.length; i++){
            if (inorder[i] == mid){
                idx = i;
                break;
            }
        }

        if (idx == 0) {
            node.left = null;
        }else{
            int [] inorderLeft = new int[idx];
            System.arraycopy(inorder,0,inorderLeft, 0, idx);
            int [] preorderLeft = new int[idx];
            System.arraycopy(preorder,1,preorderLeft, 0, idx);
            node.left = buildTree(inorderLeft, preorderLeft);

        }

        if (idx == inorder.length - 1) {
            node.right = null;
        }else{
            int [] inorderRight = new int[inorder.length - 1 -idx];
            System.arraycopy(inorder,idx + 1,inorderRight, 0, inorder.length - 1 -idx);
            int [] preoderRight = new int[inorder.length - 1 -idx];
            System.arraycopy(preorder,1 + idx,preoderRight, 0, inorder.length - 1 -idx);

            node.right = buildTree(inorderRight, preoderRight);
        }


        return node;

    }
}

class TreeNode {
    int val;
    TreeNode left; //对于引用类型的变量 未赋值 都是null。
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class BinaryTree{

    TreeNode root;

    public  BinaryTree(){
        root = null;
    }



    // 1 2 0 0
    //前 中 后序进行创建。 遇到0表示null
    public void init(int[] arr){
        TreeNode ptr = root;
        for (int e: arr){
            TreeNode node = new TreeNode(e);
            ptr = node;
            ptr.left =

        }
    }


    public void insertNode(TreeNode node){

    }


}