package com.wyb.leetcode;

import java.util.*;

// Definition for a binary tree node.
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode transArrayToTree(String nums) {
        int len = nums.length();
        String[] arrStr = nums.split(",");
        int[] arr = new int[arrStr.length];
        int d = 0, idx = 0;
        for (String s : arrStr) {
            if (s.equalsIgnoreCase("null"))
                d = Integer.MAX_VALUE;
            else
                d = Integer.valueOf(s);
            arr[idx++] = d;
        }
        // int[] arr = {6,2,8,0,4,7,9,Integer.MAX_VALUE,Integer.MAX_VALUE,3,5};
        return helper(arr);
    }

    private static TreeNode helper(int[] nums) {
        int len = nums.length;
        if (len == 0) return null;
        TreeNode p = new TreeNode(nums[0]);
        TreeNode q = p;
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        while (p != null && 2 * i + 1 < len) {
            if ((nums[2 * i + 1]) != Integer.MAX_VALUE) {
                p.left = new TreeNode(nums[2 * i + 1]);
                queue.add(p.left);
            }
            if ((nums[2 * i + 2]) != Integer.MAX_VALUE) {
                p.right = new TreeNode(nums[2 * i + 2]);
                queue.add(p.right);
            }
            p = queue.poll();
            i += 1;
        }
        return q;
    }


    private int binarySizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
