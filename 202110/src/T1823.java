import java.util.LinkedList;

/**
 * 1823. 找出游戏的获胜者
 * 共有 n 名小伙伴一起做游戏。小伙伴们围成一圈，按 顺时针顺序 从 1 到 n 编号。确切地说，从第 i 名小伙伴顺时针移动一位会到达第 (i+1) 名小伙伴的位置，其中 1 <= i < n ，从第 n 名小伙伴顺时针移动一位会回到第 1 名小伙伴的位置。
 *
 * 游戏遵循如下规则：
 *
 * 从第 1 名小伙伴所在位置 开始 。
 * 沿着顺时针方向数 k 名小伙伴，计数时需要 包含 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
 * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
 * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 顺时针下一位 小伙伴 开始，回到步骤 2 继续执行。
 * 否则，圈子中最后一名小伙伴赢得游戏。
 * 给你参与游戏的小伙伴总数 n ，和一个整数 k ，返回游戏的获胜者
 *
 * 约瑟夫环。
 *
 *
 *
 *
 * 思路：
 * 1.模拟 。 直接步长为1开始遍历 更方便。 比使用模方便， 模要判断是否移除等等，而且模对于链表就不行了。 （这题使用链表更方表）
 *
 *
 * 2.动态规划。 f(n,k). 搞清楚这个f() 的含义。
 * 我们用f(n, k)来表示从[0,n-1]这n个数里面挑出第k个剩余的结果，第一次被删除的位置是k-1，第二次我们要从n-1个数里面挑选k个，此时的推导公式是(f(n - 1,k) + k)。原因是f(n - 1,k)这个公式是从0开始找的，我们第二次起始的位置是从k开始，所以每次我们需要加上上次寻找完的偏移量。
 *https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/solution/dong-tai-gui-hua-li-jie-liao-hao-yi-hui-8qlag/
 *
 *
 * @author rzet
 * @date 2021/10/22 23:33
 */
public class T1823 {
    public int findTheWinner(int n, int k) {

        LNode list = null;
        LNode pList = null;
        for (int i = 1; i <= n; i++){
            LNode node = new LNode();
            node.val = i;
            if (list == null){
                list = pList = node;
            } else{
                pList.next = node;
                pList = node;
            }
        }
        LNode pre = pList; //尾巴。
        pList.next = list; //尾巴连到头部。
        //模拟计数。
        pList = list;
        int rmCount = 0;
        int c = 1;
        while (rmCount != n - 1){
            if ( c % k == 0){
                pre.next = pList.next;//删除
                rmCount++;
            }
            c++;
            pList = pList.next;

        }

        return pList.val;
    }

    class LNode{
        public int val;
        public LNode next;
        public LNode() {
            val = 0;
            next = null;
        }
    }
}
