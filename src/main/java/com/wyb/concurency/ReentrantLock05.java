package com.wyb.concurency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock05 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示公平锁
    @Override
    public void run(){
        for (int i=0;i<10;i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock05 rl = new ReentrantLock05();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        th1.start();
        th2.start();
    }

}
