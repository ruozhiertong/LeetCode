import java.util.List;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 *  L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 * 思路：
 * 找到中间节点， 反转后面的链表，然后合并。
 * @author rzet
 * @date 2021/10/25 15:43
 */
public class T143 {

    public void reorderList(ListNode head) {
        ListNode mid = new T876().middleNode(head);
        ListNode second = mid.next;
        //反转后面的链表。
        second = new T206().reverseList(second);

        mid.next = null;

        //合并
        ListNode l1 = head;
        ListNode l2 = second;
        ListNode result = new ListNode(); //哑节点
        ListNode pResult = result;
        while (l1 != null || l2 != null){
            if (l1 != null){
                pResult.next = l1;
                pResult = pResult.next;
                l1 = l1.next;
            }
            if (l2 != null){
                pResult.next = l2;
                pResult = pResult.next;
                l2 = l2.next;
            }
        }
    }

}
