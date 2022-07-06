/**
 * 1314. 矩阵区域和
 * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k 且
 * (r, c) 在矩阵内。
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[12,21,16],[27,45,33],[24,39,28]]
 *
 *
 * 思路：与T304类似。
 *
 * @author rzet
 * @date 2021/12/2 18:24
 */
public class T1314 {

    public int[][] matrixBlockSum(int[][] mat, int k) {

        int dp[][] = new int[mat.length + 1][mat[0].length + 1]; //0， 0 作为哑的

        for (int i = 0 ; i < dp.length; i++)
            dp[i][0] = 0;
        for (int j = 0; j < dp[0].length; j++)
            dp[0][j] = 0;

        for (int i = 1; i < dp.length; i++){
            for (int j = 1; j < dp[i].length;j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + mat[i - 1][j - 1];
        }

        int answer[][] = new int[mat.length][mat[0].length];

        for(int i = 0; i < answer.length; i++){
            for (int j = 0; j < answer[0].length;j++){
                int r1 = i - k <0? 0:i - k;
                int r2 = i + k < answer.length? i +k: answer.length - 1;
                int c1 = j - k <0? 0: j -k;
                int c2 = j + k <answer[0].length? j + k: answer[0].length - 1;

                answer[i][j] = dp[r2 + 1][c2 + 1] - dp[r1 + 1 -1] [c2 + 1] - dp[r2+1][c1 + 1 -1] + dp[r1+ 1-1][c1+ 1-1];
            }
        }

        return answer;

    }
}
