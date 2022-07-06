import java.time.temporal.Temporal;
import java.util.zip.CheckedOutputStream;

/**
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 *
 *
 * 思路： 1。暴力破解。
 *
 *
 * sum[i][j] i 到 j的和。 sum[j] - sum[i]。 [i,j] 闭区间。
 *
 *所以。 sum[idx] 表示idx结尾的sum。  sum[idx] = sum[idx -1] + a[idx]; O(n)
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/20 17:10
 */
public class T560 {

    //暴力
    public int subarraySum(int[] nums, int k) {

        int result = 0;
        int count[] = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int sum = 0; //以i为结尾的和。
            for(int start = i; start >= 0; start--) {
                sum += nums[start];
                if (sum == k)
                    result++;
//                int sum = 0; //优化，没必要进行三次循环。
//                for (int idx = start; idx <= i; idx++){
//                    sum += nums[idx];
//                }
//                if (sum == k)
//                    result++;
            }
        }

        return result;
    }


    //优化。 o(n^2)。 前缀和。（dp的方式）
    public int subarraySum2(int[] nums, int k) {

        int sum[] = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            sum[i] = sum[i-1] + nums[i];
        }

        int count = 0;
//        //len表示 连续子区间的 长度／元素个数。
//        for (int len = 1; len <= nums.length; len++ ){
//            for (int i = 0; i < nums.length - len; i++){
//
//            }
//        }
        //还可以优化成o(n). 使用map。
        //以i为结尾的和。
        int tmpSum = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i; j >=0; j--){
                tmpSum = sum[i] - sum[j] + nums[j];
                if (tmpSum == k)
                    count++;
            }
        }

        return count;

    }
}
