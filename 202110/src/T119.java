import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和
 *
 *
 *
 * 思路：分治，递归。要先计算出上一行的值。
 *
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/18 12:39
 */
public class T119 {

    public List<Integer> getRow(int rowIndex) {

        List<Integer> l = new ArrayList<Integer>();
        if(rowIndex == 0){ //从0 开始
            l.add(1);
            return l;
        }
        l.add(1);
        List<Integer> preL = getRow(rowIndex - 1);
        for (int i = 1; i <= rowIndex - 1; i++){
            l.add(preL.get(i-1) + preL.get(i));
        }
        l.add(1);

        return  l;
    }

}
