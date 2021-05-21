package com.wyb.Algorithm;

public class ShellSort {

    // 给定一个间隔Gap,依次(到gap-1)的位置经过一个间隔Gap找出一组数，先用插入排序排好顺序
    // 调整Gap减少一倍，直到1，重复以上操作
    // Gap大的时候，移动次数少
    // Gap小的时候，移动距离短
    // 跳着排序，所以不稳定

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2, 10, 12, 11};
        sort(arr);
        printArr(arr);
    }


    /**
     * 该方法实质上是一种分组插入方法,是一种更高效的改进版本。
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     *  1.插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
     *  2.但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
     * 
     * 希尔排序是非稳定排序算法
     * @param arr
     */
    public static void sort(int[] arr) {
        // Knuth 序列
        int h = 1;
        while (h <= arr.length / 3 ) {
            h = 3 * h +1;
        }

        // for (int gap = arr.length >> 1; gap > 0; gap /= 2) {
        // for (int gap = arr.length / 2; gap > 0; gap /= 2) {
        for (int gap = h; gap > 0; gap = (gap-1)/3) {
            // 下面部分为插入排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
        }
    }

    private static void swap(int[] arr, int small, int big) {
        int tmp = arr[small];
        arr[small] = arr[big];
        arr[big] = tmp;
    }

    private static void printArr(int[] arr) {
        System.out.println("排序结果为：");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println();
    }
}
