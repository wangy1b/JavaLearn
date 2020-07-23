package com.wyb.Algorithm;

/**
 * 修改算法，用临时变量记录标记项，去掉swap方法？
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2, 10};
        sort(arr);
    }


    public static void sort(int[] arr){
        //抽出第i张牌
        for (int i = 1; i < arr.length ; i++) {
            // 把第i张牌放到前面去
            for (int j = i; j >0; j--) {
                // 只有前面的比它大就交换
                if(arr[j] < arr[j-1] ) {
                    swap(arr,j,j-1);
                }
            }
        }
        //System.out.println("排序结果为：");
        //printArr(arr);
    }

    public static void sortFix(int[] arr){
        //抽出第i张牌
        for (int i = 1; i < arr.length ; i++) {
            // 把第i张牌放到前面去
            for (int j = i; j >0; j--) {
                // 只有前面的比它大就交换
                if(arr[j] < arr[j-1] ) {
                    swap(arr,j,j-1);
                }
            }
        }
        //System.out.println("排序结果为：");
        //printArr(arr);
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
