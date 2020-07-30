package com.wyb.leetcode;

public class MaxProduct {
    public static void main(String[] args) {
        // int[] nums = {2, 3, -2, 4};
        int[] nums = {0, 2};
        System.out.println(maxProduct(nums));
    }

    private static int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}
