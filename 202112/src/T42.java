import javax.swing.text.Highlighter;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 *
 * 思路： 没什么思路，那么就直接模拟下。一个下标一个下标的模拟过去。
 * 分析后发现，最高的柱子起到关键的作用。 所以要维护当前索引范围的最高柱子的idx。 最高柱子前的区域不受后面柱子的影响。
 *
 * dp[i] 表示以i结尾时能接到的最多雨水量。
 *
 * 实际上还是进入了动归。 只是要分析状态转移方程的条件。不过我这个动归是一个o(n^2).
 *
 *
 *
 * @author rzet
 * @date 2021/12/16 16:56
 */
public class T42 {
    public int trap(int[] height) {

        int[] dp = new int[height.length]; //表示当前idx下的最多雨水
        dp[0] = 0;
        int h_idx = 0;
        for (int i = 1; i < height.length; i++){
            if (height[i] <= height[i-1]){//比前一个还矮。
                dp[i] = dp[i-1];
            }else{// 比前一个高。 那么应该从前找第一个比他高的，从这个起计算。计算这两个下标区域内的水量。
                int j;
                for (j = i - 1; j > h_idx; j--){
                    if (height[j] >= height[i])
                        break;
                }
                dp[i] = dp[j] + (i - j - 1) *Math.min(height[j],height[i]);
                for (int idx = j + 1; idx<= i -1; idx++){
                    dp[i] -= height[idx];
                }
                //如果比最高的还高
                if (height[i] >= height[h_idx]){
                    h_idx =i;
                }
            }
        }
        return dp[height.length -1];
    }
}