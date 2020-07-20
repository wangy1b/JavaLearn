package Threads;

import java.util.concurrent.locks.ReentrantLock;

public class ModifiedBucketBallGame {
    private int bucket[] = {10000, 10000};
    private static boolean RIGHT_TO_LEFT;
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        new ModifiedBucketBallGame().doTransfers();
    }
    private void doTransfers() {
        for (int i = 0; i < 10; i++) {
            new Thread(new TransferThread(!RIGHT_TO_LEFT)).start();
            new Thread(new TransferThread(RIGHT_TO_LEFT)).start();
        }
    }
    public void transfer(boolean direction, int numToTransfer) {
        lock.lock();
        if (direction == RIGHT_TO_LEFT) {
            bucket[0] += numToTransfer;
            bucket[1] -= numToTransfer;
        } else {
            bucket[0] -= numToTransfer;
            bucket[1] += numToTransfer;
        }
        System.out.println("Total: " + (bucket[0] + bucket[1]));
        lock.unlock();
    }
    private class TransferThread implements Runnable {

        private boolean direction;
        public TransferThread(boolean direction) {
            this.direction = direction;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                transfer(direction, (int) (Math.random() * 2000));
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
