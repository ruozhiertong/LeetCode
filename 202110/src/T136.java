import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 *
 *
 * 思路：方法1.排序。 然后遍历，如果仅出现第一次，并且与后面不同，那么就是要找的数。或者排序后前后进行加减。 o(n^2)
 *      方法2.使用hashmap或者set。对key进行计数，找出只有计数值为1的。 o(1)
 *      方法3.空间换时间。用很大的数组，以原数组中的值为idx，并计数。 值为1的idx即要找的数。 O(1)
 * @author rzet
 * @date 2021/10/9 15:55
 */
public class T136 {
    public int singleNumber(int[] nums) {

        Set<Integer> s = new HashSet<Integer>();

        for (int e: nums){
            if(!s.contains(e))
                s.add(e);
            else
                s.remove(e);
        }
        return (Integer) s.toArray()[0];
    }
}
