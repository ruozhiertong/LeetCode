import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * 706. 设计哈希映射
 *
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashmap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author rzet
 * @date 2021/10/18 11:49
 */
public class T706 {

    public LinkedList<Node>[] data;
    public static final int BASE = 857; //使用质数作为取模，能较好均衡

    public T706() {

        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++){
            data[i] = new LinkedList<Node>();
        }



    }

    public void put(int key, int value) {
        int idx  = hash(key);
        for (Node e: data[idx]){
            if (e.key == key){
                e.value = value;
                return;
            }
        }
        data[idx].addLast(new Node(key,value));

    }

    public int get(int key) {
        int idx  = hash(key);
        for (Node  e: data[idx]){
            if (e.key == key)
                return e.value;
        }
        return -1;
    }

    public void remove(int key) {
        int idx  = hash(key);
        boolean flag = false;
        Node n = null;
        for (Node e: data[idx]){
            if (e.key == key){
                flag = true;
                n = e;
            }
        }
        if (flag)
            data[idx].remove(n);

    }

    int hash(int key){
        return  key%BASE;
    }
}

class Node{
    public int key;
    public int value;

    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}
