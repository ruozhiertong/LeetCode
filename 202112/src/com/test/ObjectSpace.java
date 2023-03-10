package com.test;

import org.openjdk.jol.info.ClassLayout;

import java.util.Arrays;

/**
 * @author rzet
 * @date 2022/8/3 19:35
 * 各数据类型 占的字节长度。 （sizeof）
 * byte boolean 1byte
 * char short   2byte
 * int float    4byte
 * double long  8byte
 * reference  4byte(机器字为32位或者64位开启压缩)， 8byte （机器字为64位且没有开启指针压缩）
 *
 *
 * 实例对象的占的字节长度，不定。 一个对象的内存中占的字节长度=对象头长度+实例数据长度+对齐字节。
 *      对象头的长度： 2个机器字长，如果是数组3个机器字长。  32位系统，机器字长4byte， 64位，8byte。
 *      所以是 8byte(/16byte), 12byte(/24byte)。 如果jvm开启指针压缩， 则为12byte， 16byte。
 *
 * -XX:-UseCompressedOops 关闭指针压缩。  默认是开启的。
 *
 *
 * 对象：对象头(markword+类型指针+数组长度[可选]) + 实例数据 + 对齐部分。
 * 对齐规则： （整个对象8字节对齐。 整体内部中实例数据的各个元素各自字节长度对齐。）
 * 1.整个对象要8字节对齐。（对象头最后的填充位要使得8对齐。bytes external）
 *      任何对象都是8字节对齐，属性按照[long,double]、[int,float]、[char,short]、[byte,boolean]、[reference,数组] 的顺序存放.
 *      有可能让小的数据先填充到前面让其满足对齐，不过reference肯定在最后。比如：
                private ObjectSpace os;
                private long lt = 0L;
                private int count = 0;
        会先int 再 long， 再os。
 * 2.每种类型要和自身所占的字节长度对齐。比如int 类型起始位要4字节对齐， double的要8字节对齐。 （内部对齐 bytes internal）
 *
 */
public class ObjectSpace {
    private ObjectSpace mos;
    private long lt = 0L;
    private int count = 0;
    private int[] arr = new int[3]; //arr 也是引用类型。同mos。
//    private int count2= 0;
//    private int count3 = 0;
//    private boolean flag;
//    private char c;
//    private float fnum;

//    private byte b;

    public int getCount(){
        return  count;
    }
    public void setCount(int count){
        this.count = count;
    }

}

class ObjectSizeAnalysis{
    public static void main(String[] args) {
        ObjectSpace os = new ObjectSpace();
//        int[] os = new int[3]; //数组。
//        boolean b = true;
//        int a = 1;
        System.out.println(ClassLayout.parseInstance(os).toPrintable());

        System.out.println("Integer: " + Integer.SIZE/8);           // 4
        System.out.println("Short: " + Short.SIZE/8);               // 2
        System.out.println("Long: " + Long.SIZE/8);                 // 8
        System.out.println("Byte: " + Byte.SIZE/8);                 // 1
        System.out.println("Character: " + Character.SIZE/8);       // 2
        System.out.println("Float: " + Float.SIZE/8);               // 4
        System.out.println("Double: " + Double.SIZE/8);             // 8
//        System.out.println("Boolean: " + Boolean.Size/8);
//        System.out.println("Object: " + Object.Size/8);

    }
}
