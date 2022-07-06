import sun.java2d.xr.MaskTile;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 *
 *
 *
 * 思路：
 * 0。最简单的就是直接算啊。 不过很可能超时。 o(n*m)
 *
 * 1。最好做一个备忘。即动归。
 * dp[i][j] 表示 i ,j 到 （0，0）的和。
 *
 *
 *
 * @author rzet
 * @date 2021/11/26 13:26
 */
public class T304 {
    int [][] matrix;
    int [][] dp;

    public T304(int[][] matrix) {
        this.matrix = matrix;
        dp = new int[matrix.length][matrix[0].length];

        dp[0][0] = matrix[0][0];
        for (int j = 1; j < dp[0].length; j++)
            dp[0][j] = dp[0][j-1] + matrix[0][j];


        for (int i = 1; i < dp.length; i++){
            dp[i][0] = dp[i-1][0] + matrix[i][0];
            for (int j = 1; j < dp[i].length;j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum = dp[row2][col2];

        if (row1 == 0 || col1 ==0){
            if (row1 ==0 && col1 != 0)
                sum -= dp[row2][col1 -1];
            else if(col1 == 0 && row1 != 0)
                sum -= dp[row1-1][col2];
        }else{
            sum = sum -dp[row1 -1] [col2] - dp[row2][col1 -1] + dp[row1-1][col1-1];
        }
        return sum;
    }
}
