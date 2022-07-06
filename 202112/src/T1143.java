import sun.misc.FpUtils;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 *
 *
 * 对于最长公共字串， result[i]的值  表示s1中以第i为开头的是s2中子串的最长长度。 o() . 实际上暴力。
 *
 *
 *
 *
 * 思路：
 * 想不到dp和转移方程。 我是的个憨憨。 和最长回文子序列 T516 类似。
 * 清楚了dp的表示后，转移方程就会简单些。
 * dp[i][j] 表示 text1 i位置， text2 j位置 最长的公共子序列长度。 有三个方向推出dp[i][j]
 * https://leetcode-cn.com/problems/longest-common-subsequence/solution/dai-ma-sui-xiang-lu-dai-ni-xue-tou-dpzi-zqa9q/
 *
 *
 *
 * @author rzet
 * @date 2021/12/11 22:40
 */
public class T1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int [][]dp = new int[m+1][n+1]; //dp[0][j]->0, dp[i][0]->0

        for (int i = 1; i < m+1; i++){
            char c1 = text1.charAt(i -1);
            for (int j = 1; j < n + 1; j++){
                char c2 = text2.charAt(j-1);
                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }
}

