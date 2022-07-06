/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数
 *
 *
 *
 * 思路：动态规划。
 *      dp[i] 表示第i个为结尾时，能编码的个数。
 *
 *          以第i为结尾，情况就只有两种：
 *            a.如果nums[i]可以本身编码，那么dp[ia] = dp[i-1]
 *            b.如果nums[i]和nums[i-1]组合后可以编码，那么dp[ib] = dp[i -2]
 *
 *
 * @author rzet
 * @date 2021/11/25 15:10
 */
public class T91 {

    public int numDecodings(String s) {
        if (s.length() == 1)
            return s.charAt(0) - '0' > 0? 1: 0;
        if (s.length() == 2){
            int nums = 0;
            if (s.charAt(0) != '0' && s.charAt(1) != '0')
                nums++;
            if (s.charAt(0) != '0' && Integer.parseInt(s.substring(0,2)) < 26)
                nums++;
            return nums;
        }

        int dp[] = new int[s.length()];
        dp[0] = s.charAt(0) - '0' > 0? 1: 0;
        dp[1] = s.charAt(0) - '0' > 0 && s.charAt(1)- '0' > 0 ? 1:0;
        if (s.charAt(0) != '0' && Integer.parseInt(s.substring(0,2)) < 26)
            dp[1]++;

        for(int i = 2; i < s.length(); i++){
            if (s.charAt(i) != '0')
                dp[i] += dp[i-1];
            if (s.charAt(i-1) != '0' && Integer.parseInt(s.substring(i-1, i+1)) < 26)
                dp[i] += dp[i-2];
        }


        return dp[dp.length -1];
    }
}
