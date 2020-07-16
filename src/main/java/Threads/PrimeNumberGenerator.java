package Threads;

public class PrimeNumberGenerator {
    public static void main(String[] args) {
        Thread primeNumberGenerator = new Thread(new WorkerThread());
        primeNumberGenerator.setDaemon(true);
        primeNumberGenerator.start();
        System.out.printf("Number of Active threads in the %s group equals %d%n"
                ,primeNumberGenerator.getThreadGroup().getName(),Thread.activeCount());

        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread t : threads) {
            System.out.printf("%s\tpriority:%d%n", t.getName(), t.getPriority());
        }

        try {
            System.out.println(Thread.currentThread().getName()+" start.");
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName()+" end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerThread implements Runnable {

    @Override
    public void run() {
        long i = 1;
        while (true) {
            long j;
            for (j = 2; j < i; j++) {
                long n = i % j;
                if(n ==0) {
                    break;
                }
            }
            if(i == j){
//                System.out.print(" " + i);
                System.out.println(Thread.currentThread().getName()+ " " + i);
            }
            i++;

        }
    }
}