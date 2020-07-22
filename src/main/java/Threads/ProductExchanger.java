package Threads;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Exchanger;

public class ProductExchanger {
    public static Exchanger<List<Integer>> exchanger = new Exchanger();

    public static void main(String[] args) {
        Thread pruducer = new Thread(new Producers());
        Thread comsumer = new Thread(new Comsumers());
        pruducer.start();
        comsumer.start();

        try {
            while (System.in.read() != '\n') ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        pruducer.interrupt();
        comsumer.interrupt();
    }
}

class Producers implements Runnable {

    private static List<Integer> buffer = new ArrayList<Integer>();
    private boolean okToRun = true;
    private final int BUFFERSIZE = 10;

    @Override
    public void run() {
        int j = 0;
        while (okToRun) {
            if (buffer.isEmpty()) {
                try {
                    for (int i = 0; i < BUFFERSIZE; i++) {
                        buffer.add((int) (Math.random() * 100));
                    }
                    Thread.sleep((int) (Math.random() * 1000));
                    System.out.println("Producer Buffer: ");
                    for (Integer i : buffer) {
                        System.out.print(i + ", ");
                    }
                    System.out.println();
                    System.out.println("Exchanging ...");
                    buffer = ProductExchanger.exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    okToRun = false;
                }
            }
        }
    }
}


class Comsumers implements Runnable {

    private static List<Integer> buffer = new ArrayList<Integer>();
    private boolean okToRun = true;

    @Override
    public void run() {
        while (okToRun) {
            if (buffer.isEmpty()) {
                try {
                    buffer = ProductExchanger.exchanger.exchange(buffer);
                    System.out.println("Comsumer Buffer: ");
                    for (Integer i : buffer) {
                        System.out.print(i + ", ");
                    }
                    System.out.println("\n");
                    Thread.sleep((int) (Math.random() * 1000));
                    buffer.clear();
                } catch (InterruptedException e) {
                    okToRun = false;
                }
            }
        }
    }
}