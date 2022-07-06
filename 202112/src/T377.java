/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围
 *
 *
 *
 * 组合数和排列数。
 *
 * 思路：这和背包问题一样。 和硬币的组合数一样，T518 求组合数。
 *
 * dp[i] 表示以某个个硬币为结尾时组合成金额i的的组合数。
 *
 *
 * 妈的，后面还有一句话 请注意，顺序不同的序列被视作不同的组合。
 * 所以这个是求排列数。 dp[i]表示金额为i的所有可能。
 *
 *
 *
 * @author rzet
 * @date 2021/12/15 21:11
 */
public class T377 {
    public int combinationSum4(int[] nums, int target) {

        int []dp = new int[target+1];

        dp[0] = 1;

        for (int i = 0; i < nums.length; i++){
            for (int j = nums[i]; j <= target; j++){
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[target];
    }


    public int combinationSum42(int[] nums, int target) {

        int []dp = new int[target+1];

        dp[0] = 1;
        for (int i = 1; i <= target; i++){
            for (int j = 0; j < nums.length; j++){
                if (i >= nums[j])
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
