/**
 *
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 思路： 从对角线二分查找，找到min max 然后在此区域寻找 target。
 * @author rzet
 * @date 2021/10/18 21:22
 */
public class T240 {
    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;
        int min = 0;
        int max = 0;

        int i = 0;
        for (i = 0; i < n; i++){
            if (matrix[i][i] >= target){
                break;
            }
        }
        min = i -1;
        max = i;
        if(min < 0 && matrix[max][max] != target || max == n)
            return false;

        //缩小到三个元素. 这个是针对n*n。 遇到m*n可能会退化成没效率的。
        if(matrix[max][max] == target || matrix[min][max] == target || matrix[max][min] == target)
            return true;
        else
            return false;




    }


    /**
     * 对角线找到比它大的第一个值，将举着分成四个区域。
     *
     *
     * 00xxxxx
     * 0Lxxxxx
     * xxG1111
     * xx11111
     * xx11111
     *
     *
     * 00000
     * 00000
     * 00000
     * 00000
     * 0000Y
     *
     * target就在x区域中。
     *
     * @param matrix
     * @param target
     * @return
     */

    public static boolean searchMatrix2(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;
        int x = Math.min(m ,n);
        int i = 0;
        for (i = 0; i < x; i++){
            if (matrix[i][i] == target){
                return true;
            }else if(matrix[i][i] > target)
                break;
        }

        //如果到最后一个还没有找到最大的，那么
        if(i == x){ //在右侧区域
            int r, c;
            if(m < n){
                r = 0;
                c = i - 1;
            }else if(m > n){
                r = i - 1;
                c = 0;
            }else{
                return  false;
            }
            for(int rt = r; rt < m; rt++){
                for (int ct = c; ct< n; ct++)
                    if (matrix[rt][ct] == target)
                        return true;
            }
            return false;
        }else{ //左侧区域。
            for (int r = 0; r< i; r++)
                for(int c = i; c < n; c++)
                    if(matrix[r][c] == target)
                        return true;
            for (int r = i; r < m; r++)
                for (int c = 0; c < i; r++)
                    if (matrix[r][i] ==  target)
                        return true;

            return false;
        }
    }

    public static void main(String[] args) {
        int arr[][] = new int[][]{{5},{6}};
        System.out.println(searchMatrix2(arr, 6));
    }
}
