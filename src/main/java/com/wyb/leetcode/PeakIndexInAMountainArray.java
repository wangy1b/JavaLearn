package com.wyb.leetcode;

/*

852. 山脉数组的峰顶索引
我们把符合下列属性的数组 A 称作山脉：

A.length >= 3
存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。



示例 1：

输入：[0,1,0]
输出：1
示例 2：

输入：[0,2,1,0]
输出：1


提示：

3 <= A.length <= 10000
0 <= A[i] <= 10^6
A 是如上定义的山脉

 https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/


 */
public class PeakIndexInAMountainArray {
    public static void main(String[] args) {
        int[] nums = {0,0,1,2,1,3,0};
        PeakIndexInAMountainArray peakIndexInAMountainArray = new PeakIndexInAMountainArray();
        int res = peakIndexInAMountainArray.peakIndexInMountainArray(nums);
        System.out.println("res = " + res );
    }

    public int peakIndexInMountainArray(int[] arr) {
        int len = arr.length;
        int peakIdx = 0;
        int s = 0, e = arr.length -1;
        while (s < e) {
            peakIdx++;
            while (s < e && arr[s] < arr[peakIdx]) s++;
            while (s < e && arr[e] < arr[peakIdx]) e--;
        }
        return peakIdx;
    }
    // 找到第一个比前一个小的值
    public int peakIndexInMountainArrayOfficial(int[] arr) {
        int i = 0;
        while (arr[i] < arr[i+1]) i++;
        return i;
    }
    // 二分法
    public int peakIndexInMountainArrayOfficial2(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (arr[mi] < arr[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }





}
