import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 *
 *
 * 思路：每次取一个区间和已在的区间数组里的每个区间进行处理。
 *
 *      1。排序，然后合并。
 *
 *      //我对于排序没有考虑到，导致后续的处理不到位。
 *
 * @author rzet
 * @date 2021/10/15 15:37
 */
public class T56 {

    public static void main(String[] args) {

        //java中二维数组，实际上是引用。因此一位数组中的个数可以不一致。 和C中普通的二维数组不一样，C中普通二维数组的一位数组中的个数要一致，和C中一维的指针数组 int* ipArr[3];这样每个数组中元素可以不一样。
        //https://zhidao.baidu.com/question/1689029767535382108.html
        int [][]a = new int[][]{{1,2},{3,4,5}};

        List<int[]> l = new ArrayList<int[]>();

        for (int i =0; i < a.length; i++) {
            l.add(a[i]);
            System.out.println(a[i]);
            for (int j = 0; j < a[i].length; j++) {
                System.out.println(a[i][j]);
            }
        }
        System.out.println(l);
        for (int [] e : l){
            System.out.println(e);
            for (int e1 : e)
                System.out.println(e1);
            System.out.println("==");
        }

        int [][]la = l.toArray(new int[5][9]);
        for (int[] e1 : la){
            for( int e2: e1)
                System.out.println(e2);
            System.out.println("------");
        }




    }

    public int [][] mergeTwo(int []a , int []b){

        if(a[1] < b[0]){
            return new int[][]{{a[0],a[1]},{b[0],b[1]}};
        }

        if(a[0] > b[1]){
            return new int[][]{{b[0],b[1]},{a[0],a[1]}};
        }

        //取最大最小组成区间。
        int min = Math.min(a[0],b[0]);
        int max = Math.max(a[1],b[1]);

        return new int[][]{{min, max}};

    }
    public int[][] merge(int[][] intervals) {
//            List<int[]> result = new ArrayList<int []>();
//            for (int [] e1 : intervals){
//                for (int []e2 : result){
//
//                }
//
//            }

        sortRange(intervals);

        List<int[]> result = new ArrayList<int []>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++){
            int [] a = result.get(result.size() - 1);
            result.remove(result.size() - 1);
            for (int [] e: mergeTwo(a, intervals[i]))
                result.add(e);
        }

        return result.toArray(new int[0][0]); //数组长度0的作用，并不是用于存储，用于表示类型占位。
    }
    public void sortRange(int[][] intervals){
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//                if(o1[0] < o2[0])
//                    return -1;
//                else if (o1[0] == o2[0])
//                    return 0;
//                else
//                    return 1;
                return o1[0] - o2[0];
            }
        });
    }



}

