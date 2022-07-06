import java.util.Arrays;

/**
 *
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 *
 *
 *
 *
 * 思路： 动态规划。 dp[i] 表示以i为结尾的序列的最大元素个数。 dp[i] = a[i] > a[i-1] ? dp[i-1] + 1:dp[i];
 * 只要有dp[i]大于3就说明存在。
 * o(n^2)
 *
 *
 * 贪心算法。 O(n)
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/c-xian-xing-shi-jian-fu-za-du-xiang-xi-jie-xi-da-b/
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/java-tan-xin-by-feilue-usn7/
 * 有点巧妙。 没想到。
 *
 *
 *
 * @author rzet
 * @date 2021/10/20 15:33
 */
public class T334 {

    public boolean increasingTriplet(int[] nums) {

        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1);

        dp[0] = 1;

        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]) { //递增
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] >= 3)
                        return true;
                }
            }
        }

        return false;

    }

}
