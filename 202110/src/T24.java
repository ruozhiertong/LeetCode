import java.util.List;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 * @author rzet
 * @date 2021/10/23 11:28
 */
public class T24 {
    public ListNode swapPairs(ListNode head) {

        ListNode p = head;
        ListNode pre = null;
        int count = 0;
        while (p != null){
            count++;
            if (count % 2 == 1) {//奇数个进行处理交换
                if (p.next != null){
                    ListNode pNext = p.next;
                    if (pre == null){ //第一个 . 如果不想处理第一个，那么可以设置一个头的哑节点。
                        p.next = pNext.next;
                        pNext.next = p;
                        head = pNext;
                    }else{
                        pre.next = pNext;
                        p.next = pNext.next;
                        pNext.next = p;
                    }
                    p = pNext;
                }
            }
            pre = p;
            p = p.next;
        }

        return head;
    }
}
