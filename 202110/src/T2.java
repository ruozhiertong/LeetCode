/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 *
 * 示例 1：
 *
 *
 * @author rzet
 * @date 2021/10/22 16:40
 */

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


class ListNode{
    int val;
    ListNode next;
    public ListNode(){
        val = 0;
        this.next = null;
    }
    public ListNode(int val){
        this.val = val;
        this.next = null;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;this.next = next;
    }
}
public class T2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = null;

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode r = result;
        int carry = 0;
        while (p1 != null || p2 != null){
            ListNode n = new ListNode();
            int sum = 0;
            if (p1 != null)
                sum += p1.val;
            if (p2 != null)
                sum += p2.val;
            n.val = sum % 10;
            n.next = null;
            carry = sum / 10;
            if (r == null)
                r = n;
            else{
                r.next = n;
                r = n;
            }
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        if (carry != 0){
            ListNode n = new ListNode();
            n.val = carry;
            r.next = n;
        }
        return result;
    }
}
