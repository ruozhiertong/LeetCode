import com.sun.xml.internal.bind.v2.model.core.ID;
import jdk.nashorn.internal.runtime.FindProperty;

/**
 * @author rzet
 * @date 2022/7/5 11:30
 *
 * 字符串搜索算法。KPM。
 *
 * 如果朴素 暴力的算法，O(m*n)
 * KPM 可以达到O(n)。 巧妙的利用搜索过字符的共有的前缀、后缀。
 *
 */
public class KPM {

    public static void main(String[] args) {
        KPM kpm = new KPM();
        int[] next = kpm.next("byb-bbye");

        int idx = kpm.KPMSearch("byte bye-by bye-bye", "bye-bye");
        System.out.println(idx);
    }


    //判断dst是否在src子串, src是否包含dst。 indexOf。
    //返回src中的下标。
    //用dst去匹配src。
    //关键是求出nex数组。
    public int KPMSearch(String src, String dst){
        int []next = next(dst);

        int n = src.length();
        int m  = dst.length();

        if (n < m)
            return -1;

        char sc,dc;
        int next_idx = 0;
        int i = 0;
        while(i < n){
            sc = src.charAt(i);
            dc = dst.charAt(next_idx);
            if (sc == dc){
                next_idx++;
                i++;
            }else{
                if (next_idx == 0) {
                    i++;
                }else{
                    next_idx = next[next_idx - 1];
                }
            }
        }
        if (next_idx != m)
            return -1;
        return i - next_idx;
    }

    //求dst的next数组。 next[i]表示 第i个字符处，从0开始的前缀和到i结束的后缀的 最大长度。 长度。
    // abcdabc => next[] : 0 0 0 0 1 2 3
    // kpm 就是利用next进行决定下一个位置。
    public int[] next(String dst){

        int n = dst.length();
        int[] next = new int[n];
        next[0] = 0;
        int i = 1 , j = 0;//错开一位
        //用j去匹配i。
        while(i < n){
            if (dst.charAt(j) == dst.charAt(i)){
                next[i] = j + 1;
                i++;
                j++;
            }else{
                if (j == 0){
                    next[i] = 0;
                    i++;
                }else {
                    j = next[j - 1];
                }
            }
        }
        for (int e : next)
            System.out.print(e + " ");
        System.out.println();
        return next;
    }



}
