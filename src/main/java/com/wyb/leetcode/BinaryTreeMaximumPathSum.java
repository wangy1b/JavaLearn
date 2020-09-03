package com.wyb.leetcode;



/*

124. 二叉树中的最大路径和
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。



示例 1：

输入：[1,2,3]

       1
      / \
     2   3

输出：6
示例 2：

输入：[-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出：42

https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/

 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                -10
                ,new TreeNode(9)
                ,new TreeNode(20
                    ,new TreeNode(15)
                    ,new TreeNode(7))

        );
        TreeNode treeNode1 = new TreeNode(1);
        System.out.println(maxPathSum(treeNode));
    }

    static int res = Integer.MIN_VALUE;
    private static int maxPathSum(TreeNode root) {
        if (root == null) return res;
        helper(root);
        return res;
    }

    public static int helper(TreeNode root){
        if (root == null) return 0;
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int l = Math.max(helper(root.left),0);
        int r = Math.max(helper(root.right),0);
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int temp = l + r + root.val;
        // 更新答案
        res = Math.max(res,temp);
        // 返回节点的最大贡献值
        return  root.val + Math.max(l,r);
    }



}
