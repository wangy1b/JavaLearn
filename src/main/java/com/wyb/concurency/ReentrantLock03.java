package com.wyb.concurency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock03 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
    *  使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
    *  可以根据tryLock的返回值来判定是否锁定
    *  也可以根据tryLock的时间，由于tryLock抛出异常，所以要注意unlock
    * */
    void m2() {

        Boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) {lock.unlock();}
        }
    }


    public static void main(String[] args) {
        ReentrantLock03 rl = new ReentrantLock03();
        new Thread(rl::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }

}
