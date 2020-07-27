package com.wyb.Algorithm;

/** 桶排序一种
 *  问题：
 *  1. 如何解决countArr数组前面空来的部分
 *  2.如何解决稳定性的问题
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arr = {4,2,1,3,5,4,2,1,3,5,4,2,1,3,5,4,2,1,3,5};
        int[] newArr = sort(arr);
        printArr(newArr);

    }


    public static int[] mySort(int[] arr) {
        int[] countArr = new int[6];


        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]] += 1;
        }
        printArr(countArr);

        int[] newArr = new int[arr.length];
        int index = 0;

        for (int i = 1; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                System.out.println("->" + i);
                newArr[index] = i;
                index ++;
            }
        }
        return newArr;
    }
    public static int[] sort(int[] arr) {
        int[] countArr = new int[6];
        int[] newArr = new int[arr.length];


        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]] ++;
        }
        // for (int i = 0,j = 0; i < countArr.length; i++) {
        //     while ( countArr[i]-- > 0) {
        //         newArr[j++] = i;
        //     }
        // }
        // return newArr;

        // 为改成稳定算法，采用累计数组
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i-1];
        }

        for (int i = arr.length -1; i >= 0 ; i--) {
            newArr[--countArr[arr[i]]] = arr[i];
        }
        return newArr;
    }


    private static void printArr(int[] arr) {
        System.out.println("排序后结果：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


}
