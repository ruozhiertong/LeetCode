/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 思路： 1。动归。 dp[i] 表示到i天为止，最大收益。 （第i天为止肯定是没有股票的。）
 *               dp[i] = nums[i] - nums[i -1] >0 ? nums[i] - nums[i -1] +  dp[i -1]: dp[i -1]
 *
 *               类似求上升子序列和。
 *
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/11/2 15:11
 */
public class T122 {

    public int maxProfit(int[] prices) {
        int dp[] = new int[prices.length];

        dp[0] = 0;

        int max = 0;
        for (int i = 1; i < prices.length; i++){
            int profit = prices[i] - prices[i-1];

            dp[i] = profit > 0? dp[i-1] + profit: dp[i -1];

            if (max < dp[i])
                max = dp[i];
        }
        return max;
    }

}
