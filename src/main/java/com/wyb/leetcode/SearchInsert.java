package com.wyb.leetcode;

public class SearchInsert {
    public static void main(String[] args) {
        int[] arr = new int[]{1};
        System.out.println(searchInsert(arr, 7));
    }

    private static  int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;

    }
}
