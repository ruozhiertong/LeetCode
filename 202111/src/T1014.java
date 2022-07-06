/**
 * 1014. 最佳观光组合
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 *
 *
 *
 * 思路： 1。动归。
 *       dp[i] 表示以i为结尾的最高分。
 *
 *
 *       证明：
 * 思路其实很简单，需要一点点对数学归纳法的理解：
 * 假设我们已知前一个节点 j 能组成的最大的组合为（i，j）， 那么紧接着的一个节点 j+1 最大得分的组合一定是（i，j+1）和（j，j+1）这两个组合中较大的一个。
 * 可以简单证明一下为什么 j+1 与 j 之前的节点组合的话，最大的一定是（i，j+1）：
 *
 * // 记组合(i, j)的得分为f(i, j)
 * // 对 j 之前的任意节点 k（即0<= k < j）, 有这样一个关系：
 * // f(k, j+1) = f(k, j) - values[j] + values[j+1] - 1
 * // 可见,f(k, j)之后的部分是一个定值，也就是说f(k, j+1)的大小仅和f(k, j)的得分相关
 * // 从假设中我们可知，当 k 取 i 时, f(k, j)取到最大值
 * // 所以当 k 取 i 时f(k, j+1)也取到最大值
 * 有这个递推关系，也找到了本问题的最佳子结构，写出一个简单的动态规划解法也就是轻而易举的了
 *
 *
 *
 *      [8,1,5,2,6]
 * @author rzet
 * @date 2021/11/2 16:10
 */
public class T1014 {
    public int maxScoreSightseeingPair(int[] values) {

        int dp[] = new int[values.length]; //dp[i] 以i为结尾 的最高分。
        dp[0] = 0;

        int max = 0;
        for (int i = 1; i < values.length; i++){
            dp[i] = Math.max(dp[i-1] - values[i-1] + values[i]  - 1, values[i] + values[i-1] - 1);
            if (max < dp[i])
                max = dp[i];

        }
        return max;
    }
}
