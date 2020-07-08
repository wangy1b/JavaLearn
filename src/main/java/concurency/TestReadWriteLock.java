package concurency;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;

    // ReadWriteLock
    // read 为共享锁
    // write 为排他锁
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
            //模拟读操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
            //模拟写操作
//            System.out.println("value : "+value);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ////下面的程序应该花20s
        //此时读和写都是排他锁
//        Runnable readR = ()-> read(lock);
//        Runnable writeR = ()-> write(lock, new Random().nextInt());


        //下面的程序应该花3s
        //读的时候是共享锁
        Runnable readR = () -> read(readLock);
        //写的时候是排他
        Runnable writeR = () -> write(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }

    }


}
