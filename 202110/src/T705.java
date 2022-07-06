import com.sun.org.apache.regexp.internal.REUtil;

import java.util.LinkedList;

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * 实现 MyHashSet 类：
 *
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 *
 * 提示：
 *
 * 0 <= key <= 10^6
 * 最多调用 104 次 add、remove 和 contains
 *
 * 进阶：你可以不使用内建的哈希集合库解决此问题吗？
 *
 *
 *
 * 思路：重点是1。hash函数设计，2。冲突的处理。 ==》 数组+链表。
 *hash函数，使用取模运算。
 *
 *
 * @author rzet
 * @date 2021/10/18 11:16
 */
public class T705 {

    public LinkedList<Integer> [] data;
    public static final int BASE = 857;


    public T705() {

        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++){
            data[i] = new LinkedList<Integer>();
        }



    }

    public void add(int key) {
        int idx = hash(key);
        for (int e : data[idx]){
            if (e == key) //已经存在
                return;
        }
        data[idx].addLast(key);

    }

    public void remove(int key) {
        int idx = hash(key);
        boolean flag = false;
        for (int e : data[idx]){
            if (e == key) //已经存在
                flag = true;
        }

        if(flag)
            data[idx].remove(Integer.valueOf(key));//注意这里要装箱，否则是以索引删除。
    }

    public boolean contains(int key) {
        int idx = hash(key);
        for (int e : data[idx]){
            if (e == key)
                return true;
        }
        return false;
    }

    public int hash(int key){
        return key%BASE;
    }


    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<Integer>();
        l.add(1);
        l.add(1);
        for (int e: l)
            System.out.println(e);

    }
}
