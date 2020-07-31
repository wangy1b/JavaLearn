package com.wyb.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/degree-of-an-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindShortestSubArray {
    public static void main(String[] args) {
        // int[] nums = {1, 2, 2, 3, 1};
        int[] nums = {1,2,2,3,1,4,2};
        System.out.println(findShortestSubArray(nums));


    }

    /** 题目没理解
        nums.length 在1到50,000区间范围内。
        nums[i] 是一个在0到49,999范围内的整数。
     */
    private static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap(),
                right = new HashMap(), count = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (left.get(x) == null) left.put(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int x: count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;

    }
}
