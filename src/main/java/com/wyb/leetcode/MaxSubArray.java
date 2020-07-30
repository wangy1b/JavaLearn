package com.wyb.leetcode;

public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /** 不会
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums){
        int max = nums[0];
        for (int i = 1; i < nums.length; i++ ) {
            if (nums[i] + nums[i-1] > nums[i] ) {
                nums[i] += nums[i-1];
            }
            if( nums[i] > max ){
                max = nums[i];
            }
        }
        return max;
    }
}
