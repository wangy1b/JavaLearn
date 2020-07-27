package com.wyb.Algorithm;

import java.util.Arrays;

public class BucketSort {

    public static void main(String[] args) {
        double[] arr = {0.0, 0.12, 0.18, 0.93, 0.45, 0.76, 0.89, 0.03, 0.55, 0.98, 0.67, 1.0};
        double[] result = sort(arr);
        printArr(result);
    }

    private static double[] sort(double[] arr) {
        // 1.遍历数组求出最小值 0.0
        // 2.遍历数组求出最大值 1.0
        // 3.算出每个桶的范围
        // 4.遍历数组里的每个元素，看看属于哪个范围
        // 5.每个桶里面进行单独排序
        // 6.最后把每个桶输出

        // 1.遍历数组求出最小值 0.0
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = arr[i] > arr[max] ? i : max;
        }
        double maxVal = arr[max];

        // 2.遍历数组求出最大值 1.0
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i] < arr[min] ? i : min;
        }
        double minVal = arr[min];

        // System.out.println(String.format("maxVal : %s,minVal : %s", maxVal,minVal));

        // 3.算出每个桶的范围
        int bucketCnt = 4;
        double bucket = (maxVal - minVal) / bucketCnt;
        double[] bucketArr = new double[5];
        for (int i = 0; i < bucketCnt; i++) {
            bucketArr[i] = minVal + bucket * i;
        }
        bucketArr[4] = maxVal;
        // printArr(bucketArr);
        double fillNum = 100.0;
        double[][] buckets = new double[4][arr.length];
        for (int i = 0; i < buckets.length; i++) {
            Arrays.fill(buckets[i], fillNum);
        }


        // 4.遍历数组里的每个元素，看看属于哪个范围
        for (int i = 0, k = 0; i < arr.length; i++) {
            for (int j = 0; j < bucketArr.length - 1; j++) {
                if (j <= 2 && arr[i] >= bucketArr[j] && arr[i] < bucketArr[j + 1]) {
                    buckets[j][k++] = arr[i];
                    break;
                } else if (j == 3 && arr[i] >= bucketArr[j] && arr[i] <= bucketArr[j + 1]) {
                    buckets[j][k++] = arr[i];
                    break;
                }

            }
        }

        // for (int i = 0; i < buckets.length; i++) {
        //     System.out.println("dimension: "+i);
        //     for (int j = 0; j < buckets[i].length; j++) {
        //         System.out.print(buckets[i][j] + " ");
        //     }
        // }

        // 5.每个桶里面进行单独排序,此处采用快速排序
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = quickSort(buckets[i], 0, buckets[i].length - 1);
        }

        // 6.最后把每个桶输出
        double[] result = new double[arr.length];
        for (int i = 0, j = 0; i < buckets.length; i++) {
            for (int k = 0; k < buckets[i].length; k++) {
                if (buckets[i][k] != fillNum) {
                    result[j] = buckets[i][k];
                    j++;
                }
            }
        }

        return result;
    }


    private static double[] quickSort(double[] arr, int leftBound, int rightBound) {
        if (leftBound >= rightBound) return arr;

        // 从两边往中间同时找，从左找到第一个比轴大的值，从右找到第一个比轴小的值，
        // 找到之后，这两个数直接做交换
        int mid = partition(arr, leftBound, rightBound);
        quickSort(arr, leftBound, mid - 1);
        quickSort(arr, mid + 1, rightBound);
        return arr;
    }

    private static int partition(double[] arr, int leftBound, int rightBound) {
        double pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) left++;
            while (left <= right && arr[right] > pivot) right--;
            if (left < right) swap(arr, left, right);
        }
        swap(arr, left, rightBound);
        return left;
    }

    private static void swap(double[] arr, int x, int y) {
        double temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void printArr(double[] arr) {
        System.out.println("排序后结果：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
