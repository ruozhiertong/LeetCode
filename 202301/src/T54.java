import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * -100 <= matrix[i][j] <= 100
 *
 *
 *
 *
 * 思路： 模拟。 右下左上
 *
 *
 * @author rzet
 * @date 2023/1/3 17:09
 */
public class T54 {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<Integer>();

        int[][] position = new int[][]{{0, 1},{1, 0},{0, -1},{-1, 0}};
        int pos = 0;
        int x = 0, y = 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int size = m * n ;
        while(true){

            result.add(matrix[x][y]);
            matrix[x][y] = -999;
            size--;
            if (size == 0)
                break;
            if (x + position[pos][0] == m || x + position[pos][0] < 0
              || y + position[pos][1] == n || y + position[pos][1] < 0
              || matrix[x + position[pos][0]][y+ position[pos][1]] ==  -999)
                pos = (pos + 1) % 4;


            x = x + position[pos][0];
            y = y +position[pos][1];

        }



        return result;
    }
}
