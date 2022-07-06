import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 *
 *
 * 思路： 1。对各个字母计数。 回文，处理偶数个。
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/20 19:20
 */
public class T409 {


    public int longestPalindrome(String s) {

        //使用数组也可以。
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0) + 1);
        }
        int count = 0;
        int odd = 0;
        for (Map.Entry<Character, Integer> e : map.entrySet()){
            if (e.getValue() % 2 == 0)
                count += e.getValue();
            else{
                count += e.getValue() - 1;
                odd = 1;
            }
        }
        if(odd == 1)
            count += 1;

        return count;

    }
}
