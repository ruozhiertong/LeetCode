/**
 *
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 思路：动态规划。 subSum[i] 表示以第i个结尾的数的最大子序和。 //subSum[i+1] = arr[i+1]>0?subSum[i]+arr[i+1]:subSum[i]
 *             状态转移方程弄错了，应该是 dp[i+1] = max(nums[i+1], dp[i] + nums[i+1])
 *
 *
 * 其他思路： 暴力破解。 贪心算法。 分治算法。 分，治：https://www.jianshu.com/p/c4d68d0376ad
 *
 * @author rzet
 * @date 2021/10/9 09:08
 */
public class T53 {


    //wrong
    public int maxSubArray(int[] nums) {
        int subSum[] = new int[nums.length];
        int subIdx[] = new int[nums.length];
        subSum[0] = nums[0];
        subIdx[0] = 0;


        for (int i = 0; i< nums.length - 1; i++){
            int tmp = 0;
            for (int j = subIdx[i]; j <= i; j++){
                tmp += subSum[j];
            }
            if(tmp > 0) {
                subSum[i + 1] = subSum[i] + tmp;
                subIdx[i + 1] = i + 1;
            }else {
                subSum[i+1] = subSum[i];
                subIdx[i+1] = subIdx[i];
            }

        }

        int max = subSum[0];
        for (int e : subSum) {
            if (max < e)
                max = e;
        }
        return max;
    }


    public int maxSubArray2(int[] nums) {
        //dp[i] 表示以i结尾的最大子序和。
        int dp[] = new int[nums.length];
        dp[0] = nums[0];

        for(int i = 0; i< nums.length - 1; i++){
           dp[i+1] =  nums[i+1] > dp[i] + nums[i+1]?nums[i+1]: nums[i+1] + dp[i];
        }
        int max = dp[0];

        for (int e: dp){
            if(e> max)
                max = e;
        }
        return max;
    }

    //dp的递归版本。
    //以r为结尾的最大子序和。
    int maxR[];
    public int maxSubArray3(int nums[],int r){
        if(r == 0)
            return nums[r];
        maxR[r - 1] = maxSubArray3(nums, r-1);
        return nums[r] > nums[r] + maxR[r - 1]? nums[r]:nums[r] + maxR[r - 1];
    }



    //贪心算法。 非常巧妙的思路，算是贪心的一种。
    public int maxSubArray4(int nums[]){
        int sum = nums[0];
        int max = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(sum < 0) //如果前面的和小于0，那么肯定不要，还不如重新开始。
                sum = nums[i];
            else
                sum += nums[i];
            if(max < sum)
                max = sum;
        }
        return  max;
    }




    //使用分治方法处理。 关键点在于分治 每个子问题的理解和解释。
    //l 到 r 最大的子段和。
    //分治。m = (l+r)/2. l 到 r 最大的子段和，要么在m左边，要么在右边，要么跨越m。
    //每个区间，lSum表示以左边l为端点的最大字段和。 rSum以右边r为端点最大字段和。iSum整个区间的和。
    //所以，区间的最大子序和为： m左子区间的最大的子序和。m右子区间的最大子序和。跨越m的最大子序和。 跨越m的最大子序和是左子区间的rSum+右边的lSum。
    //而一个区间的lsum和rsum怎么计算？ lsum： 要么是左子区间的lsum，要么是左子区间的isum+右子区间的lsum，两者取大。 或者 直接遍历开始计算以l为端点的最大子序和。
    //官方题解是的分治是很巧妙。
    public int getMax(int []nums, int l, int r){
        if (l == r)
            return nums[l];

        int m = (l + r) / 2;
        int lMax = getMax(nums,l, m);
        int rMax = getMax(nums, m + 1, r);

        //计算跨越中间的最大子序和， 从m开始端点，向左 右遍历。
        int mLsum = nums[m], mRsum = nums[m+1]; //mLsum 以m为端点，左边的最大子序和。
        int tmp = 0;
        //计算以m为起点的，最大子序和。
        for(int i = m; i >=l; i--){
            tmp += nums[i];
            if(mLsum < tmp)
                mLsum = tmp;
        }
        tmp = 0;
        for (int i = m + 1; i <= r; i++ ){
            tmp += nums[i];
            if(mRsum < tmp)
                mRsum = tmp;
        }

        int mMax = mLsum + mRsum;

        return  Math.max(Math.max(lMax,rMax), mMax);
    }
}
