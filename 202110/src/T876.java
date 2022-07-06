import java.util.List;

/**
 * 876. 链表的中间结点
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 *
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 * 思路：
 * 1。一般想法。 遍历计数。 然后再遍历计一半的数。 两次遍历。 o(n).
 *
 * 2。快慢指针。 慢走1， 快走2， 当快的走到尾，慢的就是重点。 一次遍历。 o(n). 注意边界。
 *
 * @author rzet
 * @date 2021/10/25 14:14
 */
public class T876 {
    public ListNode middleNode(ListNode head) {

        ListNode pSlow = head;
        ListNode pFast = head;

        while (pFast != null && pSlow != null){
            pFast = pFast.next;
            if (pFast == null)
                break;
            pSlow = pSlow.next;
            pFast = pFast.next;
        }

        return pSlow;


    }

}
