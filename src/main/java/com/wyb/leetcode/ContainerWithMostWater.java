package com.wyb.leetcode;
/*

11. 盛最多水的容器
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。
图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例：
输入：[1,8,6,2,5,4,8,3,7]
输出：49
https://leetcode-cn.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] nums = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(nums));
        System.out.println(maxAreaTest(nums));
    }


    private static int maxArea(int[] height) {
        int res = 0;
        for (int start =0;start < height.length;start++) {
            for (int end = start + 1; end < height.length ; end++) {
                int cur = (end - start) * (Math.min(height[start],height[end]));
                res = Math.max(res,cur);
            }
        }
        return res;
    }


    private static int maxAreaOfficial(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }
    private static int maxAreaTest(int[] height) {
        int res = 0;
        int start = 0;
        int end = height.length-1;
        while (start < end) {
            int cur = (end - start) * (Math.min(height[start],height[end]));
            res = Math.max(res,cur);
            if (height[start] < height[end]) {
                start++;
            } else {
                end --;
            }
        }
        return res;
    }
}
