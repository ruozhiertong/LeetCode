import com.sun.tools.javac.code.TargetType;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 刷算法一定要有方法，要不然没啥效果的！！！
 *
 * 比如，类似的题目要一起刷呢，刷完这道题目，你可以再刷下面的 5 道类似题目：
 *
 * 两数之和Ⅱ - 输入有序数组
 * 两数之和Ⅲ - 数据结构设计
 * 两数之和Ⅳ - 输入 BST
 * 三数之和
 * 四数之和
 * 刷完这些题，你将会得到以下技能：
 *
 * 一个刷题套路：先暴力，后优化，再继续优化，最后追求程序的极致性能
 * 三个优化算法的应用：二分查找、哈希查找以及双指针技巧
 *
 * 作者：tangweiqun
 * 链接：https://leetcode.cn/problems/two-sum/solution/zhu-jian-you-hua-yi-zhi-dao-zui-you-pei-sexli/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 *
 *
 * 思路：
 * 0。暴力。O(n^2)
 * 1。循环一次 构建hashmap。 然后再遍历一次 找出hashmap中的值。O(n). 注意nums中的相同值。
 *
 * @author rzet
 * @date 2023/1/10 16:20
 */
public class T1D10 {

    public int[] twoSum(int[] nums, int target) {

        int n = nums.length;
        for(int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (nums[i] + nums[j] == target)
                    return new int[]{i ,j};
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {

        Map map = new HashMap<Integer, Integer>(); //key 是数值。 value是idx.
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if (map.get(nums[i]) != null && nums[i] + nums[i] == target)//如果有相同的元素。
                return new int[] {(int)map.get(nums[i]) , i};
            else
                map.put(nums[i], i);
        }

        for (int i = 0; i< n; i++){
            int sec = target - nums[i];
            if (sec != nums[i] && map.get(sec) != null)
                return new int[] {i, (int)map.get(sec)};
        }
        return null;
    }
}
