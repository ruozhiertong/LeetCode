import java.util.Stack;

/**
 * 1249. 移除无效的括号
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *
 *
 *
 *
 * 思路： 利用栈。
 *
 * @author rzet
 * @date 2021/10/22 21:43
 */
public class T1249 {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> opStack = new Stack<Integer>(); //存储(的位置索引
        int rmIdx[] = new int[s.length()]; //需要删除的索引位置。
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                opStack.push(i);
            }else if(s.charAt(i) == ')'){
                if (opStack.size() == 0) //无法抵消
                    rmIdx[count++] = i;
                else
                    opStack.pop();
            }
        }
        for (int i = 0; i < opStack.size(); i++){
            rmIdx[count++] = opStack.get(i);
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < count; i++) {
            sb.setCharAt(rmIdx[i], ' ');
        }
        return sb.toString().replaceAll(" ","");

    }
}
