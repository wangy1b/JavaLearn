package com.wyb.Algorithm;

public class MergeSort {

    // 数组拆分子数组，再拆到子数据只有两个数，直接排序
    public static void main(String[] args) {
        // int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2, 10, 12, 11};
        int[] arr = {1, 4, 7, 8, 3, 6, 9};

        sort(arr, 0, arr.length - 1);
        printArr(arr);
    }

    public static void sort(int[] arr, int left, int right) {
        if (left == right) return ;
        // 分成两半
        int mid = left + (right - left) / 2;
        // 左边排序
        sort(arr, left, mid);
        // 右边排序
        sort(arr, mid + 1, right);
        // 合并
        merge(arr, left, mid + 1, right);

    }

    // private static void merge(int[] arr) {
    //     int mid = arr.length / 2;
    //     int[] temp = new int[arr.length];
    //
    //     int i = 0;
    //     int j = mid + 1;
    //     int k = 0;
    //     while (i <= mid && j < arr.length) {
    //         // 三元运算符简写
    //         temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
    //         // if (arr[i] <= arr[j]) {
    //         //     temp[k++] = arr[i++];
    //         //     // i++;
    //         //     // k++;
    //         // } else {
    //         //     temp[k++] = arr[j++];
    //         //     // j++;
    //         //     // k++;
    //         // }
    //     }
    //
    //     // 检查前半段是否有剩下元素，直接追加上
    //     while (i <= mid) {
    //         temp[k++] = arr[i++];
    //     }
    //
    //     // 检查后半段是否有剩下元素，直接追加上
    //     while (j < arr.length) {
    //         temp[k++] = arr[j++];
    //     }
    //     printArr(temp);
    //
    // }

    private static void merge(int[] arr, int leftPtr, int rightPtr, int rightBound) {
        int mid = rightPtr - 1;
        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound) {
            // 三元运算符简写
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            // if (arr[i] <= arr[j]) {
            //     temp[k++] = arr[i++];
            //     // i++;
            //     // k++;
            // } else {
            //     temp[k++] = arr[j++];
            //     // j++;
            //     // k++;
            // }
        }

        // 检查前半段是否有剩下元素，直接追加上
        while (i <= mid) temp[k++] = arr[i++];


        // 检查后半段是否有剩下元素，直接追加上
        while (j <= rightBound) temp[k++] = arr[j++];


        for (int m = 0; m < temp.length; m++) arr[leftPtr + m] = temp[m];


    }


    private static void printArr(int[] arr) {
        System.out.println("排序结果为：");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println();
    }
}

