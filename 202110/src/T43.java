import java.awt.font.NumericShaper;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 *218
 * 456
 *  912
 *  1368
 * 56088
 *
 * 思路： 大数相乘。用字符串模拟大数相乘。 模拟手动相乘。
 * @author rzet
 * @date 2021/10/21 12:03
 */
public class T43 {

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

    public String multiply(String num1, String num2) {
        String s = "0";
        int a;
        int b;

        if (num1.equals("0") || num2.equals("0"))
            return "0";

        for (int i = num1.length() - 1; i>= 0; i--){
            a = num1.charAt(i) - '0';
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            int mul = 0;
            for (int j = num2.length() -1; j >= 0; j--){
                b = num2.charAt(j) - '0';
                mul = a * b + carry;
                sb.append(mul%10);
                carry = mul/10;
            }
            if (carry != 0)
                sb.append(carry);
            sb.reverse();
            for (int k = 1; k < num1.length() - i; k++)
                sb.append(0);
            s = addStrings(s, sb.toString());
        }

        return s;
    }

    public static void main(String[] args) {
        new T43().multiply("123", "456");
    }
}
