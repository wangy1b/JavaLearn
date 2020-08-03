package com.wyb.leetcode;

public class MaxTurbulenceSize {
    public static void main(String[] args) {
        int[] nums = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        // int[] nums = {4, 8, 12, 16};
        System.out.println(maxTurbulenceSize(nums));
    }
/*
 不会
 */
    private static int maxTurbulenceSize(int[] nums) {
        int N = nums.length;
        int ans = 1;
        int anchor = 0;

        for (int i = 1; i < N; ++i) {
            int c = Integer.compare(nums[i - 1], nums[i]);
            if (i == N - 1 || c * Integer.compare(nums[i], nums[i + 1]) != -1) {
                if (c != 0) ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }

        return ans;
    }
}
