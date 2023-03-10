/**
 * 1781. 所有子字符串美丽值之和
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 *
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 *
 *
 * 思路：
 *
// * 错误！
// * 求子串，动态规划。
// *
// * subMax[i] 以第i个为结尾的子串的最高频次。  subs[i] = s[i] == s[i-1] ? subs[i-1]+1: subs[i-1]
// * subMin[i] 以第i个为结尾的子串的最低频次。
// *
 *
 *subMax[i][j] 以第i个为结尾的子串的最高频次。
 *subMin[i][j] 以第i个为结尾的子串的最低频次。
 *
 *
 *
 *
 *
 * 参考了官方答案。 直接暴力求子串， 判断最大最小。
 *
 *
 *
 * @author rzet
 * @date 2022/12/16 11:27
 */
public class T1781D12 {

    public int beautySum0(String s) {
        int n = s.length();
        int sum = 0;

        //求每个i位置开始的所有子串
        for (int i = 0; i < n; i++){
            int[] freq = new int[26];
            int max = 0;
            for (int j = i; j < n ;j++){
                freq[s.charAt(j) - 'a']++;
                max = freq[s.charAt(j) - 'a'] > max? freq[s.charAt(j)-'a']: max;

                int min = n;
                for (int c = 0; c < 26; c++)
                    if(freq[c] > 0)
                        min = min < freq[c] ? min: freq[c];

                sum  += (max - min);
            }
        }
        return sum;
    }



    public int beautySum(String s) {

//        int n = s.length();
//        int[] freq = new int[26]; //26字母的频次
//        int[] subMax = new int[n];
//        int[] subMin = new int[n];
//
//        int sum = 0;
//        for(int i = 0; i < n; i++){
//            subMax[i] = 0;
//            subMin[i] = n;
//            freq[s.charAt(i) - 'a']++;
//            for (int j = 0 ;j < 26; j++){
//                if (freq[j] > 0) {
//                    subMax[i] = Math.max(subMax[i], freq[j]);
//                    min = Math.min(min, freq[j]);
//                }
//            }
//            sum = subMax[i] - subMin[i];
//        }
//
//        return sum;
        return 0;
    }
}
