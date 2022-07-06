import sun.rmi.rmic.iiop.IDLGenerator;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 *
 *
 * 思路：
 * 0。暴力。 像求质数（只能被1和自身整除）一样。  输出第n个。 isUglyNum 方法 判断是是否是丑数。
 * 肯定超时。
 *
 *
 *
 *
 *
 * 3.堆的方法。 每次往小顶堆中添加 堆顶元素x 的2x, 3x, 5x, 然后取出x，再调整成小顶堆。 依次 取出n次即可。
 *
 *
 *  1.动态规划？？？  动不起来。
 *  既然知道了堆添加的规则，那么dp就能很容易写出来了。
 *  dp[i]的值表示第i个的丑数。 关键在于大小顺序问题。 使用三指针（这个操作真妙！！）。
 *
 *  那么dp[i]
 *
 *
 *
 *
 * @author rzet
 * @date 2021/11/25 15:47
 */
public class T264 {


    public int nthUglyNumber2(int n) {

        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        Set<Long> set = new HashSet<Long>(); //用于去重。
        heap.add(1L);
        set.add(1L);
        int count = 0;
        long x = 0;

        while (count < n){

            x = heap.poll();
            if(!set.contains(2*x)){
                set.add(2*x);
                heap.add(2*x);
            }
            if(!set.contains(3*x)){
                set.add(3*x);
                heap.add(3*x);

            }
            if(!set.contains(5*x)){
                set.add(5*x);
                heap.add(5*x);
            }
            count++;

        }
        return (int)x;
    }



    public int nthUglyNumber(int n) {
        int c = 0;
        int r = 1;
        while (c == n){
            if (isUglyNum(r))
                c++;
            r++;
        }
        return r-1;
    }

//    public boolean isUglyNum(int n){
//        if (n == 0)
//            return false;
//        if (n == 1)
//            return true;
//       for (int i = 1; i < n; i++){
//           if (i ==1 || i == 2 || i == 3 || i == 5)
//               continue;;
//           if (n % i == 0)
//               return false;
//       }
//       return true;
//    }

    public boolean isUglyNum(int n){
        if (n == 0)
            return false;
        if (n == 1)
            return true;

        while(n % 2 == 0)
            n /= 2;

        while(n % 3 == 0)
            n /= 3;

        while(n % 5 == 0)
            n /= 5;

        if (n == 1)
            return true;
        else
            return false;
    }


}
