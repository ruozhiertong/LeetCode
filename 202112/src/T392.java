/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *
 *
 * 思路：1。直接暴力。s[0]在t中的idx， 然后接着找s[1]在t[idx:]后的idx。
 *      O(n*m)
 *
 *      2.官方双指针。 好妙。
 *
 *      3。DP 想不到。 状态机。
 *
 *
 *
 * 判断子串／字串搜索／字符串匹配。 1。暴力。2。kmp。
 *
 *
 * @author rzet
 * @date 2021/12/10 19:49
 */
public class T392 {

    //1.
    public boolean isSubsequence(String s, String t) {

        for (int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            int idx = t.indexOf(c);
            if (idx == -1)
                return false;
            t = t.substring(idx + 1);
        }

        return true;

    }
}
