package com.wyb.leetcode;

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

    //todo generateTree
    private TreeNode transArrayToTree(int[] nums) {

        TreeNode root = new TreeNode(nums[0]);
        for (int i = 0,level = 0; i < nums.length; i++) {

        }
        return root;
    }

}
