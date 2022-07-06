import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 思路： 和背包问题类似。 贪心，不过一定正确啊。
 *
 *  0。 暴力。 模拟。   如果转成空间树，利用记忆化，以及剪枝， 也可以降低复杂度。
 *
 *  1。 动态规划。 dp[i] 表示金额为i时的最少个数。 如果为MAX_VALUE 那么表示无法凑成这个金额。
 *  （关键将这个dp想到了，后面就很好做了）
 *
 *  O(n*m)
 *
 * @author rzet
 * @date 2021/12/6 12:33
 */
public class T322 {

    public int coinChange(int[] coins, int amount) {

        long dp[] = new long[amount + 1];
        Arrays.fill(dp,Long.MAX_VALUE); //MAX 表示无法凑成。
        dp[0] = 0;

        for (int i = 0; i <= amount; i++){//遍历背包容量
            for (int j = 0; j < coins.length; j++){//遍历物品
                if (dp[i] != Long.MAX_VALUE && (long)(i + (long)coins[j]) <= amount) //当前这个金额要可达 && 总金额要小于amount
                    dp[i + coins[j]] = Math.min(dp[i+coins[j]],dp[i] + 1);
            }
        }

        return dp[amount] == Long.MAX_VALUE ? -1: (int) dp[amount];

    }

    public static void main(String[] args) {
        new T322().coinChange(new int[]{1,2147483647}, 2);
    }
}
