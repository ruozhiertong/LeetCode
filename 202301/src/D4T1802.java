/**
 * 1802. 有界数组中指定下标处的最大值
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 *
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 *提示：
 *
 * 1 <= n <= maxSum <= 10^9
 * 0 <= index < n
 *
 *
 *
 *
 *
 * 思路： 只要能找到一个数组就行。 因此我们为了保证能找到这么一个数组，和最小，小于maxsum就行。
 *
 *注意数组的特点，前后相差<=1。
 *
 *
 * 关键点：1.数组的特点。前后相差<=1。  2.使用贪心分析，分析index处最大值， 形成的是一个index处最大值的山峰数列， 该山峰数列前后为等差数列。
 * 3.计算等山峰左右差数列的求和(注意峰底处的连续1的计算)。
 *
 *
 * @author rzet
 * @date 2023/1/4 18:02
 */
public class D4T1802 {
    //直接暴力输出。 超时。 o(maxsum*n)
    public int maxValue(int n, int index, int maxSum) {

        for (int i = maxSum; i > 0; i--){
            int indexValue = i;
            long sum = 0;
            sum += indexValue; // value in index pos

            for (int j = index - 1 ; j >= 0; j--){
                indexValue--;
                if (indexValue < 1)
                    sum += 1;
                else
                    sum += indexValue;
            }
            indexValue = i;
            for (int j = index + 1; j < n; j++){
                indexValue--;
                if (indexValue < 1)
                    sum += 1;
                else
                    sum += indexValue;
            }
            if (sum <= maxSum) return i;
        }
        return -1;
    }



    //优化。使用二分。 o(lg(maxsum)*n)。 超时。
    public int maxValue0(int n, int index, int maxSum) {

        int l = 1;
        int r = maxSum;
        while(l <= r){
            int mid = (l+r) /2;
            int indexValue = mid;
            long sum = 0;
            sum += indexValue; // value in index pos

            for (int j = index - 1 ; j >= 0; j--){
                indexValue--;
                if (indexValue < 1)
                    sum += 1;
                else
                    sum += indexValue;
            }
            indexValue = mid;
            for (int j = index + 1; j < n; j++){
                indexValue--;
                if (indexValue < 1)
                    sum += 1;
                else
                    sum += indexValue;
            }
            if (sum <= maxSum)
                l = mid + 1;
            else
                r = mid -1;
        }
        return l -1;
    }

    //再优化。使用二分 + 直接求和(求和公式推导)。
    public int maxValue1(int n, int index, int maxSum) {
        int l = 1;
        int r = maxSum;
        while(l <= r){
            int mid = (l+r) /2;
            int indexValue = mid;
            long sum = 0;
            long lsum = helper(indexValue, index);
            long rsum = helper(indexValue, n - 1 - index);
            sum = lsum + indexValue + rsum;
            if (sum <= maxSum)
                l = mid + 1;
            else
                r = mid -1;
        }
        return l -1;
    }

    /*求山峰一侧的元素的和，max 为山峰大小，k 为某一侧的元素数量*/
    private long helper(int max, int k){
        if(max > k)return (long)k * (max - 1 +  (max - 1 - k + 1)) / 2;
        else return (long)(max - 3) * max / 2 + k + 1;
    }


    public static void main(String[] args) {
        new D4T1802().maxValue(3,0,815094800);
    }

}
