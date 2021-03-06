package com.wyb.Algorithm;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 8, 9, 3, 6};

        sort(arr,0,arr.length-1);
        printArr(arr);
    }

    /**
     * 快速排序（Quicksort）是对冒泡排序算法的一种改进
     * 基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     *
     * @param arr
     * @param leftBound
     * @param rightBound
     */
    public static void sort(int[] arr, int leftBound, int rightBound){
        if(leftBound >= rightBound) return;

        // 从两边往中间同时找，从左找到第一个比轴大的值，从右找到第一个比轴小的值，
        // 找到之后，这两个数直接做交换
        int mid = partition(arr, leftBound, rightBound);
        sort(arr,leftBound,mid -1 );
        sort(arr,mid + 1, rightBound );
    }

    /**
     *  从两边往中间同时找，从左找到第一个比轴大的值，从右找到第一个比轴小的值，
     *  找到之后，这两个数直接做交换
     */
    private static int partition(int[] arr, int leftBound, int rightBound){
        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) left ++;
            while (left <= right && arr[right] > pivot) right --;
            if (left < right) swap(arr, left, right);
        }
        swap(arr, left, rightBound);
        return left;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void printArr(int[] arr) {
        System.out.println("排序后结果为:");
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
