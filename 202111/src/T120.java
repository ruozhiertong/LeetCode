import com.sun.rowset.internal.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 * 思路： 还是比较简单的，和T64类似。 使用动态规划。
 *
 * dp[i][j] 表示到达 i，j 位置最小的路径。
 *
 * @author rzet
 * @date 2021/12/3 12:54
 */
public class T120 {
    public int minimumTotal(List<List<Integer>> triangle) {

        List<List<Integer>> dp = new ArrayList<List<Integer>>();

        for(int rowIdx = 0; rowIdx < triangle.size(); rowIdx++){
            List<Integer>dpRow = new ArrayList<Integer>();
            if (rowIdx == 0){
                for (Integer e: triangle.get(rowIdx))
                    dpRow.add(e);
            }else{
                List<Integer> preRowDP = dp.get(rowIdx - 1);
                List<Integer> curRow = triangle.get(rowIdx);
                for (int col = 0; col < curRow.size(); col++){
                    if (col == 0){
                        dpRow.add(preRowDP.get(0) + curRow.get(0));
                    } else{
                        if (col < preRowDP.size()){
                            dpRow.add(Math.min(preRowDP.get(col),preRowDP.get(col -1)) + curRow.get(col));
                        }else if (col == preRowDP.size())
                            dpRow.add(preRowDP.get(col -1) + curRow.get(col));
                        else
                            dpRow.add(Integer.MAX_VALUE);
                    }
                }
            }
            dp.add(dpRow);
        }
        int minResult = Integer.MAX_VALUE;
        for(Integer e : dp.get(dp.size() - 1)){
            if (e < minResult)
                minResult = e;
        }
        return minResult;
    }

}
