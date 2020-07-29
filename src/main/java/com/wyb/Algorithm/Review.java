package com.wyb.Algorithm;

public class Review {

    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 1, 9, 2, 0, 4, 8, 6};
        // selectionTest(arr);
        // bubbleTest(arr);
        // insertionTest(arr);
        // printArr(arr);
        // System.out.println("------");
        // quickSort(arr, 0,arr.length-1);
        // printArr(arr);

        countSort(arr);
    }

    private static void selectionTest(int[] arr){
        for (int i = 0; i < arr.length -1 ; i++) {
            int minPos = i;
            for (int j = i+1; j < arr.length ; j++) {
                minPos = arr[minPos] > arr[j] ? j :minPos;
            }
            swap(arr,i,minPos);
        }


    }


    private static void bubbleTest(int[] arr) {
        // 选择一个数与剩下的逐个比较，比他大就交换
        // 从最大位置开始，每轮找个最大的放在最右边保留不动
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) swap(arr, j, j+1);
            }
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    private static void insertionTest(int[] arr) {
        //将数插入它该有的位置
        // 可以用swap方法
        // 也可以用标记法

        // 抽出一张牌
        for (int i = 1; i < arr.length; i++) {
            // 在有序的部分，插入它该有的位置
            // 与之前有序的部分比较，如果有比自己小的，就交换
            for (int j = i ; j > 0 ; j--) {
                if (arr[j] < arr[j-1]) swap(arr, j, j-1);
            }
        }
    }

    private static void printArr(int[] arr) {
        System.out.println("排序后结果为:");
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    private static void countSort(int[] arr) {
        int[] countArr = new int[10];
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i-1];
        }

        for (int i = arr.length -1; i > 0; i--) {
            newArr[--countArr[arr[i]]] = arr[i];
        }

        printArr(newArr);
    }


    private static void quickSort(int[] arr, int leftbound, int rightbound){

        if (leftbound >= rightbound) return;
        int mid = partition(arr,leftbound,rightbound);
        quickSort(arr,leftbound,mid -1 );
        quickSort(arr,mid + 1,rightbound );

    }

    private static int partition(int[] arr, int leftbound, int rightbound) {
        // 选取最右边的一个数为pivot，从左边依次选到比pivot大的数，从右边依次选到比pivot小的数，然后交换
        int pivot = arr[rightbound];
        int left = leftbound;
        int right = rightbound-1;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) {
                left ++;
            }

            while (left <= right && arr[right] > pivot){
                right --;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }

        swap(arr, left,rightbound);
        return left;
    }


}
