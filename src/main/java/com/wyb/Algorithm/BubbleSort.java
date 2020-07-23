package com.wyb.Algorithm;

/**
 * 使得冒泡算法的最好时间复杂度优化为O(n)?
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2, 10};
        //mySort(arr);
        sort(arr);
    }


    public static void mySort(int[] arr){

        for (int i = 0; i < arr.length -1 ; i++) {
            for (int j = i + 1; j < arr.length -1   ; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        //System.out.println("排序结果为：");
        //printArr(arr);
    }

    public static void sort(int[] arr){

        //findMax(arr,8);
        for (int i = arr.length-1; i >0 ; i--) {
            findMax(arr,i);
        }
        System.out.println("排序结果为：");
        printArr(arr);
    }

    private static void findMax(int[] arr, int n){
        for (int j = 0; j < n ; j++) {
            if (arr[j] > arr[j+1]) {
                swap(arr, j, j+1);
            }
        }
    }
    private static void swap(int[] arr, int small, int big) {
        int tmp = arr[small];
        arr[small] = arr[big];
        arr[big] = tmp;
    }

    private static void printArr(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println();
    }

}


