package com.wyb.leetcode;
/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。


 */
public class IsBalanced {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3, new TreeNode(9)
                , new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // System.out.println(getDepth(treeNode));
        // System.out.println(getDepth(treeNode.left));
        // System.out.println(getDepth(treeNode.right));
        // System.out.println(getDepth(treeNode.right.left));
        // System.out.println(getDepth(treeNode.right.right));
        System.out.println(isBalanced(treeNode));
    }

    private static int getDepth(TreeNode treeNode){
        if (treeNode == null) return 0;
        TreeNode p = treeNode;
        return Math.max(getDepth(p.left) + 1,getDepth(p.right) + 1);
    }

    private static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        boolean res = false;
        TreeNode left = root;
        TreeNode right = root;
        if (root.left != null) {
            left = root.left;
        }
        if( root.right != null){
            right = root.right;
        }
        int depth = getDepth(left) - getDepth(right);
        if ( depth <= 1) res = true;
        res = res && isBalanced(left) && isBalanced(right);
        return res;
    }

     // * Definition for a binary tree node.
     private static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
         TreeNode(int x, TreeNode left, TreeNode right) {
             this.val = x;
             this.left = left;
             this.right = right;
         }
     }

}
