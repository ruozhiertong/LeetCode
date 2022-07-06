import sun.misc.FpUtils;

/**
 *
 * 1567. 乘积为正数的最长子数组长度
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 *
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 *
 * 请你返回乘积为正数的最长子数组长度。
 *
 *
 *
 *
 * 思路： 1。暴力。 dp[i][j]
 *       2。动态规划。 posDp[i] 表示以i为结尾,乘积值为正的最长数组长度。  negDp表示以i为结尾,值为负的最长数组长度
 *
 *
 * @author rzet
 * @date 2021/11/2 14:11
 */
public class T1567 {

    public int getMaxLen(int[] nums) {
            int posDp[] = new int[nums.length];
            int negDp[] = new int[nums.length];

            if (nums[0] > 0){
                posDp[0] = 1;
                negDp[0] = 0;
            }else if (nums[0] < 0){
                posDp[0] = 0;
                negDp[0] = 1;
            }else {
                posDp[0] = 0;
                negDp[0] = 0;
            }


            for (int i = 1; i < nums.length; i++){
                if (nums[i] > 0){
                    posDp[i] = posDp[i -1] + 1;
                    if (negDp[i -1] != 0)
                        negDp[i] = negDp[i -1] + 1;
                } else if (nums[i] < 0){
                    if (negDp[i - 1] != 0)
                        posDp[i] = negDp[i - 1] + 1;
                    negDp[i] = posDp[i - 1] + 1;
                } else { // = 0
                    posDp[i] = 0;
                    negDp[i] = 0;
                }
            }

            int maxL = posDp[0];
            for (int e : posDp){
                if (maxL < e)
                    maxL = e;
            }

            return maxL;

    }
}
