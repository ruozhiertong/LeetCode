import java.util.Stack;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *
 *
 * 思路：
 * 注意点就是要根据字符串decode出唯一的二叉树。 因此在二叉树中应该有相应的分界符。使用null进行界定。
 *
 * 其实使用层次遍历最简单吧。 如果是层次遍历的话，从字符串codec成二叉树比较方便。
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/26 14:53
 */
public class T297 {

    //前序遍历最好。 这样根据的字符串进行构建最方便。
    public void preTrace(TreeNode node, StringBuilder sb){
        if (node == null){
            sb.append("null"); //表null
            return;
        }
        sb.append(node.val + " ");
        preTrace(node.left, sb);
        preTrace(node.right, sb);

    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preTrace(root, sb);
        return sb.toString().trim();
    }

    // Decodes your encoded data to tree.
    //递归更方便。
    // 迭代。 模拟。
    public TreeNode deserialize(String data) {
        String [] arr = data.split(" "); //空哥分割。
        if (arr.length == 0)
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        TreeNode pNode = root;
        Stack<TreeNode> st = new Stack<TreeNode>(); //存放已经放好左子节点的节点。
        for (int i = 1; i < arr.length; i++){
            if (arr[i].equals("null")){

            }else{
                pNode.left = new TreeNode(Integer.parseInt(arr[i]));
                st.push(pNode);
                pNode = pNode.left;
            }

        }
    return null;
    }
}
