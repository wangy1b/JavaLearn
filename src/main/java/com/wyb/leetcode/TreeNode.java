package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // todo transListToTree
    public static TreeNode transListToTree(List<Integer> nums) {
        // root = [6,2,8,0,4,7,9,null,null,3,5]
        int len = nums.size();
        int ideaSize = binarySizeFor(len);
        System.out.println("len : " + len);
        System.out.println("ideaSize : " + ideaSize);

        return helper(nums,0,len - 1, ideaSize);
    }

    private static TreeNode helper(List<Integer> nums, int startIdx, int endIdx,int perfectSize){
        int len = nums.size();
        if (len == 0 || startIdx > len - 1) return null;
        TreeNode res = new TreeNode(nums.get(startIdx));
        if (len == 1) return res;

        int leftStartIdx = startIdx + 1;
        int rightStartIdx = perfectSize >> 1;

        res.left = helper(nums,leftStartIdx, rightStartIdx, rightStartIdx);
        res.right = helper(nums,rightStartIdx, endIdx, rightStartIdx);
        return res;
    }


     private static int binarySizeFor(int cap) {
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
