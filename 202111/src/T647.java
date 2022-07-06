/**
 *
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 *
 * 回文字符串 是正着读和倒过来读一样的字符串。
 *
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 *
 * 思路： 同T5。
 *
 * dp[i][j] 表示i到j的字串是否是回文。
 *
 *
 * @author rzet
 * @date 2021/12/10 19:25
 */
public class T647 {

    public int countSubstrings(String s) {

        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;

        for (int i = s.length() - 1; i>= 0; i--){
            dp[i][i] = true;
            count++;
            for (int j = i + 1; j < s.length(); j++){
                if (j == i + 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                if (dp[i][j])
                    count++;
            }
        }

        return count;
    }
}
