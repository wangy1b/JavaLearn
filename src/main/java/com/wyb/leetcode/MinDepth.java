package com.wyb.leetcode;

/*

给定一个二叉树，找出其最小深度。
最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
说明: 叶子节点是指没有子节点的节点。
示例:

给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回它的最小深度  2.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */

public class MinDepth {
    public static void main(String[] args) {
        // TreeNode treeNode = new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7)));
        // TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode = new TreeNode(1,new TreeNode(2),null);
        System.out.println(minDepth(treeNode));
    }

    // todo meidong
    private static int minDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
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
