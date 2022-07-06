/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 *
 * 和T5 不同。 T5是子串，这个是子序列。
 *
 *
 * 做不出来。 fail。
 * 其实思路也是dp[i][j], 只是状态转移这步没有分析出来。 我是个憨憨。
 *
 * 看了官方解题。
 * dp[i][j] 表示i到j最大的的回文子序列。（含义 不是以i打头，j结尾，而是i到j区间内。）
 * dp[i][j] 状态由两个组成，
 * 如果s[i] == s[j] 那么 dp[i][j] = d[i+1][j-1] + 2
 * 如果s[i] != s[j] 那么 dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
 *
 *
 *
 * **思路：** dp[i][j]表示s[i..j]代表的字符串的最长回文子序列
 *
 * d[i][i]=1
 * dp[i][j] = dp[i+1][j-1]+2 当s[i]=s[j]
 * dp[i][j]=max(dp[i+1][j],dp[i][j-1]) 当s[i]!=s[j] 取s[i+1..j] 和s[i..j-1]中最长的 由于dp[i][j]需要dp[i+1][j]所以需要逆序枚举s的长度，而又因为j是递增的，所以在求解dp[i][j]时,dp[i][j-1]肯定已经求解过了
 *
 *
 *
 *
 * @author rzet
 * @date 2021/12/3 14:11
 */
public class T516 {
    public int longestPalindromeSubseq(String s) {
        int dp[][] = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >=0; i--){
            dp[i][i] = 1;
            char start = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++){
                char end = s.charAt(j);
                if (start == end){
                    dp[i][j] = dp[i + 1][j - 1] + 2; // dp[1][0] => 0.  if i > j, dp[i][j] =>0.
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][s.length() - 1];

    }
}
