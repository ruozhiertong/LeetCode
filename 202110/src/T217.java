import java.util.HashMap;

/**
 *
 *
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 *
 * 思路：方式1.排序，遍历。  O(n^2)
 *      方式2.空间换时间，用很大数组去接，数据做idx。 O(1), 不过前提要保证数据范围。
 *      方式3.做hash。java中做hashmap
 *
 * @author rzet
 * @date 2021/10/8 21:02
 */
public class T217 {

    public boolean containsDuplicate(int[] nums) {

        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();

        for(int i = 0; i< nums.length; i++){
            if(hashMap.containsKey(nums[i]))
                return true;
            hashMap.put(nums[i],1);
        }

        return false;
    }
}
