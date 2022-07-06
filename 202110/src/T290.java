import javafx.scene.shape.PathElement;

import java.util.HashMap;

/**
 * 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 *
 *
 *
 *
 * 思路： 使用hashmap判断规律。
 *
 *
 *
 * @author rzet
 * @date 2021/10/20 19:34
 */
public class T290 {
    public boolean wordPattern(String pattern, String s) {

        HashMap<String,Integer> patternMap = new HashMap<String,Integer>();
        HashMap<String,Integer> strMap = new HashMap<String, Integer>();

        String[] sArr = s.split(" ");
        if(pattern.length() != sArr.length)
            return false;

        for (int i = 0; i < pattern.length(); i++){
            int idx1 = patternMap.getOrDefault(pattern.substring(i,i+1),-1);
            int idx2 = strMap.getOrDefault(sArr[i], -1);
            if(idx1 != idx2)
                return false;

            if(idx1 == -1) {
                patternMap.put(pattern.substring(i,i+1), i);
            }
            if (idx2 == -1){
                strMap.put(sArr[i],i);
            }
        }
        return true;

    }

    //优化，使用一个map
    public boolean wordPattern2(String pattern, String s) {

        HashMap<Character, String> patternStrMap = new HashMap<Character, String>();

        String[] sArr = s.split(" ");
        if (pattern.length() != sArr.length)
            return false;

        for (int i = 0; i < pattern.length(); i++) {
            if (!patternStrMap.containsKey(pattern.charAt(i)) && !patternStrMap.containsValue(sArr[i])) {
                patternStrMap.put(pattern.charAt(i), sArr[i]);
            } else {
                if (!patternStrMap.getOrDefault(pattern.charAt(i), "").equals(sArr[i]))
                    return false;
            }
        }
        return true;
    }
}
