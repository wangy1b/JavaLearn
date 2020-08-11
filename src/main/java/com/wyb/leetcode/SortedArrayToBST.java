package com.wyb.leetcode;

import java.util.LinkedList;
import java.util.Queue;


/*

将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */

public class SortedArrayToBST {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(0,
                new TreeNode(-3, new TreeNode(-10), null),
                new TreeNode(9, new TreeNode(5), null));

        int[] nums = {-10, -3, 0, 5, 9};
        printTree(sortedArrayToBST(nums));

    }


    private static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int start, int end) {
        if (start > end) return null;

        // mid
        // int mid = (start + end) / 2;
        // int mid = (start + end) >> 1;
        // int mid = (start + end + 1) / 2;
        int mid = (start + end + 1) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid - 1);
        root.right = helper(nums, mid + 1, end);
        return root;
    }


    // Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }

    private static void printTree(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 核心方案：Queue中取出一个元素，再把期左右孩子放入Queue
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                System.out.print(node.val + " -> ");
                // 左孩子不空，加入queue
                if(node.left != null) queue.add(node.left);
                // 右孩子不空，加入queue
                if(node.right != null) queue.add(node.right);
            }

        }

    }

}
