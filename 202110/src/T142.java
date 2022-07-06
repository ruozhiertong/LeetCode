import java.util.HashMap;
import java.util.List;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *
 * 这个是 链表是否有环 进阶版。
 *
 *
 * 这个题目其实是找到环的第一个元素。
 * 1。第一个在hashmap中出现的那个元素。
 * 2。使用快慢指针，要用数学思维思考下 关系。
 *
 * 思路：
 *      1。循环，每个元素设置标志位，如果已经访问过的置1，如果再次访问到，则说明有环。
 *      2。使用hashmap。其实也是1的一种形式，如果访问的已经在hashmap中，那么就是有环。
 *      3。使用快慢指针。类似田径场跑圈， 快的赶上慢的，那么就是有环。 慢的走1步，快的走2步。 那么快的比慢的快1步，当赶上慢的就说明有环。赶上的距离是环的长度的倍数。
 *
 * @author rzet
 * @date 2021/10/22 17:11
 */
public class T142 {
    public ListNode detectCycle(ListNode head) {

        HashMap<ListNode, Integer> hashMap = new HashMap<ListNode, Integer>();

        ListNode p = head;
        while (p != null) {

            int count = hashMap.getOrDefault(p, 0);
            hashMap.put(p, count + 1);

            if (count == 1)
                return p;
            p = p.next;
        }
        return null;
    }
}
