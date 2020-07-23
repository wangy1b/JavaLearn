package com.wyb.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T05_CountDownLatchWithOutSleep {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatchWithOutSleep c = new T05_CountDownLatchWithOutSleep();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 启动");

            if (c.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println("t2 结束");
            //通知t1继续执行
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 启动");

            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);


                if (c.size() == 5) {
                    //打开门闩，让t2执行
                    latch.countDown();
                }


                //下面这段注释后就无法执行了。可以采用两个latch来完成
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();


    }
}
