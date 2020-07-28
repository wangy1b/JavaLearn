package com.wyb.leetcode;

public class findMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {3, 4};
        System.out.println(findMSA(nums1, nums2));
    }

    private static double finaMid(int[] nums1) {
        int minPos = 0;
        int maxPos = 0;
        for (int i = 0; i < nums1.length; i++) {
            minPos = nums1[i] < nums1[minPos] ? i : minPos;
            maxPos = nums1[i] > nums1[maxPos] ? i : maxPos;
        }
        return (nums1[minPos] + nums1[maxPos]) / 2;
    }

    private static double findMSA(int[] nums1, int[] nums2) {
        return (double)(finaMid(nums1) + finaMid(nums2)) / 2;
    }
}
