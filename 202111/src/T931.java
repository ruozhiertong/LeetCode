/**
 * 931. 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 *
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 *
 *
 *
 * 思路：同T20。
 *
 * dp[i][j]从顶到 i，j位置的最小路径。
 *
 *
 * @author rzet
 * @date 2021/12/3 13:33
 */
public class T931 {
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int dp[][] = new int[m][n];

        for (int i =0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0){
                    dp[0][j] = matrix[0][j];
                }else{
                    dp[i][j] = matrix[i][j];
                    if (j == 0 && j == n-1)
                        dp[i][j] += dp[i-1][j];
                    else if (j == 0 && j != n-1)
                        dp[i][0] += Math.min(dp[i-1][j], dp[i-1][j+1]);
                    else if(j == n-1 && j != 0)
                        dp[i][j] += Math.min(dp[i-1][j],dp[i-1][j-1]);
                    else
                        dp[i][j] += Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i-1][j+1]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++){
            if (dp[m-1][j] < min)
                min = dp[m-1][j];
        }

        return min;
    }
}
