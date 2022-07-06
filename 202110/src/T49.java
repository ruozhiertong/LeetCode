import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 *
 * 思路： 1。计算每个字符串中各个字符次数。 两个字符串中各字符和出现个数相同 那么这两个字符串就是异位词。
 *         费力不讨好。能做。
 *
 * 2。每个字符串排序，然后转化成找重复的字符串。
 *
 *
 * 3。官方的字母+次数组成的串 作为map的key。 妙。
 *
 *
 *
 * @author rzet
 * @date 2021/10/21 11:29
 */
public class T49 {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, LinkedList<String>> hashMap = new HashMap<String,LinkedList<String>>();

        for (String s : strs){
            char []tmp = s.toCharArray();
            Arrays.sort(tmp);
            String ks = String.valueOf(tmp);
            if (hashMap.containsKey(ks)){
                hashMap.get(ks).add(s);
            }else{
                LinkedList<String> l = new LinkedList<String>();
                l.add(s);
                hashMap.put(ks, l);
            }
        }
        List<List<String>> result = new LinkedList<List<String>>();
        for (List<String> v: hashMap.values()){
            result.add(v);
        }
        return result;
    }
}
