/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 思路： 和T62 类似。 就是多了判断条件的。
 * @author rzet
 * @date 2021/12/2 19:35
 */
public class T63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int dp[][] = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++){
            dp[i][0] = 0;
        }

        for(int j = 0; j <= n; j++){
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m ; i++){
            for (int j = 1; j <= n; j++){
                if (i == 1 && j ==1)
                    dp[i][j] = obstacleGrid[0][0] == 0?1:0;
                else
                if (obstacleGrid[i -1][ j -1] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][ j -1];
            }
        }

        return dp[m][n];

    }
}
