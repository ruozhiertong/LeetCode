package com.test;

/**
 * @author rzet
 * @date 2022/7/18 13:27
 */
public class Child extends Parent{

    //写不写 Override，只要和父类同名，就是重写。
    @Override
    public void test(){
        System.out.println("Parent");
    }
}

interface IChild{

    boolean equals(Object o);
}
