package concurency;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        //Semaphore 信号灯 n个信号同时被取到的时候其他线程是取不到的
        // Semaphore最多时候允许多少个线程在进行，可以用来限流
        // 8条车道只有2个高速收费口
//        Semaphore semaphore = new Semaphore(2);
        // fair = true 时 新来的车不会插队
        // fair = false时 新来的车会插队
        Semaphore semaphore = new Semaphore(2,true);
        //允许一个线程同时执行
        //
        new Thread(() -> {
            try {
                semaphore.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
