/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 *
 *
 *
 * 思路：打家劫舍进阶版。 主要的是dp的含义和解释。
 *
 * dp[i] 还是表示以第i个结尾时，能偷到的最大金额数。
 * 抢到第i间房子时，能抢到的最大的金额。
 *
 *
 * dp[i] 表示考虑第i个及之前的节点，最大的金额。 以第i节点为考虑对象。  那么dp[i]  考虑分两种，i选中，i未选中。 注意是考虑，考虑实际上是分两种偷或没偷。
 *      i选中时，那么就不能再考虑i-1的情况，即只能从i-2来，只能考虑i-2节点的情况 + nums[i]，即dp[i-2].（i-2的情况，不一定是i-2选中，也有可能未选中）
 *      i未选中时，那么可以考虑i-1节点的情况，dp[i-1]。（ 注意这⾥是考虑，并不是⼀定要偷i-1房，也有可能没有偷i-1房）
 * 以上就是i节点的全部情况的全集。
 * 和爬楼梯类似的。
 *
 *
 * 所以， dp[i] = max(dp[i-2] + nums[i], dp[i-1])
 *
 *
 * 主要的是 要打破环，形成普通版的打家劫舍。
 *
 *
 * 一般能用dp解决的，也可以用暴力破解。
 * 暴力破解，枚举所有情况，就是计算解空间树，形成树结构。 对于规模小的，没问题，但是对于规模大的，就肯定超限的，复杂度太高。
 *
 *
 *
 * 有点麻烦。
 *
 * 不过想到了一个好思路，拆掉一个最小的节点，将其变成打家劫舍普通版，不就可以用dp了吗？ 应该是对的。
 *
 * 因为这个是圆形，每个元素都是同等地位的，因此选择去掉最小元素的那个节点没有问题的。
 *
 * 有问题。 因为去掉那一个是一个考虑的问题。 去掉后顺序就有关系了。因此不行。
 *
 * 除非 遍历每一个去掉的情况。
 *
 *
 * 官方的解释就很妙。只要比较第一间是否被偷。 只要比较第一间被偷与没偷情况下的最大dp。 而不需要像我考虑的那样去遍历每个不偷的情况。
 *
 * 应该考虑某个节点被偷或没被偷，形成全集。
 * 如果考率最小节点的去除情况，这个不是全集，只考虑了去除/即没被偷的情况。
 * 为了形成全集，我进行了遍历。而我之前遍历每个去掉的情况实际上是只考虑了没被偷的情况。所以显的复杂了。
 *
 * 所以，正确的思考和解释是很重要的。
 *
 *
 * 官方：[0,n-1].
 * 分成是否考虑0的情况。 考虑，不考虑。 考虑不考虑会对该节点前的有影响。因为后面节点会影响前面节点是否需要考虑。
 * 1。0不考虑，那么就可以考虑1和n-1，就是说可以考虑的范围是[1,n-1],那么就计算[1,n-1] 的dp，
 * 2. 0考虑， 那么就是从0开始考虑，n-1就不能考虑，那么就计算[0, n-2] 的dp。
 * 选择两种情况下 大的值。
 *
 *
 *
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/27 11:27
 */
public class T213 {

    public int rob(int[] nums) {


        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        if (nums.length ==3)
            return Math.max(Math.max(nums[0],nums[1]), nums[2]);

        int maxDP = 0;
        //        int minIdx = 0;
//        for (int i =0; i< nums.length; i++){
//            if (nums[minIdx] > nums[i])
//                minIdx = i;
//        }

        for(int rmIdx = 0; rmIdx < nums.length; rmIdx++){
            int dp[] = new int[nums.length - 1];
            dp[0] = nums[(rmIdx + 1)%nums.length];
            dp[1] = Math.max(nums[(rmIdx + 1)%nums.length],nums[(rmIdx + 2)%nums.length]);

            int startIdx = rmIdx + 3;
            for (int i = 2; i < dp.length; i++,startIdx++){
                dp[i] = Math.max(dp[i-1], dp[i-2] + nums[startIdx% nums.length]);
            }

            if (maxDP < dp[dp.length -1])
                maxDP = dp[dp.length -1];

        }
        return maxDP;

    }


    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {

        new T213().rob(new int[]{1,1,1,1});
    }

}
