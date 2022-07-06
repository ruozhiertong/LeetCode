import com.sun.java.swing.action.NextAction;

import java.util.List;
import java.util.Stack;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 *
 *
 * 思路： 太简单了。 草率了。
 *
 * 1。使用hashMap。hashMap中只出现1个的节点。
 *
 * 2。使用三指针。 pre p  next 只有 p不等与前后两个节点时加入。
 *
 * 3。使用start end cur pre 指针，来遍历。 start指向cur不等与pre的指针。 end 指向上一个的未重复的。O（n）
 *
 * @author rzet
 * @date 2021/10/22 18:10
 */
public class T82 {

    public ListNode deleteDuplicates(ListNode head) {

        ListNode result = null;
        ListNode pResult = null;
        ListNode pre = null;
        ListNode p = head;
        //ListNode next = null; 不太需要的。

        while (p != null){
            if (pre == null){ //第一个节点
                if (p.next == null || p.val != p.next.val){
                    result = pResult = p;
                }
            }else{
                if (p.next == null){
                    if(p.val != pre.val){
                        if (pResult == null){
                            result = pResult = p;
                        }else{
                            pResult.next = p;
                            pResult = p;
                        }
                    }
                }else {
                    if (p.val != pre.val && p.val != p.next.val){
                        if (pResult == null){
                            result = pResult = p;
                        }else{
                            pResult.next = p;
                            pResult = p;
                        }
                    }
                }

            }

            pre = p;
            p = p.next;
        }
        if (pResult != null) pResult.next = null;
        return result;
    }
}
