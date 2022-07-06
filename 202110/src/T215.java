/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *
 * 思路： 排序。  大顶堆／或 优先队列(堆实现的优先队列，java中是PriorityQueue)。 不过这个是不是复杂度过高。
 *  官方居然也是这两种思路。 哈哈哈。
 *
 *
 *
 * 快排：https://zhuanlan.zhihu.com/p/123416868
 * 递归的总能转换成迭代。利用栈。比如上面连接中将区间作为栈进行迭代。
 *
 * void Partition(int numbers[],int low, int high,int *pos)
 * {
 *     if (low>=high)
 *     {
 *         *pos = low;
 *         return;
 *     }
 *
 *     int start = low;
 *     int flag = numbers[start];
 *     low++;
 *     int temp;
 *
 *     while (1)
 *     {
 *         while(numbers[low]<flag)
 *         {
 *             low++;
 *         }
 *         while(numbers[high]>flag) {
 *             high--;
 *         }
 *         if (low >= high) {
 *             break;
 *         }
 *         temp = numbers[low];
 *         numbers[low] = numbers[high];
 *         numbers[high] = temp;
 *         low++;
 *         high--;
 *     }
 *
 *     temp = numbers[high];
 *     numbers[high]= numbers[start];
 *     numbers[start] = temp;
 *
 *     *pos = high;
 *
 * }
 *
 * //改进版本，可以随机的选择flag。 这个是有bug的。 应该找到flag 后，交换到start或end。
 * //否则有bug， 1 2 6 4 3 6 8 7 7 3， 如果是6 那么就走不下去了。
 * //其次，如果像下面java那样，也会有bug，会出现死循环现象。
 * //所以 正确的写法，应该将pivot预留到start或end， 最后进行swap。 这样逻辑也更顺畅。
 * void Partition2(int numbers[],int low, int high,int *pos)
 * {
 *     if (low >= high)
 *     {
 *         *pos = low;
 *         return;
 *     }
 *     int index = (rand()%(high - low + 1)) + low;
 *     int flag = numbers[index]; //随机的选择flag
 *
 *     //while(low < high)
 *     while(1)
 *     {
 *         while (numbers[low] < flag)
 *         {
 *             low++;
 *         }
 *         while (numbers[high] > flag)
 *         {
 *             high--;
 *         }
 *
 *         if (low>=high)
 *         {
 *             break;
 *         }
 *
 *         int temp = numbers[low];
 *         numbers[low] = numbers[high];
 *         numbers[high] = temp;
 *         low++;
 *         high--;
 *     }
 *     *pos = high;
 *
 * }
 *
 * public <T extends Comparable<T>> void quickSort(T[] nums, int low, int high,boolean aes){
 *         if (low >= high)
 *             return;
 *         int idx = low + (int) (Math.random()*(high - low + 1) ); // [0,1)
 *         System.out.println(low +"," + high + "," + idx);
 *         T pivot = nums[idx];
 *         int i = low;
 *         int j = high;
 *
 *         //前 pivot 后。  找到排好序后pivot所在的idx。
 *         while (true){
 *             if (aes){
 *                 while(i<= high && nums[i].compareTo(pivot) <= 0)
 *                     i++;
 *                 while (j >=low && nums[j].compareTo(pivot) >0)
 *                     j--;
 *             }else {
 *                 while(i<=high && nums[i].compareTo(pivot) >= 0)
 *                     i++;
 *                 while (j>= low && nums[j].compareTo(pivot) < 0)
 *                     j--;
 *             }
 *
 *             if (i >= j)
 *                 break;
 *             T tmp = nums[i];
 *             nums[i] = nums[j];
 *             nums[j] = tmp;
 *         }
 *         quickSort(nums, low, i, false);
 *         quickSort(nums, i + 1 ,high, false);
 *     }
 *
 *
 *
 *
 * @author rzet
 * @date 2021/10/28 16:36
 */
public class T215 {
    public int findKthLargest(int[] nums, int k) {

        Integer []nums2 = new Integer[nums.length];
        for (int i = 0; i < nums2.length; i++){
            nums2[i] = nums[i];
        }
        quickSort(nums2, 0, nums.length - 1, false); //降序
        return nums2[k -1];
    }

    public <T extends Comparable<T>>  void swap(T nums[], int i, int j){
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length || i == j)
            return;
        T tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public <T extends Comparable<T>> void quickSort(T[] nums, int low, int high,boolean aes){
        if (low >= high)
            return;
        int idx = low + (int) (Math.random()*(high - low + 1) ); // [0,1)
        System.out.println(low +"," + high + "," + idx);
        T pivot = nums[idx];
        swap(nums, low, idx);

        int i = low + 1;
        int j = high;

        //前 pivot 后。  找到排好序后pivot所在的idx。
        while (true){
            if (aes){
                while(i<= high && nums[i].compareTo(pivot) <= 0)
                    i++;
                while (j >=low && nums[j].compareTo(pivot) >0)
                    j--;
            }else {
                while(i<=high && nums[i].compareTo(pivot) >= 0)
                    i++;
                while (j>= low && nums[j].compareTo(pivot) < 0)
                    j--;
            }

            if (i >= j)
                break;
            T tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        //别忘了最后一步的交换。
        swap(nums, low, i - 1); // i -1 为 pivot的idx。

        quickSort(nums, low, i - 2, false);
        quickSort(nums, i ,high, false);
    }

    public static void main(String[] args) {
        new T215().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4);
    }

    //堆的操作。
    //关键在调整。调整：前提是存在的数据是堆了。 在满足堆结构的数据中添加数据，调整使其继续满足对结构。添加位置，1。根，要shiftdown。 2。最后节点，要shiftup。
    //建堆。 每次加入到最后一个节点， 然后以此进行shiftup调整。
    //添加。 往尾巴添加。 然后做shiftup调整。
    //删除。 每次删除根，最后节点加入到根。 然后做shiftdown调整。

    //要对一个已经存在的数据进行调整成堆，1。可以从最后一个非叶子节点遍历到根节点，遍历每个节点做shiftdown调整。
    // （也可以从根节点遍历到最后一个非叶子节点，遍历每个节点做shiftup调整。）
    // 2。按照每次加入到最后节点，按建堆 处理。 做shiftup调整。

    /**
     * public int findKthLargest(int[] nums, int k) {
     *         int heapSize = nums.length;
     *         buildMaxHeap(nums, heapSize);
     *         for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
     *             swap(nums, 0, i);
     *             --heapSize;
     *             maxHeapify(nums, 0, heapSize);
     *         }
     *         return nums[0];
     *     }
     *
     *     public void buildMaxHeap(int[] a, int heapSize) {
     *         for (int i = heapSize / 2; i >= 0; --i) {
     *             maxHeapify(a, i, heapSize); //相当于shiftdown操作的。
     *         }
     *     }
     *
     *     public void maxHeapify(int[] a, int i, int heapSize) {
     *         int l = i * 2 + 1, r = i * 2 + 2, largest = i;
     *         if (l < heapSize && a[l] > a[largest]) {
     *             largest = l;
     *         }
     *         if (r < heapSize && a[r] > a[largest]) {
     *             largest = r;
     *         }
     *         if (largest != i) {
     *             swap(a, i, largest);
     *             maxHeapify(a, largest, heapSize);
     *         }
     *     }
     *
     *     public void swap(int[] a, int i, int j) {
     *         int temp = a[i];
     *         a[i] = a[j];
     *         a[j] = temp;
     *     }
     *
     *
     *
     */

}
