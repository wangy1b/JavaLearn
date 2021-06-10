package com.wyb.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 480. 滑动窗口中位数
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * <p>
 * 例如：
 * <p>
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * <p>
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1  [3  -1  -3] 5  3  6  7      -1
 * 1  3  [-1  -3  5] 3  6  7      -1
 * 1  3  -1  [-3  5  3] 6  7       3
 * 1  3  -1  -3  [5  3  6] 7       5
 * 1  3  -1  -3  5  [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 始终有效，即：k 始终小于等于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 * <p>
 * https://leetcode-cn.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int resLen = len - k + 1;
        double[] res = new double[len - k + 1];
        // pq中存放数组，[窗口中数值，以及对应的idx]
        PriorityQueue<int[]> pq = new PriorityQueue<>();
        // odd 基数
        boolean oddFlag = k % 2 == 1;
        // 结果idx
        int resIdx = 0;
        int halfPqCnt = (k >> 1) + 1;
        if (!oddFlag) halfPqCnt += 1;


        // 中位数，转化成找出K数组中第 K/2 th小的数据，k/2位基数的时候就是本身，k/2为偶数的时候，取k/2和k/2+1的值取平均值

        // 每个window
        for (int windowEndIdx = 0; windowEndIdx < len; windowEndIdx++) {
            // idx从k开始往后多次
            // 需要维护pq, idx >= k
            // pq没满的时候随便加
            // 如果峰值的idx已经超出window开始的idx了，需要去掉


            // 当达到k的个数的时候，最小的值如果大于 当前遍历的值n（那这个值就不该进入pq）
            if (pq.size() == k && pq.peek()[0] >= nums[windowEndIdx]) {
                continue;
            }
            // 当达到k的个数的,并且大于最小值，那就要把最小值拉出，加入当前值
            if (pq.size() == k) {
                pq.poll();
            }

            // // 当前的值大于峰值的时候？
            // if (pq.size() == k && pq.peek() >= n)
            pq.offer(new int[]{nums[windowEndIdx], windowEndIdx});


            if (windowEndIdx >= k -1) {
                // 每个window里的维护的前k/2小的值
                res[resIdx++] = (double) pq.peek()[0];
                if (!oddFlag) {
                    while (pq.peek()[1] < windowEndIdx - k + 1 || pq.size() > halfPqCnt) pq.poll();
                    res[resIdx - 1] = (double) (res[resIdx - 1] + pq.peek()[0]) / 2;
                }

            }
        }

        return res;
    }


    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        double[] res = s.medianSlidingWindow(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
