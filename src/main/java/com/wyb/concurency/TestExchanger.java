package com.wyb.concurency;

import java.util.concurrent.Exchanger;

public class TestExchanger {
    // Exchanger 交换器,容器
    // 第一个线程调用exchange方法时，把自己的值丢进容器，阻塞线程，
    // 等第二个线程也调用这个方法的时候，把自己的值丢进容器 和第一个线程交换
    // 游戏中两个人交换装备？
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
                //多长时间可以超时退出
//                s = exchanger.exchange(s,1000)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }
}
