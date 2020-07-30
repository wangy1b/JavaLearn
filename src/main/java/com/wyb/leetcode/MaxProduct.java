package com.wyb.leetcode;

public class MaxProduct {
    public static void main(String[] args) {
        // int[] nums = {2, 3, -2, 4};
        int[] nums = {-3, -2, 4};
        // int[] nums = {0, 2};
        System.out.println(maxProduct(nums));
    }

    private static int maxProduct(int[] nums) {
        // 好理解
        int imax = 1, imin = 1, result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            // 当负数出现时则imax与imin进行交换再进行下一步计算
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            // 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
            imax = Math.max(imax * nums[i],nums[i]);
            // 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
            imin = Math.min(imin * nums[i],nums[i]);
            result = Math.max(imax, result);
        }
        return result;
    }

    private static int maxProductOfficial(int[] nums) {
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
