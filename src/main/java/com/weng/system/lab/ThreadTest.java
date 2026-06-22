package com.weng.system.lab;

import java.io.File;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  yifeng
 * @ CreateDate    :  2019/9/24 11:28
 * @ Version       :  1.0
 */
public class ThreadTest {

    private static boolean aa = false;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while (!aa){
//                System.out.print("false");
                new File("");
            }
            System.out.println("true");
        }).start();
        Thread.sleep(3000);
        new Thread(() -> {
            aa = true;
        }).start();
    }
}
