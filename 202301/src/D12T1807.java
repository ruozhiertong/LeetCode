import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * 1807. Evaluate the Bracket Pairs of a String
 * You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
 *
 * For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and "age".
 * You know the values of a wide range of keys. This is represented by a 2D string array knowledge where each knowledge[i] = [keyi, valuei] indicates that key keyi has a value of valuei.
 *
 * You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair that contains some key keyi, you will:
 *
 * Replace keyi and the bracket pair with the key's corresponding valuei.
 * If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?" (without the quotation marks).
 * Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
 *
 * Return the resulting string after evaluating all of the bracket pairs.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
 * Output: "bobistwoyearsold"
 * Explanation:
 * The key "name" has a value of "bob", so replace "(name)" with "bob".
 * The key "age" has a value of "two", so replace "(age)" with "two".
 *
 *
 *
 *
 *
 * 思路： 直接上。map存储key value， 然后替换字符串。
 *
 * @author rzet
 * @date 2023/1/14 13:41
 */
public class D12T1807 {
    public String evaluate(String s, List<List<String>> knowledge) {

        Map knowledgeMap  = new HashMap<String, String>();

        for (List e : knowledge){
            knowledgeMap.put(e.get(0), e.get(1));
        }


        List bracketPos = new LinkedList<Integer[]>();

        //find bracket position
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                int[] b = new int[2];
                b[0] = i;
                bracketPos.add(b);
            }
            if (s.charAt(i) == ')'){
                int[] b = (int[])bracketPos.get(bracketPos.size() -1);
                b[1] = i;
//                bracketPos.set(bracketPos.size() -1,b); //不需要也行。
            }
        }

        StringBuffer sb = new StringBuffer(s);
        //replace
        for (int i = bracketPos.size() - 1; i >=0; i--){
            int[] pos = (int[])bracketPos.get(i);
            String substr = sb.substring(pos[0]+1, pos[1]);
            String repS = (String)knowledgeMap.getOrDefault(substr,"?");
            sb.replace(pos[0],pos[1] + 1,repS);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List l = new LinkedList<List<String>>();
        List e = new LinkedList<String>();
        e.add("name");
        e.add("bob");
        l.add(e);
        e = new LinkedList<String>();
        e.add("age");
        e.add("two");
        l.add(e);
        String s = new D12T1807().evaluate("(name)is(age)yearsold", l);
        System.out.println(s);
    }

}
