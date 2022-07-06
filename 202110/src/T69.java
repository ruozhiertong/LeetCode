import java.util.HashMap;
import java.util.Set;

/**
 *
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 实际上是找众数。
 *
 * 思路：方法1。 排序。 中间的那个数就是多数元素。 (这个要求多数元素是大于n/2，这时中位数就是众数)。
 *             进阶，排序。 以1／2区间长度为计量。 如果是大于1／3的元素，以1／3长度为计量。 滑动窗口。
 *      方法2。 更一般的情况是 找众数。 排序，遍历计数每个数的次数。
 *      方法3。 使用hashmap 进行计数。 O(n)
 *
 *
 *      学习，摩尔投票法。
 *
 *
 * @author rzet
 * @date 2021/10/9 16:40
 */
public class T69 {
    public int majorityElement(int[] nums) {

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int e: nums) {
            if (!hashMap.containsKey(e))
                hashMap.put(e, 1);
            else
                hashMap.put(e, hashMap.get(e) + 1);
        }
        int max = 0;
        int maxE = -9999;
        Set<Integer>  ks =  hashMap.keySet();
        for (Integer e : ks){
            if(hashMap.get(e) > max){
                max = hashMap.get(e);
                maxE = e;
            }
        }
        return maxE;
    }
}
