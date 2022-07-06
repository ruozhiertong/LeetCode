import java.util.List;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定 s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：拆分时可以重复使用字典中的单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 *
 * 思路：0。遍历 循环 暴力。 递归方式。 o(n！*m) 。 DFS。 （避免时间超限，可以加入记忆化）
 *      1。动态规划。 O()
 *      2。字典树／tril树／前缀树 + DFS。（避免时间超限，可以加入记忆化）
 *      https://leetcode-cn.com/problems/word-break/solution/geekplayes-leetcode-ac-qing-xi-yi-dong-d-vwbu/
 *      3。DFS，BFS。 回溯。 实际上也是暴力。
 *      https://leetcode-cn.com/problems/word-break/solution/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/
 *
 * @author rzet
 * @date 2021/11/19 11:36
 */
public class T139 {
    public boolean wordBreak(String s, List<String> wordDict) {

        if (wordDict.contains(s))
            return true;

        for (int i = 1; i < s.length(); i++){
            String subS = s.substring(0,i);
            if (wordDict.contains(subS)){
                if (wordBreak(s.substring(i,s.length()), wordDict))
                    return true;
            }
        }
        return false;
    }


    //动态规划
    public boolean wordBreak2(String s, List<String> wordDict) {

        boolean dp[] = new boolean[s.length() + 1]; // dp[i]表示到i为止的子串拆分后能否在wordDict中。
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++){
            for (int j = i - 1; j >=0; j--){
                if (wordDict.contains(s.substring(j, i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

}
