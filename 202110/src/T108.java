/**
 *
 * 108. 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 *
 *
 *
 * 思路：
 * 基础不牢固，导致没想到思路。
 *
 * 其实就是二叉树的基础问题，
 * 1。根据二叉树，给出前/中／后序 遍历的结果。递归或迭代(迭代借助栈)。  层次遍历(广度，借助队列)。 (深度搜索实际上是前序。)
 * 2。根据某种遍历的结果重建二叉树(当然构建二叉树不是唯一)。 用递归。 找到根节点，然后递归左右。
 *
 *
 *
 * BST的遍历。前序遍历，结果是升序。 后序遍历是降序。
 * BST的构建。 1。给定全部的序列构建(可以用方法2直接构建。也可以排序后进行利用前序构建，因为BST的前序是排序的)。 2。一个一个的构建（就是模拟构建）。
 *
 * BST 可以是平衡的，也可以是不平衡的。
 *
 *
 *
 * TODO： 如何一步一步构建BST（就是从根开始判断加入到哪边，递归进行。 不过这个可能会导致不平衡）。
 *        如何一步一步构建平衡树。（构建的平衡树不一定是排序树）。 重点在于如何进行调整平衡。
 *        以及如何构建平衡的BST(其实就是构建BST时加入平衡调整)。
 *
 *
 * 所以这道题，就是如何从有序的序列中构建BST。 而且是平衡的。
 *
 * 更进一步，如何将二叉搜索树变平衡。 先变为有序序列，再构建平衡 BST。
 *
 * 构建平衡BST，从中点选取为根节点。
 *
 * @author rzet
 * @date 2021/10/26 12:23
 */
public class T108 {
    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedArrayToBST(nums, 0, nums.length -1);

    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2; //中点。如果是两个中点 是左边的那个。
        TreeNode rootNode = new TreeNode(nums[mid]);

        rootNode.left = sortedArrayToBST(nums, start, mid - 1);
        rootNode.right = sortedArrayToBST(nums, mid + 1, end);
        return rootNode;
    }
}
