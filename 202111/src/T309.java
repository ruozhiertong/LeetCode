/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 *
 *
 *
 * 思路： 同714， 使用dp[][] 通用的动态规划。
 *
 * dp[i][0] dp[i][1] 表示第i天持有时，没有持有时的最大收益。
 *
 * dp[i][0] = max(dp[i-1][0], nums[i] -nums[i-1] + dp[i-1][1])
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] -  nums[i])  持有时的情况：1。由前一天持有。2。由前2天没持有的状态中买入。
 * 不能由前一天没有的状态中买入，是因为有可能前一天是刚卖出的，那么今天是冷冻期。所以不能由前一天状态得来。
 * 而从前两天状态得来，是因为，如果是前2天是卖出，那么前一天就冷冻了(即受益是和前2天是一样的，即dp[i-2][0] ==  dp[i-1][0] 所以直接从前2天来是没问题的)，那么今天是可以直接买入的。如果前2天是一直空状态，那么今天也可以买入的。
 *
 *
 *
 * 官方的题解，是分成了三个状态。 直接都是和前一天状态相关。 这个思路更加容易理解的。
 *
 *
 *
 * @author rzet
 * @date 2021/11/16 17:52
 */
public class T309 {

    public int maxProfit(int[] prices) {

        if (prices.length <= 1)
            return 0;
        if (prices.length == 2)
            return Math.max(0, prices[1] - prices[0]);

        int dp[][] = new int[prices.length][2];


        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0];
        dp[1][0] = Math.max(dp[0][0], prices[1] + dp[0][1]);;
        dp[1][1] = Math.max(0 - prices[0],0 - prices[1]);

        for (int i = 2; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i-1][0], prices[i] + dp[i-1][1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] -  prices[i]);
        }
        return dp[prices.length-1][0];
    }
}
