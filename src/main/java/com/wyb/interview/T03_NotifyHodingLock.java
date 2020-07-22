package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T03_NotifyHodingLock {
    volatile List lists = new ArrayList();

    public void add(Object o) { lists.add(o);}

    public int size() {return lists.size();}

    public static void main(String[] args) {
        T03_NotifyHodingLock notifyHodingLock = new T03_NotifyHodingLock();

        final Object lock = new Object();

        new Thread(()->{
            synchronized (lock) {
                System.out.println("t2 启动");

                if (notifyHodingLock.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e ){
                        e.printStackTrace();
                    }
                }

                System.out.println("t2 结束");
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                notifyHodingLock.add(new Object());
                System.out.println("add " + i);
                if (notifyHodingLock.size() == 5) {
                    lock.notify();//notify 不是放锁
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        },"t1").start();




    }


}
