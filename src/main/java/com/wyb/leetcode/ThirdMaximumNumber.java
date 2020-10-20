package com.wyb.leetcode;

import java.util.PriorityQueue;

/*

414. 第三大的数
给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:

输入: [3, 2, 1]

输出: 1

解释: 第三大的数是 1.
示例 2:

输入: [1, 2]

输出: 2

解释: 第三大的数不存在, 所以返回最大的数 2 .
示例 3:

输入: [2, 2, 3, 1]

输出: 1

https://leetcode-cn.com/problems/third-maximum-number/

 */
public class ThirdMaximumNumber {

    public static void main(String[] args) {
        int[] nums = {1,1};
        int res = thirdMax(nums);
        System.out.println("res : " + res);

    }

    private static int thirdMax(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        int k = 3;
        for( int n : nums) {
            if (!heap.contains(n)) {
                heap.add(n);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        if (heap.size() < k && heap.size() > 1) heap.poll();
        return heap.peek();
    }


    private static int thirdMax1(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int num : nums) {
            if(num>first){
                third = second;
                second = first;
                first = num;
            }else if(num>second&&num<first){
                third = second;
                second = num;
            }else if(num<second&&num>third){
                third = num;
            }
        }
        return third!=Long.MIN_VALUE ? new Long(third).intValue() : new Long(first).intValue();
    }

}
