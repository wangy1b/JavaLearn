package com.wyb.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 239. 滑动窗口最大值
 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

 返回滑动窗口中的最大值。

 示例 1：
 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 输出：[3,3,5,5,6,7]
 解释：
 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

 示例 2：
 输入：nums = [1], k = 1
 输出：[1]

 示例 3：
 输入：nums = [1,-1], k = 1
 输出：[1,-1]

 示例 4：
 输入：nums = [9,11], k = 2
 输出：[11]

 示例 5：
 输入：nums = [4,-2], k = 2
 输出：[4]

 提示：
 1 <= nums.length <= 105
 -104 <= nums[i] <= 104
 1 <= k <= nums.length
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        // int[] nums = {1,-1};
        // int k = 1;
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        int[] res = s.maxSlidingWindow(nums, k);
        for (int re : res) {
            System.out.println(re);
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k > nums.length) return null;
        // 记录最大值
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return - o1.compareTo(o2);
            }
        });
        // 记录最小值
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        int len =  nums.length;
        int[] res = new int[len];
        int last_idx = 0;
        for (int i = 0; i < nums.length; i++) {
            // todo 如何移除maxQueue中要出队的数据
            if (maxQueue.size() == k) {
                if (nums[i] > minQueue.peek() || k == 1){
                    maxQueue.remove(minQueue.poll());
                    maxQueue.offer(nums[i]);
                    minQueue.offer(nums[i]);
                }
            } else {
                maxQueue.offer(nums[i]);
                minQueue.offer(nums[i]);
            }
            if (i >= k - 1)
                res[last_idx++] = maxQueue.peek();
        }
        return Arrays.copyOfRange(res, 0, last_idx);
    }
}
