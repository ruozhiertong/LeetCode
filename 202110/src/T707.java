import java.awt.*;
import java.util.LinkedList;

/**
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 * @author rzet
 * @date 2021/10/23 12:08
 */
public class T707 {
}



class MyLinkedList {

    //伪头。 tail是真tail，指向最后一个。
    ListNode head, tail;
    int size; //有这个设计 更方便。

    public MyLinkedList() {
        this.head = new ListNode();
        this.tail = null;
        size = 0;

    }

    public int get(int index) {
        ListNode pNode = head.next;
        if (this.size <= index)
            return -1;

        int count = 0;
        while(pNode != null && count <= index){
            if (count == index)
                return pNode.val;
            count++;
            pNode = pNode.next;
        }

        return -1;

    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = this.head.next;
        this.head.next = node;
        if (this.tail == null) //第一个
            this.tail = node;

        this.size++;

    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (this.tail == null){ //第一个
            this.tail = node;
            this.head.next = node;
        }else{
            this.tail.next = node;
            this.tail = node;
        }

        this.size++;

    }

    public void addAtIndex(int index, int val) {
        if (index > this.size)
            return;

        if (index <= 0)
            index = 0;
        ListNode pnode = this.head;
        for (int i = 0; i <= index - 1; i++){
            pnode = pnode.next;
        }
        ListNode node  = new ListNode(val);
        node.next = pnode.next;
        pnode.next = node;

        if (node.next == null) //最后一个
            this.tail = node;

        this.size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size)
            return;

        ListNode pnode = this.head;
        for (int i = 0; i <= index - 1; i++){
            pnode = pnode.next;
        }

        pnode.next = pnode.next.next;
        if (pnode.next == null) {//删除的是最后一个。
            if (pnode == head)
                this.tail = null;
            else
                this.tail = pnode;
        }
        this.size--;
    }


    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3


    }
}

