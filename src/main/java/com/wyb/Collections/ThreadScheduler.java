package com.wyb.Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ThreadScheduler {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random()*100));
        }

        PriorityQueue<Integer> threadQueue = new PriorityQueue<>();
        threadQueue.addAll(list);
        System.out.println("Waiting theads...");
        for (Integer thread : threadQueue) {
            System.out.print(thread + ",");
        }
        System.out.println("\nDeploying threads...");
        while (!threadQueue.isEmpty()){
            System.out.print(threadQueue.remove() + ",");
        }
    }
}
