import java.text.FieldPosition;

/**
 *
 * 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 *
 *
 *
 *
 * 思路： 动态规划。 dp[i]表示第i个为止（以第i个为结尾），等差数列子数组的个数。
 *
 *
 *
 * @author rzet
 * @date 2021/11/21 22:54
 */
public class T413 {
    public int numberOfArithmeticSlices(int[] nums) {

        if (nums.length <= 2)
            return 0;
        int dp[] = new int[nums.length];
        dp[0] = 0;
        dp[1] = 0;
        int sum = 0;
        for (int i = 2; i < nums.length; i++){
            if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2])
                dp[i] = dp[i-1] + 1;
            else
                dp[i] = 0;
            sum += dp[i];
        }


        return sum;
    }

}
