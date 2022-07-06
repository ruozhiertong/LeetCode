import java.util.HashMap;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 *
 *
 * 思路：两个hashmap存储两条链。
 *
 *
 * 官方的双指针好妙。
 *
 *
 * @author rzet
 * @date 2021/10/22 17:53
 */
public class T160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        HashMap<ListNode, Integer> hashMapA = new HashMap<ListNode, Integer>();
        HashMap<ListNode, Integer> hashMapB = new HashMap<ListNode, Integer>();

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != null || pB != null){

            if (pA != null){
                hashMapA.put(pA, 1);
                if (hashMapB.containsKey(pA))
                    return pA;
                pA = pA.next;
            }
            if (pB != null){
                hashMapB.put(pB, 1);
                if (hashMapA.containsKey(pB))
                    return pB;
                pB = pB.next;
            }
        }
        return null;
    }

}
