/**
 * 918. 环形子数组的最大和
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 *
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
 *
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 *
 *
 *
 * 应该是连续子数组。
 *
 * 最大连续子数组和的变种。
 *
 *
 * 思路： 1。破环。 然后转成最大子数组和。 好像不行，破环没应以。
 *       2。
 *       分情况：1。这个连续子数组 起始点 < 结束点. 2. 起始点> 结束点。(区间经过0)
 *
 *
 *      3。 动归的dp想不出来。 那直接暴力， dp[i] 以i为起始点的最大子和。 O(n^2). 时间超限。
 *
 *
 *
 *
 *      方法2 分情况是正确的思路。 感谢leecode群友的提示。
 *      因为这整个数组的sum一定。 而且求的最大子数组是连续的。 那么将整个数组分为两部分。一部分取max，相应的另一部分应该就是min。
 *
 *      如果最大值是在0，n区间， 起始点 < 结束点， 那么直接dp。
 *      如果最大值在 起始点> 结束点。(区间经过0)， 相应的最小值就是在0，n区间内，所以求得最小值，那么最大值就 sum -min.
 *
 *      两个dp数组。一个放最大，一个放最小。
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/29 16:35
 */
public class T918 {



    public int maxSubarraySumCircular2(int[] nums) {

        int dpMax[] = new int[nums.length]; //表示以i为结尾的最大值。
        int dpMin[] = new int[nums.length]; //表示以i为结尾的最小值。
        int sum = 0;
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        sum += nums[0];
        int max = dpMax[0];
        int min = dpMin[0];

        for (int i = 1; i < nums.length; i++){

            sum +=  nums[i];
            dpMax[i] = Math.max(nums[i], nums[i] + dpMax[i -1]);
            dpMin[i] = Math.min(nums[i], nums[i] + dpMin[i - 1]);
            max = Math.max(max, dpMax[i]);
            min = Math.min(min, dpMin[i]);
        }

        if (min == sum) //如果最小值是全部的内容，那么只要考虑max。
            return max;
        else
            return  Math.max(max, sum - min);

    }


    public int maxSubarraySumCircular(int[] nums) {

        int resultMax = Integer.MIN_VALUE;
        int dp[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            int max = nums[i];
            int sum = nums[i];
            for (int j = i + 1; j % nums.length != i ; j++){
                sum += nums[j % nums.length];
                if (sum > max)
                    max = sum;
            }
            dp[i] = max;
            if (resultMax < dp[i])
                resultMax = dp[i];
        }

        return  resultMax;

    }
}
