/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 *
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。
 *
 *
 *
 * 思路：1。直接全部乘积，然后除以自身。 o(n)
 *      2。 不允许用除法，那么双重循环，跳过自身。 o(n^2)
 *      3。感谢提示中的前缀 后缀 給的提醒。 然后使用利用类似动归中的状态转移方程，来解决问题。
 *      dp[i] i的前缀积。 dp[i] = dp[i-1] * a[i - 1];
 *      dp2[i] 以i结尾的后缀积。
 *      两次循环。
 *      O(n)
 *
 *      4。前后双指针。 O(n^2)
 *
 *
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/20 16:35
 */
public class T238 {
    public int[] productExceptSelf(int[] nums) {
        int pre[] = new int[nums.length];
        int post[] = new int[nums.length];
        pre[0] = 1;
        post[post.length - 1] = 1;

        for (int i = 1; i < nums.length; i++){
            pre[i] = nums[i - 1] * pre[i-1];
            post[nums.length - 1 - i] = nums[nums.length - 1 - i + 1] * post[nums.length - 1 - i + 1];
        }
        int outputp[] = new int[nums.length];
        for (int i = 0; i < outputp.length; i++) {
            outputp[i] = pre[i] * post[i];
        }

        return outputp;

    }
}

