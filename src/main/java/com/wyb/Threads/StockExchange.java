package com.wyb.Threads;

import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.util.concurrent.*;

public class StockExchange {
    public static void main(String[] args) {
        System.out.printf("Hit Enter to terminate%n%n");
        BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<Integer>(5);
        Seller seller = new Seller(orderQueue);
        Thread[] sellerThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            sellerThreads[i] = new Thread(seller);
            sellerThreads[i].start();
        }
        Buyer buyer = new Buyer(orderQueue);
        Thread[] buyerThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            buyerThreads[i] = new Thread(buyer);
            try {
//                TimeUnit.SECONDS.sleep(10);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buyerThreads[i].start();
        }

        try {
            while (System.in.read() != '\n'){};
        } catch (IOException ex){
        }
        System.out.println("terminating");
        for (Thread sellerThread : sellerThreads) {
            sellerThread.interrupt();
        }
        for (Thread buyerThread : buyerThreads) {
            buyerThread.interrupt();
        }

    }
}

class Seller implements Runnable{
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private static int id;

    public Seller(BlockingQueue orderQueue){
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (shutdownRequest == false){
            int quantity = (int) (Math.random()*100);
            try {
                orderQueue.put(quantity);
                System.out.println("Sell order by "
                + Thread.currentThread().getName()
                + ": " + quantity);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                shutdownRequest = true;
            }
        }
    }
}

class Buyer implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;

    public Buyer(BlockingQueue orderQueue){
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (shutdownRequest == false) {
            try {
                int quantity =(int) orderQueue.take();
                System.out.println("Buy order by "
                        + Thread.currentThread().getName()
                        + ":" + quantity);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                shutdownRequest = true;
            }
        }

    }
}