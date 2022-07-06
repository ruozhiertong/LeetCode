import sun.misc.FpUtils;

/**
 * 740. 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 *
 *
 * 思路：完全没有思路。 暴力破解好像也不太行。 贪心？
 *
 *
 * 没办法，看官方。 转换为打家劫舍。 真没想到这样的想法。 不过提示以下，就只知道怎么做了。 主动删除的表示偷，被动删除的表示不偷。
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/28 16:15
 */
public class T740 {
    public int deleteAndEarn(int[] nums) {
        //重建数组，以值为下标。

        int max = nums[0];
        for (int val : nums){
            if (max < val)
                max = val;
        }

        //新数组中的值为可获得的点数。
        int nums2[] = new int[max + 1];

        for (int val : nums){
            nums2[val] += val;
        }

        if (max + 1 == 1) //长度只有1 . 0
            return nums2[0];

        if (max + 1 == 2) // 0, 1
            return nums2[1];


        int dp[] = new int[max + 1];

        dp[0] = 0;
        dp[1] = nums2[1];

        for (int i = 2; i < max + 1; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums2[i]);
        }


        return dp[dp.length-1]; //要全部偷完。


    }
}
