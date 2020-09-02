package com.wyb.leetcode;

import java.util.LinkedList;
import java.util.List;

/*

671. 二叉树中第二小的节点
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1:

输入:
    2
   / \
  2   5
     / \
    5   7

输出: 5
说明: 最小的值是 2 ，第二小的值是 5 。
示例 2:

输入:
    2
   / \
  2   2

输出: -1
说明: 最小的值是 2, 但是不存在第二小的值。

https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/

 */
public class SecondMinimumNodeInABinaryTree {
    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(
                2
                , new TreeNode(2, new TreeNode(2), new TreeNode(4))
                , new TreeNode(5
                                , new TreeNode(5)
                                , new TreeNode(7)
                                )
                );
        System.out.println(findSecondMinimumValue(treeNode));

    }


    private static int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return helper(root, root.val);
    }

    private static int helper(TreeNode root, int min) {
        if (root == null) return -1;
        if (root.val > min) return root.val;
        int left = helper(root.left, min);
        int right = helper(root.right, min);
        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);

    }
}
