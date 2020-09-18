package com.wyb.leetcode;

/**
 *  构造二叉搜索树
 */
public class CreateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode res = createBinarySearchTree(5);
    }

    private static TreeNode createBinarySearchTree(int n){
        return helper(1, n);
    }

    private static TreeNode helper(int start, int end){
        if(start > end)
            return null;

        // 这里可以选择从start到end的任何一个值做为根结点，
        // 这里选择它们的中点，实际上，这样构建出来的是一颗平衡二叉搜索树
        // int val = (start + end) / 2;
        int val = (start + end) >> 1;
        TreeNode root = new TreeNode(val);

        root.left = helper(start, val - 1);
        root.right = helper(val + 1, end);

        return root;
    }


}
