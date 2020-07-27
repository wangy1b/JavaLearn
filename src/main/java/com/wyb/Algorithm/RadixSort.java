package com.wyb.Algorithm;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {421, 240, 115, 532, 305, 430, 124};
        int[] result = sort(arr);
        printArr(result);
    }

    private static int[] sort(int[] arr) {
        int[] result = new int[arr.length];
        int[] count = new int[10];

        //第一步求最高位数
        for (int i = 0; i < 3; i++) {
            int division = (int) Math.pow(10, i);
            // System.out.println(String.format("division : %s", division));
            for (int j = 0; j < arr.length; j++) {
                // System.out.println(String.format("arr[j] : %s", arr[j]));
                int num = arr[j] / division % 10;
                // System.out.println(String.format("num : %s", num));
                count[num]++;
            }

            // 累计数组
            for (int m = 1; m < count.length; m++) {
                count[m] = count[m] + count[m - 1];
            }

            // 计数排序回填结果 (重点是倒序回填)
            for (int n =  arr.length - 1; n >= 0; n--) {
                int num = arr[n] / division % 10;
                result[--count[num]] = arr[n];
            }
            // printArr(result);

            System.arraycopy(result, 0, arr, 0, arr.length);
            Arrays.fill(count, 0);
        }
        return result;

    }


    private static void printArr(int[] arr) {
        System.out.println("排序后结果：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
