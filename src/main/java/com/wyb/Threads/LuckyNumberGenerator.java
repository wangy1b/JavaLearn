package com.wyb.Threads;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.*;

public class LuckyNumberGenerator {
    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<String>();
        Thread producer = new Thread(new Producer1(queue));
        producer.setDaemon(true);
        producer.start();

//        try {
//            System.out.println("producer sleep for 5 sec.");
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 10; i++) {
            Thread comsumer = new Thread(new Comsumer1(queue));
            comsumer.setDaemon(true);
            comsumer.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Producer1 implements Runnable {
    private final TransferQueue<String> queue;

    Producer1(TransferQueue<String> queue) {
        this.queue = queue;
    }

    private String produce() {
        return " your lucky number " + (new Random().nextInt(100));
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (queue.hasWaitingConsumer()) {
                    queue.transfer(produce());
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
        }

    }
}


class Comsumer1 implements Runnable {
    private final TransferQueue<String> queue;

    Comsumer1(TransferQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println(" comsumer "
                    + Thread.currentThread().getName() + queue.take());

        } catch (InterruptedException e) {
        }

    }
}
