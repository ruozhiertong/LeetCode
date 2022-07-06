import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 *
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 *
 * 思路： 暴力。
 *
 *       使用hashmap优化。遍历取得所有长度为10的子串。o((n-k)*n)  k=10; 遍历出所有子串。
 *
 * @author rzet
 * @date 2021/10/22 15:50
 */
public class T187 {
    public List<String> findRepeatedDnaSequences(String s) {

        List<String> l = new ArrayList<String>();
        int k = 10;


        //o（n^3）
        for(int i = 0; i < s.length() - k; i++){
            String subS = s.substring(i, i + k);

            if (s.substring(i+1).indexOf(subS) != -1){
                if (!l.contains(subS))
                    l.add(subS);
            }

        }
        return l;

    }


    public List<String> findRepeatedDnaSequences2(String s) {

        List<String> l = new ArrayList<String>();
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        int k = 10;

        //o(n)
        for(int i = 0; i <= s.length() - k; i++){
            String subS = s.substring(i, i + k);
            int count = hashMap.getOrDefault(subS, 0);
            hashMap.put(subS, count + 1);
            if (count == 1) //说明之前有加入过了。0没有加入过， 大于1重复了。
                l.add(subS);
        }
        return l;

    }

    public static void main(String[] args) {
        new T187().findRepeatedDnaSequences2("AAAAAAAAAAA");
    }
}
