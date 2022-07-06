import java.util.ArrayList;
import java.util.List;

/**
 *
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 * 思路：0。直接处理。
 *      1。使用dp[i][j] 表示第i行第j列的值。
 *
 *
 * @author rzet
 * @date 2021/11/25 19:53
 */
public class T118 {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> row;

        row = new ArrayList<Integer>();
        row.add(1);
        list.add(row);

        for(int i = 1; i < numRows; i++){
            row = new ArrayList<Integer>();
            row.add(1);
            List<Integer> preRow = list.get(i -1);
            for (int j = 0; j < preRow.size() -1; j++){
                row.add(preRow.get(j) + preRow.get(j+1));
            }
            row.add(1);
            list.add(row);
        }

        return list;





    }

}
