/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 *
 * 思路： dp。
 * 首先要分析可以使用dp。可以分解成子问题。
 * 其次要明白dp的含义。
 * 利用dp含义，分析转移方程。 尽量要使用上上一个dp的状态（即要充分利用子问题已经的结果来求解）。
 *
 * dp[i]的值表示i被拆分(至少两个)后乘积的最大值。
 *
 * @author rzet
 * @date 2021/12/16 14:34
 */
public class T343 {

    //O(n^2)
    public int integerBreak(int n) {
        int [] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n ; i++){
            for (int j = 1; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(dp[j], j) * (i-j)); //
            }
        }
        return dp[n];
    }
}
