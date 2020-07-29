package com.wyb.leetcode;

import java.util.Arrays;

public class RemoveDuplicates {
    public static void main(String[] args) {
        // int[] nums = new int[] {1,1,2};
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        // int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        // int[] nums = new int[] {0};
        // int[] nums = new int[] {};
        // System.out.println(removeDuplicates(nums));
        // System.out.println(removeDuplicatesOffical(nums));
        printArr(nums);

    }

    private static void printArr(int[] arr) {
        System.out.print("结果为:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 没做出来
    // private static int removeDuplicates(int[] arr){
    //     if (arr.length <= 1) return arr.length;
    //     int j = 1;
    //     for (int i = arr.length - 1; i > 0; i--) {
    //         if (arr[i] == arr[i-1]) {
    //             moveForward(arr,i);
    //             j++;
    //         }
    //     }
    //     printArr(arr);
    //     return j;
    // }

    private static int[] moveForward(int[] arr, int idx) {
        int temp = arr[idx];
        while (idx < arr.length -1) {
            arr[idx] = arr[idx+1];
            idx++;
        }
        arr[idx] = temp;
        return arr;
    }



    private static int removeDuplicatesOffical(int[] arr){
        if (arr.length <= 1) return arr.length;
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if(arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }
        printArr(arr);
        return i+1;
    }



}
