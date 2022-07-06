import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1382. 将二叉搜索树变平衡
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 *
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 *
 * 如果有多种构造方法，请你返回任意一种。
 *
 * 思路：先转成有序的数组，然后根据有序的数组构建平衡的BST。
 *
 * @author rzet
 * @date 2021/10/26 14:23
 */
public class T1382 {


    List<Integer> arr = new ArrayList<Integer>();


    //中序遍历
    public void midTraceTree(TreeNode node){
        if (node == null)
            return;
        midTraceTree(node.left);
        arr.add(node.val);
        midTraceTree(node.right);


    }
    public TreeNode balanceBST(TreeNode root) {
        midTraceTree(root);

        //https://blog.csdn.net/huanghanqian/article/details/73920439

        Integer[] nums = arr.toArray(new Integer[0]);

        return sortedArrayToBST(nums, 0, nums.length -1);


    }

    public TreeNode sortedArrayToBST(Integer[] nums, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2; //中点。如果是两个中点 是左边的那个。
        TreeNode rootNode = new TreeNode(nums[mid]);

        rootNode.left = sortedArrayToBST(nums, start, mid - 1);
        rootNode.right = sortedArrayToBST(nums, mid + 1, end);
        return rootNode;
    }
}
