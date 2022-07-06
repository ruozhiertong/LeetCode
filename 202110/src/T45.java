import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 *
 * 思路：dp。
 *
 *
 * dp[i]  表示跳到i最少的跳跃数。
 *
 * @author rzet
 * @date 2021/10/28 15:26
 */
public class T45 {
    public int jump(int[] nums) {

        int dp[] = new int[nums.length];

        Arrays.fill(dp,Integer.MAX_VALUE); //表示不可达

        dp[0] = 0;

        for (int i = 0; i < nums.length - 1; i++){
            if (dp[i] != Integer.MAX_VALUE) {
                int nextIdx = nums[i] + i < nums.length - 1? nums[i] + i : nums.length - 1;
                for(int idx = i + 1; idx <= nextIdx; idx++){
                    dp[idx] = Math.min(dp[idx], dp[i] + 1);
                }
            }
        }

        return dp[nums.length - 1];



    }


}
