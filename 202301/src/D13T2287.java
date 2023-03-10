/**
 *
 * 2287. Rearrange Characters to Make Target String
 * You are given two 0-indexed strings s and target. You can take some letters from s and rearrange them to form new strings.
 *
 * Return the maximum number of copies of target that can be formed by taking letters from s and rearranging them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ilovecodingonleetcode", target = "code"
 * Output: 2
 * Explanation:
 * For the first copy of "code", take the letters at indices 4, 5, 6, and 7.
 * For the second copy of "code", take the letters at indices 17, 18, 19, and 20.
 * The strings that are formed are "ecod" and "code" which can both be rearranged into "code".
 * We can make at most two copies of "code", so we return 2.
 *
 *
 *
 * 思路：计数。
 *
 * @author rzet
 * @date 2023/1/14 16:58
 */
public class D13T2287 {
    public int rearrangeCharacters(String s, String target) {

        int[] tarLetter = new int[26];
        int[] sLetter = new int[26];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < target.length(); i++){
            tarLetter[target.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++){
            sLetter[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < target.length(); i++){
            int t = sLetter[target.charAt(i)-'a'] / tarLetter[target.charAt(i)-'a'];
            min = min < t? min:t;
        }



        return min;
    }

    public static void main(String[] args) {
        new D13T2287().rearrangeCharacters("ilovecodingonleetcode"
                ,"code");
    }


}
