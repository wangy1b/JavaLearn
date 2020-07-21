package Threads;

import java.io.IOException;
import java.util.concurrent.*;

public class EnhancedStockExchange {
    public static void main(String[] args) {
        BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<Integer>();
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch stopLatch = new CountDownLatch(20);
        Seller1 seller = new Seller1(orderQueue, startLatch, stopLatch);
        Thread[] sellerThread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            sellerThread[i] = new Thread(seller);
            sellerThread[i].start();
        }
        Buyer1 buyer = new Buyer1(orderQueue, startLatch, stopLatch);
        Thread[] buyerThread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            buyerThread[i] = new Thread(buyer);
            buyerThread[i].start();
        }
        System.out.println("Go");
        startLatch.countDown();
        try {
            while (System.in.read() != '\n') ;
        } catch (IOException e) {
        }
        System.out.println("Terminating");
        for (Thread t : sellerThread) {
            t.interrupt();
        }
        for (Thread t : buyerThread) {
            t.interrupt();
        }
        try {
            stopLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("Closing down");

    }
}

class Seller1 implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private static int id;
    private CountDownLatch startLatch, stopLatch;

    public Seller1(BlockingQueue orderQueue, CountDownLatch startLatch, CountDownLatch stopLatch) {
        this.orderQueue = orderQueue;
        this.startLatch = startLatch;
        this.stopLatch = stopLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {}
        while (shutdownRequest == false) {
            Integer quantity = (int) (Math.random() * 100);
            try {
                orderQueue.put(quantity);
                System.out.println("Sell order producer # "
                        + Thread.currentThread().getName()
                        + ": " + quantity);
            } catch (InterruptedException e) {
                shutdownRequest = true;
            }
        }
        stopLatch.countDown();
    }
}


class Buyer1 implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private CountDownLatch startLatch, stopLatch;

    public Buyer1(BlockingQueue orderQueue, CountDownLatch startLatch, CountDownLatch stopLatch) {
        this.orderQueue = orderQueue;
        this.startLatch = startLatch;
        this.stopLatch = stopLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
        }
        while (shutdownRequest == false) {
            try {
                Integer quantity = (Integer) orderQueue.take();
                System.out.println("Buy order comsumer # "
                        + Thread.currentThread().getName()
                        + ": " + quantity);
            } catch (InterruptedException e) {
                shutdownRequest = true;
            }
        }
        stopLatch.countDown();
    }
}

