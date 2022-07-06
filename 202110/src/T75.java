import org.w3c.dom.html.HTMLOptGroupElement;

import javax.xml.ws.Holder;
import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 *
 * 思路：1。排序。 虽然可以题解，不过 o(nlogn)，不是一次遍历。
 *      2。将1小的放前面，1的紧跟着放后面。 两趟。o(n).
 *      3。统计0 1 2 个数，然后改写原数组。 o(n)
 *      4。双指针。前后开搞。一趟。o(n).
 *      5。快排有点问题的。 使用双轴可以符合该题目的要求（其实就是双指针）。
 *
 *
 * 进阶：
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *
 *充分考虑这道题，因为只有 0 ， 1 ， 2的排序，因此可以直接遍历一遍进行排序。 将1小的放到低处，比1大的放到高处，剩下的自然是1。
 *有点像快排的一次排序，找到轴，大的放前，小的放后。要改造下。不过快排一次遍历可能还是不行，因为可能会成 1 1 0 1 2，它只保证其在轴的左边比它小，在轴的右边比它大，不能保证其左边就是排好序的。
 *这个告诉我们，有时候具体情况，可以特殊处理，尽量充分使用题干信息，从而让答案更优。
 *
 *
 *
 * @author rzet
 * @date 2021/10/15 10:46
 */
public class T75 {

    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }



    //快排的一趟。 有点问题，不行的。
    public void sortColors2(int[] nums) {
        int flag = 1;
        int lowIdx = 0;
        int highIdx = nums.length -1;
        while (lowIdx < highIdx){
            while(nums[lowIdx] < flag)
                lowIdx++;

            while(nums[highIdx] > flag)
                highIdx--;

            //swap
            int tmp = nums[lowIdx];
            nums[lowIdx] = nums[highIdx];
            nums[highIdx] = tmp;

            lowIdx++;
            highIdx--;
        }
    }


    public void sortColors3(int nums[]){

        int num0 = 0, num1 = 0, num2 =0;

        for (int e: nums){
            if(e == 0)
                num0++;
            else if (e == 1)
                num1++;
            else
                num2++;
        }
        int idx =0 ;
        for(int i = 0; i < num0; i++)
            nums[idx++] = 0;

        for(int i = 0; i < num1; i++)
            nums[idx++] = 1;

        for(int i = 0; i < num2; i++)
            nums[idx++] = 2;

    }


    public void sortColors4(int nums[]){
        int mvIdx = 0;
        int tmp = 0;
        //0全部排前面
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 1){
                //swap
                tmp = nums[mvIdx];
                nums[mvIdx] = nums[i];
                nums[i] = tmp;
                mvIdx++;
            }
        }
        //1紧跟着后面
        for(int i = mvIdx; i < nums.length; i++){
            if(nums[i] == 1){
                //swap
                tmp = nums[mvIdx];
                nums[mvIdx] = nums[i];
                nums[i] = tmp;
                mvIdx++;
            }
        }

    }


    public void sortColors5(int nums[]) {
        int lowIdx = 0;
        int highIdx = nums.length - 1;
        int tmp = 0;
        int idx = 0;
        for (int i = 0; i <= highIdx; i++){
            if(nums[i] == 0){
                tmp = nums[lowIdx];
                nums[lowIdx] = nums[i];
                nums[i] = tmp;
                lowIdx++;
            }else if(nums[i] == 2){
                tmp = nums[highIdx];
                nums[highIdx] = nums[i];
                nums[i] = tmp;
                highIdx--;
                i--; //交换的要重新处理。
            }
        }
    }

}
