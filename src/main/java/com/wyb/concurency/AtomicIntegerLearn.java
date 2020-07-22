package concurency;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicIntegerLearn {

    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[1000];

        for (int i=0 ;i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for(int k=0;k<100000;k++){
                    count1.incrementAndGet();
                }
            });

        }


        long start = System.currentTimeMillis();

        for (Thread t : threads) { t.start();}
        for (Thread t : threads) { t.join();}

        long end = System.currentTimeMillis();
        System.out.println("Atomic:" + count1.get() + " time " + (end - start)/1000D +"s");

        Object o = new Object();
        for (int i=0 ;i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for(int k=0;k<100000;k++){
                    synchronized (o) {
                        count2++;
                    }
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread t : threads) { t.start();}
        for (Thread t : threads) { t.join();}
        end = System.currentTimeMillis();
        System.out.println("Sync:" + count2 + " time " + (end - start)/1000D +"s");


        for (int i=0 ;i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for(int k=0;k<100000;k++){
                    count3.increment();
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads) { t.start();}
        for (Thread t : threads) { t.join();}
        end = System.currentTimeMillis();
        System.out.println("LongAdder:" + count3+ " time " + (end - start)/1000D +"s");


    }

}
