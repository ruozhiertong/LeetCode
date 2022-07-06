import java.util.List;
import java.util.TreeMap;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 * 思路： 模拟。
 *
 * @author rzet
 * @date 2021/10/25 16:38
 */
public class T25 {


    public ListNode reverseKGroup(ListNode head, int k) {


        ListNode result = null;
        ListNode p = head;
        ListNode pStart = p; //反转的开始位置。
        ListNode preStart = null; //开始位置前的一个节点。

        int count = 1;
        while (p != null){

            if (count == k){

                ListNode kNext = p.next;
                p.next = null;
                ListNode n = new T206().reverseList(pStart);

                // 反转后要重新拼接。
                if (preStart == null)//开头
                    result = n;
                else
                    preStart.next = n; //接头

                pStart.next = kNext; //结尾。 因为反转后，pstart变成了结尾位置。

                preStart = pStart;
                pStart = kNext;
                p = kNext;
                count = 1;
                if (p == null) //更新p后 判断p是否为空
                    break;
            }

            p = p.next;
            count++;

        }

        return result;

    }
}
