import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 *
 * 1801. 积压订单中的订单总数
 * 给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 笔类型为 orderTypei 、价格为 pricei 的订单。
 *
 * 订单类型 orderTypei 可以分为两种：
 *
 * 0 表示这是一批采购订单 buy
 * 1 表示这是一批销售订单 sell
 * 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。
 *
 * 存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
 *
 * 如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。
 * 反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。
 * 输入所有订单后，返回积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 109 + 7 取余的结果。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * 输出：6
 * 解释：输入订单后会发生下述情况：
 * - 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
 * - 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
 * - 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
 * - 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
 * 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
 *
 *
 *
 *
 * 思路： 按照题意做。
 *
 *
 *
 * @author rzet
 * @date 2023/1/2 00:20
 */
public class D2T1801 {




    //排序。
    public void insertBacklog(LinkedList<int[]> l, int[] arr){

        int i = 0;
        for(i = 0; i < l.size(); i++){
            if (l.get(i)[0] < arr[0])
                break;
        }
        l.add(i, arr);
    }

    //复杂度高了。 使用堆。
    public int getNumberOfBacklogOrders(int[][] orders) {
        int n = orders.length;
        //大到小排列
        LinkedList<int[]> sellBacklog = new LinkedList<int[]>();
        LinkedList<int[]> buyBacklog = new LinkedList<int[]>();

        //check sellbacklog and buybacklog
        //buy's price >= sell's price
        for (int i = 0; i < n; i++) {
            //buy. 则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单
            if (orders[i][2] == 0) {
                int num = orders[i][1];

                while (num > 0 && sellBacklog.size() > 0 ) {

                    int[] minSell = sellBacklog.get(sellBacklog.size() - 1);


                    if (minSell[0] > orders[i][0])
                        break;

                    num -= minSell[1];
                    if (num > 0){
                        sellBacklog.remove(sellBacklog.size() - 1);
                    }else{
                        minSell[1] = -num;
                    }
                }
                if (num > 0) {
                    orders[i][1] = num;
                    insertBacklog(buyBacklog, orders[i]);
                }

            }
            //sell, 则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当
            if (orders[i][2] == 1) {
                int num = orders[i][1];

                while (num > 0 && buyBacklog.size() > 0) {

                    int[] maxBuy = buyBacklog.get(0);
                    if (maxBuy[0] < orders[i][0])
                        break;

                    num -= maxBuy[1];
                    if (num > 0) {
                        buyBacklog.remove(0);
                    } else {
                        maxBuy[1] = -num;
                    }
                }
                if (num > 0) {
                    orders[i][1] = num;
                    insertBacklog(sellBacklog, orders[i]);
                }
            }
        }

        int sum = 0;
        final int MOD = 1000000007;

        for (int[] b: buyBacklog)
            sum = (sum + b[1]) % MOD;

        for (int[] s: sellBacklog)
            sum = (sum + s[1]) % MOD;

        return sum;

    }



    public int getNumberOfBacklogOrders0(int[][] orders) {
        int n = orders.length;

        //大顶堆
        PriorityQueue<int[]> buyOrders = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);

        //小顶堆
        PriorityQueue<int[]> sellOrders = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

        //check sellbacklog and buybacklog
        //buy's price >= sell's price
        for (int i = 0; i < n; i++) {
            //buy. 则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单
            if (orders[i][2] == 0) {
                int num = orders[i][1];

                while (num > 0 && sellOrders.size() > 0 ) {

                    int[] minSell = sellOrders.peek();


                    if (minSell[0] > orders[i][0])
                        break;

                    num -= minSell[1];
                    if (num > 0){
                        sellOrders.remove();
                    }else{
                        minSell[1] = -num;
                    }
                }
                if (num > 0) {
                    orders[i][1] = num;
                    buyOrders.add(orders[i]);
                }

            }
            //sell, 则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当
            if (orders[i][2] == 1) {
                int num = orders[i][1];

                while (num > 0 && buyOrders.size() > 0) {

                    int[] maxBuy = buyOrders.peek();
                    if (maxBuy[0] < orders[i][0])
                        break;

                    num -= maxBuy[1];
                    if (num > 0) {
                        buyOrders.remove();
                    } else {
                        maxBuy[1] = -num;
                    }
                }
                if (num > 0) {
                    orders[i][1] = num;
                    sellOrders.add(orders[i]);
                }
            }
        }

        int sum = 0;
        final int MOD = 1000000007;

        for (int[] b: buyOrders)
            sum = (sum + b[1]) % MOD;

        for (int[] s: sellOrders)
            sum = (sum + s[1]) % MOD;

        return sum;

    }

    public static void main(String[] args) {

//        int [][]orders = new int[][] {{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}};
    int [][]orders = new int [][] {{944925198,885003661,0},{852263791,981056352,0},{16300530,415829909,0},{940927944,713835606,0},{606389372,407474168,1},{139563740,85382287,1},{700244880,901922025,1},{972900669,15506445,0},{576578542,65339074,0},{45972021,293765308,0},{464403992,97750995,0},{29659852,536508041,0},{799523481,299864737,0},{711908211,480514887,1},{354125407,677598767,1},{279004011,688916331,0},{263524013,64622178,0},{375395974,460070320,0},{971786816,379275200,1},{577774472,214070125,1},{987757349,711231195,0}};
        System.out.println(new D2T1801().getNumberOfBacklogOrders(orders));
    }
}
