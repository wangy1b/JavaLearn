package com.wyb.interview;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T01_WithVolatile {
    volatile List lists = new LinkedList();
    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        T01_WithVolatile c = new T01_WithVolatile();

        new Thread(()->{
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+ i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t1").start();


    }
}
