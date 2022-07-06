/**
 *
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 思路：
 * 1。直接更改next指针。改变指针指向。
 *
 * @author rzet
 * @date 2021/10/25 14:57
 */
public class T206 {
    public ListNode reverseList(ListNode head) {

        ListNode preNode = null;
        ListNode pNode = head;
        while (pNode != null) {
            ListNode next = pNode.next;
            pNode.next = preNode;
            preNode = pNode;
            pNode = next;
        }

        return preNode;
    }

}

