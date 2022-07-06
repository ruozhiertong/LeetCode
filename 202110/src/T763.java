import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z'
 *
 * 思路： 模拟。 遍历。将读到的字符，定位到最后出现该字符的位置。 这种一般也是暴力的。 时间复杂度较高。
 *
 * TODO 应该思考好的方法。
 *
 *
 * @author rzet
 * @date 2021/10/21 09:13
 */
public class T763 {

    public List<Integer> partitionLabels(String s) {

        List<Integer> l = new LinkedList<Integer>();

        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int start = -1, end = 0;
        int i = 0;
        char c;
        while (i < s.length()){
            start = i;
            c = s.charAt(start);
            hashMap.put(c, 1); //只存放出现的第一个字符。
            end = s.lastIndexOf(c);

            while(start < end){
                start++;
                c = s.charAt(start);
                if (hashMap.containsKey(c))
                    continue;
                int end0 = s.lastIndexOf(c);
                if (end0 > end)
                    end  = end0;
            }

            l.add(end - i + 1);
            i = end + 1;
        }
        return l;
    }



    //官方的贪心 好妙。
    public List<Integer> partitionLabels2(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            //分割区域的第一个字符，不在之前区域中，最后一个字符也不在之后的区域中。
            //所以 当最后出现位置和当前位置相同，表明是该区域的最后一个字符。
            if (i == end) { //只有最后出现位置和当前位置相同，就表明分割结束。
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
