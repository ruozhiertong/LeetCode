/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 *
 *
 * 思路： 动态规划。 思路和最长公共子序列类似 T1143。 都是二维dp，状态来源也是三个。 二维的dp要注意状态的转移。
 *
 * dp[i][j]  表示word1 i结尾到word2 j结尾 的最小编辑距离。
 *
 * dp[i][j] 的来源三个 ， 取最小。
 *      dp[i-1][j] + 1(删除)
 *      dp[i][j-1] + 1(增加)
 *      dp[i-1][j-1] +0（word1[i]==word2[j]）, 或+1(替换)
 *
 *
 * @author rzet
 * @date 2021/12/16 18:20
 */
public class T72 {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++){
            dp[i][0] = i; //完全删除操作
        }

        for (int j = 0; j <= n; j++){
            dp[0][j] = j; //完全添加操作。
        }

        for (int i = 1; i <= m; i++){
            char c1 = word1.charAt(i-1);
            for (int j =1; j <= n; j++){
                char c2 = word2.charAt(j-1);
                int dst = 0;
                if (c1 == c2)
                    dst = dp[i-1][j-1];
                else
                    dst = dp[i-1][j-1] + 1;
                dp[i][j] = Math.min(dst, Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1));
            }
        }

        return dp[m][n];

    }
}
