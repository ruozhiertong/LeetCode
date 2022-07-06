/**
 *
 *
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 *
 *
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/javayi-ge-si-lu-da-bao-suo-you-gu-piao-t-pd1p/
 * 0.首先，你要觉得这个能用动态规划解决。 （一般的解法／暴力 都是超时的，因此可以考虑动态规划。）
 * 1.dp的定义是什么？有几个状态（用几维数组表示）？
 * 2.初始值如何确定？
 * 3.状态方程又是什么？（状态转移方程的确定）
 *以上基本是把动态规划的问题的套路都給讲清楚了。
 *
 *
 *          1。要想清楚dp的含义。 状态的表示（几维数组的表示）。
 *  *       2。然后 确定状态转移方程。
 *  *       dp[i][j] = dp[i+1][j-1] && (arr[i] == arr[j])
 *  *       3。确定初始化。
 *  *       4。循环遍历 构造整个dp。
 *
 *
 *
 * 受上面题解启发，要清楚dp的含义，定义。
 *
 *
 * 其实和官方的题解是一样的。这里只是将官方的buy和sell合并成0,1数组。
 *
 *
 * @author rzet
 * @date 2021/11/17 18:57
 */
public class T188 {
    public int maxProfit(int k, int[] prices) {

        if(prices.length == 0)
            return 0;

        int dp[][][] = new int[prices.length][k + 1][2]; //dp[i][k][0]表示第i天未持有股票，交易了k次， 的最大收益。(第i天时k次卖出时最大收益，不一定是第i天时第k次卖出)
        // dp[i][k][1]表示第i天持持有股票，交易了k次， 的最大收益。(第i天时k次买入时最大收益，不一定是第i天时第k次买入)

        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        for (int i = 1; i <= k; i++){
            dp[0][i][1] = dp[0][i -1][0] - prices[0]; //要先买后卖。
            dp[0][i][0] = dp[0][i][1] + prices[0];
        }

        for (int i = 1; i < prices.length; i++){
            for (int j = 1; j <= k ; j++){ // 0次买入和卖出，不用考虑。 全为0。
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j][1] + prices[i]);
            }
        }
        return dp[prices.length-1][k][0];
    }
}
