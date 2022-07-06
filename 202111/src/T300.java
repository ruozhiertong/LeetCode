import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 思路： 这题和T516类似，都是子序列。 不过更简单些。思路更加清晰。
 *
 * dp[i] 表示以i为结尾时 最大的增长子序列长度。
 *      O(n^2)
 *
 * @author rzet
 * @date 2021/12/3 18:22
 */
public class T300 {


    //O(n^2)
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];

        Arrays.fill(dp,1); //默认本身就是一个增长子序列。

        int max = dp[0];
        for (int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if (nums[i] > nums[j])
                    dp[i] =Math.max(dp[i], dp[j] + 1);
            }
            if (dp[i] > max)
                max =dp[i];
        }


        return max;


    }
}
