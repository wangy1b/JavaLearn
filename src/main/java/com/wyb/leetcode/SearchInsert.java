package com.wyb.leetcode;

public class SearchInsert {
    public static void main(String[] args) {
        // int[] arr = new int[]{1};
        int[] arr = new int[]{1,3,5,6};
        System.out.println(searchInsert(arr, 1));
    }

    private static  int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while(left <= right) {
            while(left <= right && nums[left] < target ) left ++;
            while(left <= right && nums[right] > target ) right --;
            if (left == right && nums[left] == target) return left;
        }
        return left;
    }
    private static  int searchInsertOfficial(int[] nums, int target) {
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
