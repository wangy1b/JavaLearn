package com.wyb.leetcode;

public class MergeTwoArray {
    public static void main(String[] args) {

        // int[] nums1 = {1,2,3,0,0,0};
        // int[] nums2 = {2,5,6};
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        int[] nums2 = {1, 2, 3};

        // merge(nums1,3, nums2, 3);
        // merge2(nums1, 3, nums2, 3);
        merge3(nums1, 3, nums2, 3);
        printArr(nums1);

    }

    private static void printArr(int[] nums1) {
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(" " + nums1[i]);
        }
        System.out.println();
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
            for (int j = m + i - 1; j >= 0; j--) {
                if (nums1[j] > nums1[j + 1]) {
                    swap(nums1, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private static void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        for (int i = m; i < m + n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums1[j] > nums1[j + 1]) {
                    swap(nums1, j, j + 1);
                }
            }
        }
    }

    private static void merge3(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
        {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);


    }




}
