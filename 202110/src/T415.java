/**
 *
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 *
 * 思路： 大数和。 用字符串来模拟大数和。 注意点是 进位的处理。
 *
 *
 * @author rzet
 * @date 2021/10/20 18:58
 */
public class T415 {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        int sum = 0;
        while(i >= 0 && j >= 0){
            sum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + carry;
            sb.append(sum%10);
            carry = sum/10;
            i--;
            j--;
        }
        while (i >= 0){
            sum = num1.charAt(i) - '0' + carry;
            sb.append(sum%10);
            carry = sum/10;
            i--;
        }

        while (j >= 0){
            sum = num2.charAt(j) - '0' + carry;
            sb.append(sum%10);
            carry = sum/10;
            j--;
        }

        if (carry == 1)
            sb.append(1);

        return sb.reverse().toString();


    }
}
