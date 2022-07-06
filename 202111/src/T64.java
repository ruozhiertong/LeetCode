import javax.swing.*;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 * 思路： 动归， 和T62类似。
 * @author rzet
 * @date 2021/12/2 19:46
 */
public class T64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m+ 1][n + 1];

        for(int i = 0; i <= m; i++){
            dp[i][0] = Integer.MAX_VALUE;
        }

        for(int j = 0; j <= n; j++){
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= m ; i++){
            for (int j = 1; j <= n; j++){
                if (i == 1 && j ==1)
                    dp[1][1] = grid[0][0];
                else
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][ j -1]) + grid[i-1][j-1];
            }
        }

        return dp[m][n];
    }

}
