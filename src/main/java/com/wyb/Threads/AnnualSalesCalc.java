package com.wyb.Threads;

import java.text.DateFormatSymbols;
import java.util.*;
import java.util.concurrent.*;

/**
 *  use Callable/Future
 */
public class AnnualSalesCalc {
    private static int NUMBER_OF_CUSTOMERS = 100;
    private static int NUMBER_OF_MONTHS = 12;
    private static int salesMatrix[][];

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        generateMatrix();
        printMatrix();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Set<Future<Integer>> set = new HashSet<Future<Integer>>();
        for (int row = 0; row < NUMBER_OF_CUSTOMERS; row++) {
            Callable<Integer> callable = new Summer(row);
            Future<Integer> future = executor.submit(callable);
            set.add(future);
        }
        int sum = 0;
        for (Future<Integer> future : set) {
            sum += future.get();
        }
        System.out.printf("%nThe annual turnover (bags): %s%n%n", sum);
        executor.shutdown();
    }

    private static class Summer implements Callable {
        private int companyID;

        public Summer(int companyID) {
            this.companyID = companyID;
        }


        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < NUMBER_OF_MONTHS; i++) {
                sum += salesMatrix[companyID][i];
            }
            System.out.printf("Totaling for client 1%02d completed%n", companyID);
            return sum;
        }
    }


    private static void generateMatrix(){
        salesMatrix = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_MONTHS];

        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
                salesMatrix[i][j] = (int)(Math.random() * 100);
            }
        }
    }

    private static void printMatrix(){
        System.out.print("\t\t");
        String[] monthDisplayNames = (new DateFormatSymbols()).getShortMonths();
        for (String strName : monthDisplayNames) {
            System.out.printf("%8s", strName);
        }
        System.out.printf("%n%n");
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            System.out.printf("Client ID: 1%02d", i);
            for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
                System.out.printf("%8d", salesMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.printf("%n%n");
    }
}
