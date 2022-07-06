import sun.management.counter.AbstractCounter;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 *
 *
 *
 * 思路：直接用T322就好。
 * 呵呵，天真了。还是不太一样的。 搞死了。
 *
 * dp[i] 表示 凑成金额i的组合数。 -1表示无法凑成。
 *
 *
 *
 * 0。暴力。空间树形式。要利用剪枝 和 记忆化。 注意 这里是求组合数，因此这个暴力求解出来的是有重复的，要进行处理。 失败了。
 *
 * 1。一样的问题。求组合数，要去除重复的。
 *
 *
 *
 * 关键在于两层for循环 遍历顺序的不同。遍历顺序不同，导致结果是 排列数还是组合数。
 *
 * 其实只要搞清楚dp的含义，自然就弄清楚遍历顺序问题。
 *
 * 组合数： dp[i]的含义，表示可以凑成金额i的组合数。 先遍历物品，再遍背包容量。 先遍历物品，表示以第i个物品为结尾时的所有情况（这里已经考虑确定了物品的顺序，因此是一个组合数，即只会出现 1，2，而不会出现2，1这样的组合）。
 *
 * 排列数： dp[i] 表示可以凑成金额i的种类数。 先遍历背包容量，再遍历物品。 先遍历背包容量，表示以第i容量的背包所有情况。
 *
 *
 * 如果变成二维dp，那么就简单了，不需要考虑内外循环顺序问题。不过复杂度就高了。dp[i][j] 前i个硬币数组合成j金额的组合数。枚举所有第i个硬币所能有的数量。
 *
 *
 * 背包问题：https://leetcode-cn.com/problems/coin-change-2/solution/gong-shui-san-xie-xiang-jie-wan-quan-bei-6hxv/
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/12/6 19:27
 */
public class T518 {

    //fail.
    public int change(int amount, int[] coins) {
        long dp[] = new long[amount + 1];
        Arrays.fill(dp,-1); //MAX 表示无法凑成。
        dp[0] = 0;

        for (int i = 0; i <= amount; i++){
            for (int j = 0; j < coins.length; j++){
                if (dp[i] != -1 && (long)(i + (long)coins[j]) <= amount) { //当前这个金额要可达 && 总金额要小于amount
                    dp[i + coins[j]]++;
                }
            }
        }

        return (int) (dp[amount] + 1);
    }



    long memo[];
    //暴力。 使用DFS，递归方式。 也会重复的问题。 还是排列数。 fail.
    public int change2(int amount, int[] coins) {

        if (amount == 0)
            return 1;
        memo = new long[amount+1];
        Arrays.fill(memo , -1);

        long count = dfs(amount,coins);

        return (int) count;

//        return (int) (count == 1? count: count/2); 排列数和组合数不是简单的／2。

    }

    public long dfs(int amount, int []coins){
        if (amount <= 0)
            return 0;

        if (memo[amount] != -1)
            return (int)memo[amount];

        long count = 0;
        for (int i = 0; i < coins.length; i++){
            if (coins[i] == amount){
                count++;
            } else if(coins[i] < amount){ //剪枝
              count += dfs(amount - coins[i], coins);
            }
        }
        memo[amount] = count; //记忆化。
        return count;
    }

    public static void main(String[] args) {
        new T518().change2(500, new int[]{1,2,5});
    }


    public int change3(int amount, int coins[]){
        int []dp = new int[amount + 1];

        dp[0] = 1;


        for (int i = 0; i < coins.length; i++){ //i表示，以第i个硬币为结尾时的所有情况。
            for (int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }


}
