package com.wyb.leetcode;

import java.util.Arrays;
import java.util.Random;

public class Knuth {


    // 算法，可以保证上面我所说的，对于生成的排列，每一个元素都能等概率的出现在每一个位置。
    // 或者反过来，每一个位置都能等概率的放置每个元素。
    public static void randomize( int arr[], int n)
    {
        Random r = new Random();
        for (int i = n-1; i > 0; i--) {
            int j = r.nextInt(i+1); // 选择一个 0,i+1 的随机数
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        // 纸牌编号
        int a[] = {0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22,
                23, 24, 25, 26, 27, 28, 29,
                30, 31, 32, 33, 34, 35, 36,
                37, 38, 39, 40, 41, 42, 43,
                44, 45, 46, 47, 48, 49, 50, 51};


        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        // randomize (arr, n);
        randomize (a, 52);
    }
}
