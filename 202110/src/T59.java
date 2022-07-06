/**
 *
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *
 *
 * 思路： 初始化数组为0， 然后按照顺时针进行填入，遇到非0或越界的改变方向。
 *
 * @author rzet
 * @date 2021/10/18 18:29
 */
public class T59 {
    //数组定义时的初始化。 new 后不能指定大小。由系统来定。如果没有初始化，new后必须指定n。
    int position[][] =new int[][]{{0,1},{1,0},{0,-1},{-1,0}}; //右 下 左 上

    //int []t = new int[5];

    public int[][] generateMatrix(int n) {
        int [][] arr = new int[n][n]; //默认初始化是0,如果要其他值，必须for赋值。

        int i = 0, j= 0;
        int count = 1;
        int pos = 0;
        while(count <= n*n){
            arr[i][j] = count++;
            i += position[pos][0];
            j += position[pos][1];
            if(i == n || j ==n || i ==-1 || j ==-1 || arr[i][j] !=0 ){
                //回退
                i -=position[pos][0];
                j -=position[pos][1];
                pos = (pos + 1)%4;
                i +=position[pos][0];
                j +=position[pos][1];
            }
        }


        return arr;
    }
}
