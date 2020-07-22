package com.wyb.Algorithm;

import java.sql.Time;
import java.util.Arrays;
import java.util.Random;

public class DataChecker {
    static int[] generateRandomArray(){
        Random r = new Random();
        int[] arr = new int[10000];

        for (int i = 0; i < arr.length -1; i++) {
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    static void check(){
        boolean same = true;
        for (int times = 0; times < 1000; times++) {
            int[] arr = generateRandomArray();
            int[] arr2 = new int[arr.length];
            System.arraycopy(arr,0,arr2,0,arr.length);
            Arrays.sort(arr);
            // SelectionSort.sort(arr2);
            SelectionSort.sortAdvance(arr2);

            for (int i = 0; i < arr2.length; i++) {
                if (arr[i] != arr2[i] ) {same = false;}
            }
        }
        System.out.println(same == true ? "right":"wrong");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        check();
        long end = System.currentTimeMillis();
        double times = (end - start )/1000.0;
        System.out.println("Times: "+times +" s");
        // Times: 35.334 s


    }

}
