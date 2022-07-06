import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 思路： 同最大连续子数组和。
 * 靠，这个还相当的麻烦。处理方式不同。 考虑元素的正负，是最大连续子数组和 的变种。
 *
 * dp。
 *
 *
 *
 * 思路： 1。dp。 维护一个最大dp，维护一个最小dp。 遇到nums[i]是负数，那么最大 最小就要交换。
 *       2。累计乘积。 ／ 类似滑动窗口。
 *       3。负数的个数。 ／最优解的区间分布情况。
 *
 *       其实还是dp应该算是最好想的。只是要绕个弯弯。
 *
 * @author rzet
 * @date 2021/11/2 00:41
 */
public class T152 {

    public int maxProduct(int[] nums) {

        int dp[] = new int[nums.length]; //表示以第i为结尾时的最大乘积。最大乘积是绝对值的最大。
        //这样的动归有问题， 当前位置的最优解未必是由前一个位置的最优解转移得到的。状态转移方程是错误的，要另外考虑。
        //考虑正负的思路是正确的。

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++){
            dp[i] = nums[i] * dp[i - 1] ;
        }


        //因为有正有负，需要特殊处理
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i > 0; i--){
//            if (dp[i] == 0){
//                dp[i] = Math.max(nums[i],dp[i-1]);
//            }else{
//                if (dp[i] < 0 && nums[i] > 0) // dp[i], nums[i]: ++, +-, -+, --. dp为负时
//                    dp[i] = nums[i];
//                else if (dp[i] < 0 && nums[i] < 0)
//                    dp[i] = dp[i -1];
//            }

            //dp[i] = Math.max(dp[i],Math.max(nums[i], dp[i-1]));

            if (dp[i] <= 0) {
                if (nums[i] >= 0)
                    dp[i] = nums[i];
                else
                    dp[i] = Math.max(dp[i], nums[i]);
            }

            if (max < dp[i])
                max = dp[i];
        }


        return max;



    }


    //暴力。 时间超限。 还是要想其他办法。
    public int maxProduct2(int[] nums) {
        int dp[][] = new int[nums.length][nums.length]; //dp[i][j] 表示从i到j的乘积。 就是暴力。

        int max = nums[0];
        for (int i = 0; i < nums.length; i++){
            dp[i][i] = nums[i];
            if (max < dp[i][i])
                max = dp[i][i];
            for (int j = i + 1; j < nums.length; j++){
                dp[i][j] = dp[i][j -1] * nums[j];

                if (max < dp[i][j])
                    max = dp[i][j];
            }
        }
        return max;
    }

    // dp[i][j] = dp[0][j] / dp[0][i] ,所以要dp[i][j] max 那么就需要 保证dp[0][j]最大，dp[0][i]最小。


    //思路：或者我们考虑最优解的区间分布情况。 如果负数为偶数，那么就是全部乘积。 如果负数为奇数，那么区间肯定是排除某个负数的，从左到右，从右到左。







    //dp
    public int maxProduct3(int[] nums) {
        int maxDp[] = new int[nums.length]; // 以i为结尾的最大乘积。
        int minDp[] = new int[nums.length];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        int max = maxDp[0];

        for (int i = 1; i < nums.length; i++){
            if (nums[i] > 0){
                maxDp[i] = Math.max(nums[i], maxDp[i -1] * nums[i]);
                minDp[i] = Math.min(nums[i], minDp[i -1] * nums[i]);
            }else{
                maxDp[i] = Math.max(nums[i], minDp[i -1] * nums[i]);
                minDp[i] = Math.min(nums[i], maxDp[i-1] * nums[i]);
            }
            if (max < maxDp[i])
                max = maxDp[i];

        }
        return max;
    }
}
