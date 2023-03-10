package com.test;

/**
 * @author rzet
 * @date 2022/7/29 12:00
 *
 * 将字节码 反汇编 成汇编。 javap。
 * javap -verbose TestJVM.class
 *
 *
 * 操作：局部边链表， 操作栈。
 *
 */
public class TestJVM {
    private Long count;
    public Long getCount(){
        return count;
    }
    public void incrementCount(){
        count++;
    }
    //i++, ++i.
    //https://blog.csdn.net/qq_39618369/article/details/109516380
    public static void main(String[] args) {
        int i = 0;
        i = i++;
        System.out.println(i);
    }
}
