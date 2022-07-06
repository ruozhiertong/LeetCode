import java.security.cert.LDAPCertStoreParameters;
import java.util.Arrays;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 思路： //1。动态规划。 dp[i] 表示以第i个为结尾的最长的字符个数。
 *
 *        //dp[i][j]
 *
 *       1。动态规划。
 *       要想清楚dp的含义。 dp[i][j] 表示 i到j的子串是否是回文, true, false。
 *
 *       然后 确定状态转移方程。
 *       dp[i][j] = dp[i+1][j-1] && (arr[i] == arr[j])
 *
 *       确定初始化。
 *
 *       循环遍历 构造整个dp。
 *
 *
 *
 *
 *
 *
 *      2。 暴力。len[i] 表示以第i个为结尾的最长的字符个数。
 *
 *      优化，根据第i个字符作为中心扩展开来，求第i个字符作为中心的最大长度。 只要中心两端相同就继续扩展。
 *
 * @author rzet
 * @date 2021/10/21 15:20
 */
public class T5 {


    public boolean isReverseStr(String s){
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    //暴力。 时间超限。
    public String longestPalindrome(String s) {
        int len[] = new int[s.length()];
        Arrays.fill(len, 1); //默认都有本身。
        len[0] =1;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++){
                if (isReverseStr(s.substring(j, i + 1))) {
                    len[i] = i - j + 1;
                    break;
                }
            }
        }
        int maxIdx = 0;
        for (int j = 1; j < len.length; j++){
            if (len[j] > len[maxIdx]){
                maxIdx = j;
            }
        }

        return s.substring(maxIdx + 1 - len[maxIdx],maxIdx + 1);

    }

    //动态规划。o(n+(n-1)+...1)=o(n^2)
    public String longestPalindrome2(String s) {

        //dp[i][j] 表示 i到j的子串是否是回文, true, false。
        boolean dp[][] = new boolean[s.length()][s.length()];

        dp[0][0] = true;
        //dp[i][j] = dp[i+1][j-1] && (arr[i] == arr[j])

        for (int j = 0; j < dp.length; j++){
            for (int i = j; i >= 0; i--){
                if(j == i)
                    dp[i][j] = true;
                else if (i == j -1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
            }
        }

        //寻找最长的回文。 当然也可以在上面构造过程中找到。
        int maxLen = 0;
        int start = 0, end = 0;
        for (int j = s.length() - 1; j >= 0; j--) {
            int i = 0;
            for (i = 0; i <= j; i++) {
                if (dp[i][j])
                    break;
            }
            if (maxLen < j - i + 1){
                maxLen = j -i +1;
                start = i;
                end = j;
            }
        }

        return s.substring(start, end + 1);



    }



}
