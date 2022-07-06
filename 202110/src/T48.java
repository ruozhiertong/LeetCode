/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 * 思路： 分析规律。 Aij--> Bzy. z = j, y=n-i。
 *      TODO：可能有线性代数的方法来处理这个问题。
 *
 *
 * @author rzet
 * @date 2021/10/18 13:41
 */
public class T48 {

    //利用另一数组。
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int [][]tmp = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                tmp[j][n-1-i] = matrix[i][j];
            }
        }
        System.arraycopy(tmp, 0, matrix,0, n);
    }


    //观察规律。从正对角线开始循环。 每一次回字旋转。
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        int mid = n /2;
        int tmp = 0;
        int preV = 0;
        for (int i = 0; i < mid; i++){
            for ( int j = i; j < n -i - 1; j++){
                int preI = i , preJ =j;
                int nextI = -1, nextJ =-1;
                preV = matrix[preI][preJ];
                while(true){
                    nextI = preJ;
                    nextJ = n - 1 - preI;
                    tmp = matrix[nextI][nextJ];
                    matrix[nextI][nextJ] = preV;
                    preV = tmp;
                    preI = nextI;
                    preJ = nextJ;
                    if(preI == i && preJ == j)
                        break;
                }
            }
        }
    }


}
