import java.util.ArrayList;
import java.util.List;

/**
 * @author rzet
 * @date 2021/11/30 12:56
 */
public class MyTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);
    }
}


interface A{
    public void test();
    default void testB(){

    }
}

interface B{
    public void test();

    default void testB(){

    }
}

class C implements A, B{

    @Override
    public void test() {

    }


    //要明确重写，否则会出现二义性错误。
    @Override
    public void testB() {
        A.super.testB();
    }
}
