/**
 *
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 * 注意这道题全程只能买卖各一次。 求最大差值。
 *
 *
 * 思路：
 *      1。我记得以前我做过这道题，思路是用贪心，以理论差值为贪心。 题 T122。
 *
 *      2。动归。dp[i] 表示卖出时最大的获益。 dp[i] = dp[i-1] + nums[i] - nums[j];
 *
 *
 *      3。暴力。
 *
 *      4。记录滚动的当前为止的历史最低值。求当前的值与最低值的差值。
 *
 *
 *
 *
 *
 * 对于力扣平台上的股票类型的题目：
 *
 * 121. 买卖股票的最佳时机
 *
 * 122. 买卖股票的最佳时机 II
 *
 * 123. 买卖股票的最佳时机 III
 *
 * 188. 买卖股票的最佳时机 IV
 *
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 714. 买卖股票的最佳时机含手续费
 *
 * 剑指 Offer 63. 股票的最大利润
 *
 * @author rzet
 * @date 2021/11/2 14:42
 */
public class T121 {

    //暴力。 时间超限。
    public int maxProfit(int[] prices) {

        int max = 0 ; //以i买入时能赚的最多钱。

        for (int i = 0; i < prices.length; i++){
            for (int j = i + 1; j < prices.length; j++){
                int tmp = prices[j] - prices[i]; //i买入， j卖出，
                max = max > tmp? max: tmp;
            }
        }
        return max;
    }

    //dp。
    public int maxProfit2(int[] prices) {

        int dp[] = new int[prices.length]; //dp[i] 表示以第i天卖出时的最大收益。
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++){
            int tmp = dp[i -1] + prices[i] - prices[i -1]; //因为只有一次卖出机会。所以相当于将前一天卖出的再买入，然后再卖出今天的。
            if (tmp < 0)
                dp[i] = 0;
            else
                dp[i] = tmp;

            if (max < dp[i])
                max = dp[i];

        }
        return max;
    }
}
