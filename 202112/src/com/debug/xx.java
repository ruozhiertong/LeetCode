package com.debug;

import com.test.lxr;

/**
 * @author rzet
 * @date 2021/12/12 16:35
 */
public class xx extends lxr {

    public int print(){
        System.out.println(age);
        new lxr().age;
        return 0;
    }

    public static void main(String[] args) {
        new xx().print();
        new lxr().age;
    }
}
