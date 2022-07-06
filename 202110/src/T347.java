import java.text.FieldPosition;
import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 *
 * 思路： 朴素想法， hashmap进行计数，然后根据其个数排序。
 *
 *
 * @author rzet
 * @date 2021/10/29 18:38
 */
public class T347 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++){
            int count = hashMap.getOrDefault(nums[i], 0);
            hashMap.put(nums[i], count + 1);
        }

        //Map.Entry<Integer, Integer>[] arr = (Map.Entry<Integer, Integer>[]) (hashMap.entrySet().toArray(Map.Entry<Integer,Integer>[0]));

        //https://www.cnblogs.com/MrJR/p/10463479.html
        Map.Entry<Integer, Integer>[] arr;
        arr = (Map.Entry<Integer, Integer>[]) (hashMap.entrySet().toArray(new Map.Entry[0]));


        Arrays.sort(arr, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        int result[] = new int[k];
        int count = 0;
        while(count < k){
            result[count] = arr[arr.length - 1- count].getKey();
            count++;
        }
        return  result;

    }

    public static void main(String[] args) {
        //new T347().topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        test();
    }



    public static void test(){

        //ArrayList []arr = 和 ArrayList<String> []arr 是不同的数组。 前者是泛型的原生类型。后者是ArrayList<String> 类型。
        ArrayList []arr = new ArrayList[10];


        ArrayList<String> e1 = new ArrayList<String>();
        e1.add("hello");
        arr[0] = e1;

        ArrayList<Integer> e2 = new ArrayList<Integer>();
        e2.add(123);
        arr[1] = e2; //数组如果是指定ArrayList<String> []arr 就只能存String。 如果使用泛型的原生类，数组中可以存放不同类型（实现了数组中存放不同类型的数据）。 不过这个就要自己去掌握类型安全。

        ArrayList<Node> e3 = new ArrayList<Node>();

        e3.add(new Node(12,13));

        arr[2] = e3;


        for (ArrayList e: arr){
            System.out.println(e.toString());
        }

        //ArrayList<String> []strArr = new ArrayList<String>[10];


        //一般 ，数组是要检查元素类型，类型检查，类型要一致(或派生的多态)。 （运行时检查）
        //泛型会使得类型擦除。 （编译后都是擦除的）

        //既然泛型在编译后是擦除，而数组又是要类型一致，那泛型应该可以啊？
        //本来可以，但是这样会导致类型安全的问题，如果你不知道你每个元素是什么类型，那么可能会导致类型安全问题。
        //所以，为了避免这个问题，直接在编译器编译阶段对其做了限制，即直接对泛型数组编译不通过。
        //所以，这两者是矛盾的（一个类型要一致，一个可能类型就不是一致）。为了安全，编译器会在编译时使得泛型和数组不能放在一起。
        //其实如果能绕过编译时的检查，泛型和数组是可以放在一起的。 即都用泛型的原生类型做数组。 但是这个就要有自己去控制类型了。
        //即要想做泛型数组，使用泛型原生类，然后自己控制类型。

        //泛型：泛型类， 泛型方法。
        //<T> <T extends XXX>  <?>  <? extends XXX> <? super XXX>  (没有这个<T super XXX>)


    }
}
