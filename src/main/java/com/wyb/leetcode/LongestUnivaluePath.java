package com.wyb.leetcode;

/*

687. 最长同值路径
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:

输入:

              5
             / \
            4   5
           / \   \
          1   1   5
输出:

2
示例 2:

输入:

              1
             / \
            4   5
           / \   \
          4   4   5
输出:

2
注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。

https://leetcode-cn.com/problems/longest-univalue-path/

 */
public class LongestUnivaluePath {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                1
                ,new TreeNode(4
                            ,new TreeNode(4)
                            ,new TreeNode(4)
                                 )
                ,new TreeNode(5,null,new TreeNode(5))
        );

        System.out.println(longestUnivaluePath(treeNode));
    }
    static int res = 0;

    // TODO: 2020/9/3 not finished
    private static int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        return Math.max(helper(root.left,root.val),helper(root.right,root.val));
    }

    private static int helper(TreeNode node ,int val){
        if (val == node.val) {
            res ++;
        }
        else {
            val = node.val;
            res = 0;
        }

        if (node.left != null) helper(node.left,val);
        if (node.right != null) helper(node.right,val);
        return res;
    }



}
