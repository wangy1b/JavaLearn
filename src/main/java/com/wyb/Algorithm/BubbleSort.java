package com.wyb.Algorithm;

/**
 * 使得冒泡算法的最好时间复杂度优化为O(n)?
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2, 10};
        sort(arr);
    }

    /**
     * 冒泡排序（Bubble Sort），是一种计算机科学领域的较简单的排序算法。
     * 它重复地走访过要排序的元素列，依次比较两个相邻的元素，如果顺序（如从大到小、首字母从Z到A）错误就把他们交换过来。
     * 走访元素的工作是重复地进行直到没有相邻元素需要交换，也就是说该元素列已经排序完成。
     * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端（升序或降序排列），
     * 就如同碳酸饮料中二氧化碳的气泡最终会上浮到顶端一样，故名“冒泡排序”。
     * 如果两个元素相等，是不会再交换的；
     * 如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
     * 所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
     *
     * @param arr
     */
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


