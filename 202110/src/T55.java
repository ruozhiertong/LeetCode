import java.util.Arrays;

/**
 *
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 *
 *
 * 思路： dp。 找到对应状态转移方程。 dp的含义。
 *
 * dp[i] 表示到能否到达i位置， true， false。
 *
 * dp[i] = dp[i - nums[i]].
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/28 15:07
 */
public class T55 {

    public boolean canJump(int[] nums) {

        boolean dp[] = new boolean[nums.length]; //初始化 默认都是false。

        dp[0] = true;

        for (int i = 0; i < nums.length - 1; i++){
            if (dp[i]){
                int nextIdx = nums[i] + i < nums.length - 1? nums[i] + i : nums.length - 1;
                Arrays.fill(dp, i , nextIdx + 1, true);
            }
        }

        return dp[nums.length - 1];


    }

    public static void main(String[] args) {
        new T55().canJump(new int[]{2,3,1,1,4});
    }
}
