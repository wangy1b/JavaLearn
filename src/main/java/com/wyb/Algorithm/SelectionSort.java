package com.wyb.Algorithm;

/**
 * 优化思路：
 * 1.选择最小值pos的同时找出最大值的pos。这样能减少一半的循环?
 * 2.每次找出剩下值中两个值一起比较，先比较选择的两个值的大小?
 * 3.写一个程序证明选择排序不稳定?
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2, 10};
        // sort(arr);
        sortAdvance(arr);
    }

    /**
     * 选择排序（Selection sort）是一种简单直观的排序算法。
     * 它的工作原理是：
     * 第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，
     * 然后再从剩余的未排序元素中寻找到最小（大）元素，然后放到已排序的序列的末尾。
     * 以此类推，直到全部待排序的数据元素的个数为零。
     * 选择排序是不稳定的排序方法。
     * @param arr
     * @return int[]
     */
    static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            swap(arr, minPos, i);
            // System.out.print(String.format("经过%s次排序后结果为：", i + 1));
            // printArr(arr);
        }
        return arr;
    }

    static int[] sortAdvance(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            int maxPos = arr.length - 1 - i;
            if (minPos == maxPos ) {
                break;
            }
            for (int j = i + 1; j < arr.length - 1; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
                //int maxPosPre = maxPos - 1;
                //maxPos = arr[maxPosPre] > arr[maxPos] ? maxPosPre : maxPos;

            }
            //swap(arr, minPos, i);
            for (int maxPre = maxPos - 1; maxPre >= 0; maxPre--) {
                maxPos = arr[maxPre] > arr[maxPos] ? maxPre : maxPos;
            }

            swap(arr, arr.length - 1 - i, maxPos);
            System.out.print(String.format("经过%s次排序后结果为：", i + 1));
            printArr(arr);
        }
        return arr;
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
