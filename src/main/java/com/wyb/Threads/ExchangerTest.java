package com.wyb.Threads;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s = "hello";
            System.out.println(Thread.currentThread().getName() + ": " + s);
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + s);
        },"A").start();


        new Thread(()->{
            String t = "world";
            System.out.println(Thread.currentThread().getName() + ": " + t);
            try {
                t = exchanger.exchange(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ ": " + t);
        },"B").start();

    }
}
