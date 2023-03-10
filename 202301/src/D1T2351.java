/**
 *
 *
 * 2351. 第一个出现两次的字母
 * 给你一个由小写英文字母组成的字符串 s ，请你找出并返回第一个出现 两次 的字母。
 *
 * 注意：
 *
 * 如果 a 的 第二次 出现比 b 的 第二次 出现在字符串中的位置更靠前，则认为字母 a 在字母 b 之前出现两次。
 * s 包含至少一个出现两次的字母。
 *
 *
 * 示例 1：
 *
 * 输入：s = "abccbaacz"
 * 输出："c"
 * 解释：
 * 字母 'a' 在下标 0 、5 和 6 处出现。
 * 字母 'b' 在下标 1 和 4 处出现。
 * 字母 'c' 在下标 2 、3 和 7 处出现。
 * 字母 'z' 在下标 8 处出现。
 * 字母 'c' 是第一个出现两次的字母，因为在所有字母中，'c' 第二次出现的下标是最小的。
 *
 *
 *
 * 思路： 1。哈希表。 2。计数。状态集。（可以利用位运算进行优化）
 *
 *
 * @author rzet
 * @date 2023/1/2 00:08
 */
public class D1T2351 {

    public char repeatedCharacter(String s) {
        int[] charnum = new int[26];

        for (int i = 0 ; i < s.length(); i++){
            charnum[s.charAt(i)-'a']++;

            if (charnum[s.charAt(i) - 'a'] == 2)
                return s.charAt(i);
        }
        return '0';
    }

    public char repeatedCharacter0(String s) {
        int seen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int x = ch - 'a';
            if ((seen & (1 << x)) != 0) {
                return ch;
            }
            seen |= (1 << x);
        }
        // impossible
        return ' ';
    }
}
