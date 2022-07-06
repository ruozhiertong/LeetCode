/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 * 思路： dp。 和爬楼梯类似的。
 *
 * @author rzet
 * @date 2021/12/2 19:25
 */
public class T62 {

    public int uniquePaths(int m, int n) {
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
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][ j -1];
            }
        }

        return dp[m][n];
    }


}
