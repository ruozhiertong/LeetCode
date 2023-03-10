/**
 * 2185. 统计包含给定前缀的字符串
 * 给你一个字符串数组 words 和一个字符串 pref 。
 *
 * 返回 words 中以 pref 作为 前缀 的字符串的数目。
 *
 * 字符串 s 的 前缀 就是  s 的任一前导连续字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["pay","attention","practice","attend"], pref = "at"
 * 输出：2
 * 解释：以 "at" 作为前缀的字符串有两个，分别是："attention" 和 "attend" 。
 * 示例 2：
 *
 * 输入：words = ["leetcode","win","loops","success"], pref = "code"
 * 输出：0
 * 解释：不存在以 "code" 作为前缀的字符串。
 *
 *
 *
 *
 * 思路：
 * 1。暴力。判断是否前缀。 O(m*n) m字符串个数。n prefix长度。
 *
 * 2。使用字典树，前缀树进行统计。
 *
 *
 *
 *
 * @author rzet
 * @date 2023/1/8 22:59
 */
public class D8T2185 {

    public int prefixCount(String[] words, String pref) {

        int count = 0;
        for (String word: words){
//            if (isStartPref(word, pref))
            if (word.startsWith(pref))
                count++;
        }

        return count;
    }



    private boolean isStartPref(String src, String pre){

        int n = src.length();
        int m = pre.length();


        int i = 0;
        for(i = 0; i < m && i < n; i++){
            char sc = src.charAt(i);
            char pc = pre.charAt(i);
            if (sc != pc)
                return false;
        }

        if (i == m)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        new D8T2185().prefixCount(new String[]{"pay","attention","practice","attend"}
        ,"at");
    }


}
