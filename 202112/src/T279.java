import java.util.Arrays;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 思路： dp。
 * dp[i]  表示组成i的平方数的最少数量。
 *
 *
 * @author rzet
 * @date 2021/12/16 15:28
 */
public class T279 {
    //o(n^2)
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++){
            if (isPerfectSquare(i)){
                dp[i] = 1;
                continue;
            }
            for (int j = 1; j <= i/2; j++){
                dp[i] = Math.min(dp[i], dp[j] + dp[i-j]);
            }
        }

        return dp[n];

    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }
}
