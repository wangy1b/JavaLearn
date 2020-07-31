package com.wyb.leetcode;

public class MaxTurbulenceSize {
    public static void main(String[] args) {
        int[] nums = {9,4,2,10,7,8,8,1,9};
        System.out.println(maxTurbulenceSize(nums));
    }

    private static int maxTurbulenceSize(int[] nums) {
        if (nums.length == 1) return 1;


        for (int i = 1; i < nums.length; i=i+2) {
        }
        return 0;
    }
}
