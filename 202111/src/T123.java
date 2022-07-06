/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * 思路： 想不出来。
 *
 * 跪了。 关键时要找到多种状态之间的关系。
 *
 *
 *
 * 这个题解很妙啊。
 *  public int maxProfit(int[] prices) {
 *         对于任意一天考虑四个变量:
 *         fstBuy: 在该天第一次买入股票可获得的最大收益
 *         fstSell: 在该天第一次卖出股票可获得的最大收益
 *         secBuy: 在该天第二次买入股票可获得的最大收益
 *         secSell: 在该天第二次卖出股票可获得的最大收益
 *         分别对四个变量进行相应的更新, 最后secSell就是最大
 *         收益值(secSell >= fstSell)
 *
 *int fstBuy=Integer.MIN_VALUE,fstSell=0;
         *int secBuy=Integer.MIN_VALUE,secSell=0;
         *for(int p:prices){
         *fstBuy=Math.max(fstBuy,-p);
         *fstSell=Math.max(fstSell,fstBuy+p);
         *secBuy=Math.max(secBuy,fstSell-p);
         *secSell=Math.max(secSell,secBuy+p);
         *}
         *return secSell;
         *}


 官方的题解。


 *
 *
 *
 * @author rzet
 * @date 2021/11/17 12:43
 */
public class T123 {

    public int maxProfit(int[] prices) {
        int first[] = new int[prices.length]; //第i天为止，第一次卖出时的最大值。
        int sec[] = new int[prices.length]; //第i天为止，第二次卖出时的最大值。

        first[0] = 0;
        sec[0] = 0;

        for (int i = 1; i < prices.length; i++){
            int profit = prices[i] - prices[i-1];
            first[i] = profit > 0? first[i-1] + profit: first[i -1];

            sec[i] = profit > 0? first[i -1] + profit: sec[i - 1];


        }
        return sec[prices.length - 1];
    }


    //妙！
    public int maxProfit2(int[] prices) {
        /**
         对于任意一天考虑四个变量:
         fstBuy: 在该天第一次买入股票可获得的最大收益
         fstSell: 在该天第一次卖出股票可获得的最大收益
         secBuy: 在该天第二次买入股票可获得的最大收益
         secSell: 在该天第二次卖出股票可获得的最大收益
         分别对四个变量进行相应的更新, 最后secSell就是最大
         收益值(secSell >= fstSell)
         **/
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p);
        }
        return secSell;
    }
}
