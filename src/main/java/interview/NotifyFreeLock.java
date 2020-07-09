package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotifyFreeLock {

    volatile List lists = new ArrayList();

    public void add(Object o) { lists.add(o);}

    public int size() {return lists.size();}

    public static void main(String[] args) {
        NotifyFreeLock notifyFreeLock = new NotifyFreeLock();

        final Object lock = new Object();

        new Thread(()->{
            synchronized (lock) {
                System.out.println("t2 启动");

                if (notifyFreeLock.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e ){
                        e.printStackTrace();
                    }
                }

                System.out.println("t2 结束");
                //通知t1继续执行
                lock.notify();
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
                    notifyFreeLock.add(new Object());
                    System.out.println("add " + i);
                    if (notifyFreeLock.size() == 5) {
                        lock.notify();//notify 不是放锁
                        //通过t1来wait来释放锁，让t2能得以执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
