
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *
 *
 *  思路：广度优先搜索/层次遍历的变种。
 *
 *  深度，广度。 =》递归。  =》迭代：深度用栈，广度用队列。
 *
 * @author rzet
 * @date 2021/10/26 10:57
 */
public class T103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        List<TreeNode> curLevel = new ArrayList<TreeNode>();
        curLevel.add(root);
        int level = 0;

        while (curLevel.size() != 0){
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            List<Integer> l = new ArrayList<Integer>();
            for (int i = 0; i < curLevel.size(); i++){
                TreeNode node = curLevel.get(i);
                l.add(node.val);
            }
            result.add(l);
            //用双端队列，更方便。
            for (int i = curLevel.size() -1 ; i >= 0; i--){
                TreeNode node = curLevel.get(i);
                if (level % 2 == 0){
                    if (node.left != null)
                        nextLevel.add(node.left);
                    if (node.right != null)
                        nextLevel.add(node.right);
                }else{
                    if (node.right != null)
                        nextLevel.add(node.right);
                    if (node.left != null)
                        nextLevel.add(node.left);
                }
            }
            level++;
            curLevel = nextLevel;
        }
        return result;
    }

}
