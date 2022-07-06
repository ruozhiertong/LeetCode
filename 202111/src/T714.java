/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 *
 *
 *
 * 思路： 1。动归。 dp[i] 表示到i天为止，最大收益。
 *  *            dp[i] = nums[i] - nums[i -1] >0 ? nums[i] - nums[i -1] +  dp[i -1]: dp[i -1]
 *
 *              如果 前一天没有股票，
 *              dp[i] = nums[i] - nums[i -1] - fee >0 ? nums[i] - nums[i -1] - fee +  dp[i -1]: dp[i -1]
 *
 *              如果前一天有股票
 *              dp[i] = nums[i] - nums[i -1] - fee >0 ? nums[i] - nums[i -1] - fee +  dp[i -1]: dp[i -1]
 *
 *
 *
 *                  因为频繁交易 会导致手续费过高。 因此 dp[i]表示的最大收益 并不仅仅是和i-1的状态做比较。
 *  *
 *  *               而122之所以可以这么做，因为没有手续费的限制，因此dp[i]可以表示 第i天为止的收益 也是第i天卖出时的收益。
 *  *
 *  *               实际上，第i天的最大收益的 总的状态，要根据第i天是否持有股票。持有股票 和 没有持有股票。
 *  *
 *  *               dp[i][0] = max(dp[i-1][0], nums[i] -nums[i-1] + dp[i-1][1])
 *  *               dp[i][1] = max(dp[i-1][1], dp[i-1][0] -  nums[i])
 *
 *                  设计成dp[][]的状态转移方程 可以通用的解决各种股票问题。 清楚了今天的状态是与上一个状态相关就行。
 *                  注意到在状态转移方程中，dp[i][0]dp[i][0] 和 dp[i][1]dp[i][1] 只会从 dp[i-1][0]dp[i−1][0] 和 dp[i-1][1]dp[i−1][1] 转移而来，
 *  *
 *
 * @author rzet
 * @date 2021/11/2 17:53
 */
public class T714 {

//    public int maxProfit(int[] prices, int fee) {
//        int dp[] = new int[prices.length];
//
//        dp[0] = 0;
//
//        int max = 0;
//        for (int i = 1; i < prices.length; i++){
//            int profit = prices[i] - prices[i-1] - fee;
//
//            dp[i] = profit > 0? dp[i-1] + profit: dp[i -1];
//
//            if (max < dp[i])
//                max = dp[i];
//        }
//        return max;
//    }

    public int maxProfit(int[] prices, int fee) {
        int dp[][] = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0];

        for (int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i-1][0], prices[i] - fee + dp[i-1][1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] -  prices[i]);
        }
        return dp[prices.length-1][0];
    }

}
