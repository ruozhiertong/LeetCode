/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 思路： 1.动态规划。 同时利用到前缀和用于判断是否全为1。
 *  其实方法1并不是动态规划，只是利用前缀和本身的动态规划。 实际上还是暴力。只是判断是否正方形的条件优化了，利用前缀和进行了优化。
 *
 *  2。真正的动态规划。应该是官方的那种。 关键在于dp的转移方程。
 *
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/12/3 00:09
 */
public class T221 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int dp[][] = new int[m][n]; //dp[i][j]表示 以i j为右下角终点时 最大正方形的面积。

        int sum[][] = new int[m + 1][n + 1]; //sum[i][j] 0,0 到 i j的sum。

        for(int i = 0; i <= m; i++){
            for (int j =0; j <= n;j++){
                if(i == 0){
                    sum[0][j] = 0;
                }else if(j == 0){
                    sum[i][0] = 0;
                }else{
                    sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + (matrix[i -1][j -1] - '0');
                }
            }
        }

        int max = 0;
        for (int i = 0;i < m; i++){
            for (int j = 0; j < n;j++){
                int maxLen = Math.min(i,j) + 1;
                for (int len = maxLen; len >= 1; len--){
                    int r1,c1,r2,c2;
                    r2 = i;
                    c2 = j;
                    r1 = i - len + 1;
                    c1 = j -len + 1;
                    int subSum = sum[r2 + 1][c2 + 1] - sum[r1 + 1 -1] [c2 + 1] - sum[r2+1][c1 + 1 -1] + sum[r1+ 1-1][c1+ 1-1];
                    if (subSum == len * len){
                        dp[i][j] = len*len;
                        if (dp[i][j] > max)
                            max = dp[i][j];
                        break;
                    }
                }
            }
        }

        return max;

    }

}
