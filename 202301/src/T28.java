/**
 *
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 *
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 *
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2023/1/3 15:51
 */
public class T28 {

    //字符串匹配。
    //1.暴力。 2.KMP
    public int strStr(String haystack, String needle) {

        for(int i = 0; i < haystack.length(); i++){
            int j = 0;
            for(j = 0; j < needle.length() && i+j <haystack.length(); j++){
                if (needle.charAt(j) != haystack.charAt(i+j))
                    break;
            }
            if (j == needle.length())
                return i;
        }
        return -1;
    }
}
