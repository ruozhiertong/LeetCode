import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 *
 * 思路：
 * 1。非常原始，暴力的思路。做一个类似邻接矩阵，重叠矩阵 n*n。直到邻接矩阵全为0。 每次去除重叠最多的那个区间。 贪心。 先去除重复的区间，再去除重叠最多的区间。
 * 不过好像不准确。 要找一个反例。 TODO。
 *
 * 2。动态规划。
 *
 *
 * 3。贪心。
 *
 * @author rzet
 * @date 2021/10/19 10:19
 */
public class T435 {

    public static boolean isOverlap(int []a, int []b){

        if(a[1] <= b[0] || a[0]>= b[1])
            return false;
        else
            return true;
    }
    public static boolean isZero(int [][]matrix){
        for (int i = 0; i < matrix.length; i++){
            for (int j = i + 1; j < matrix.length; j++) //只需检测上三角
                if(matrix[i][j] != 0)
                    return false;
        }
        return true;
    }
    public static int maxOverlap(int [][]matrix){
        long maxSum = -1; //因为可能超过int。
        int idx = -1;

        for (int i = 0 ; i < matrix.length; i++){
            long sum = 0;
            for (int j = 0 ; j < matrix[i].length;j++){
                sum += matrix[i][j];
            }
            if (maxSum < sum){
                maxSum = sum;
                idx = i;
            }
        }
        return idx;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {

        int n = intervals.length;
        int matrix[][] = new int[n][n]; //初始化 0值。

        for (int i = 0; i < n ; i++){
            for (int j = i + 1; j < n; j++){
                //重复。视为最多的重叠。
                if (intervals[i][0] == intervals[j][0] && intervals[i][1] == intervals[j][1])
                    matrix[i][j] = matrix[j][i] = Integer.MAX_VALUE;
                else{
                    matrix[i][j] = matrix[j][i] = isOverlap(intervals[i], intervals[j])?1: 0;
                    //实际上只用上三角就可以的。
                }

            }
        }

        int count = 0;
        while(!isZero(matrix)){
            //去除 重叠最多的元素
            int idx = maxOverlap(matrix);
            //System.out.println(idx);
            System.out.println(intervals[idx][0] + "," + intervals[idx][1]);
            for (int j = 0; j < matrix[idx].length; j++){
                matrix[idx][j] = 0;
                matrix[j][idx] = 0;
            }
            count++;
        }

        return count;
    }


    //贪心。
    public static int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //先排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //记录区间尾部的位置
        int start = intervals[0][0];
        int end = intervals[0][1];
        //需要移除的数量
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                //如果重叠了，必须要移除一个，所以count要加1，
                //然后更新尾部的位置，我们取尾部比较小的
                //end = Math.min(end, intervals[i][1]);
                if(end < intervals[i][1]){
                    System.out.println(intervals[i][0] + "," + intervals[i][1]);
                }else{
                    System.out.println(start+"," + end);
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
                count++;
            } else {
                //如果没有重叠，就不需要移除，只需要更新尾部的位置即可
                end = intervals[i][1];
                start = intervals[i][0];
            }
        }
        return count;
    }

    //贪心2
    public static int eraseOverlapIntervals22(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //先排序。 按照右端点排序。
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0; //记录不重叠的个数
        //记录区间尾部的位置
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
           if (intervals[i][0] >= end) {//不重叠
               count++;
               end = intervals[i][1]; //更新end
           }
        }
        return intervals.length - count;
    }


    //动态规划。 先排序。 fi 表示 第i个区间结尾最大的不重叠区间个数。 不过时间超限了。O(n^2)
    public int eraseOverlapIntervals3(int [][]intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int fi[]  =new int[intervals.length];
        Arrays.fill(fi,1); //默认都有自己一个区间。
        fi[0] = 1;
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) { //fi[i]和之前的各个fi比较
                if (intervals[i][0] >= intervals[j][1]) //不重叠
                    fi[i] = Math.max(fi[i], fi[j] + 1);
                //else //如果遍历后和其他结尾的区间都有重叠，那么就只能是自己本身。
                //    fi[i] = fi[j];

            }
        }
        int max = 0;
        for (int i = 0; i < fi.length; i++){
            if (fi[i] > max)
                max = fi[i];
        }
        return intervals.length - max;
    }




    public static void main(String[] args) {
        int [][]a = new int[][]
                {{-25322,-4602},{-35630,-28832},{-33802,29009},{13393,24550},{-10655,16361},{-2835,10053},{-2290,17156},{1236,14847},{-45022,-1296},{-34574,-1993},{-14129,15626},{3010,14502},{42403,45946},{-22117,13380},{7337,33635},{-38153,27794},{47640,49108},{40578,46264},{-38497,-13790},{-7530,4977},{-29009,43543},{-49069,32526},{21409,43622},{-28569,16493},{-28301,34058}};
        int n  = eraseOverlapIntervals2(a);
        System.out.println(n);
    }

}
