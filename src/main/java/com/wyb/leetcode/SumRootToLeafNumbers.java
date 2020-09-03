package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/*

129. 求根到叶子节点数字之和
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

说明: 叶子节点是指没有子节点的节点。

示例 1:

输入: [1,2,3]
    1
   / \
  2   3
输出: 25
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.
示例 2:

输入: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
输出: 1026
解释:
从根到叶子节点路径 4->9->5 代表数字 495.
从根到叶子节点路径 4->9->1 代表数字 491.
从根到叶子节点路径 4->0 代表数字 40.
因此，数字总和 = 495 + 491 + 40 = 1026.

https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/

 */
public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                4
                ,new TreeNode(9
                                ,new TreeNode(5)
                                ,new TreeNode(1))
                ,new TreeNode(0)
        );
        System.out.println(sumNumbers(treeNode));
    }

    static int res = 0;
    private static void helper(TreeNode node,StringBuffer s,int level){
        if (node == null) return;
        s.append(node.val);
        if (node.left == null && node.right == null) {
            System.out.println("s : " + s.toString());
            res += Integer.valueOf(s.toString());
        }
        if (node.left != null) {
            helper(node.left,s,level + 1);
        }
        if (node.right != null) {
            helper(node.right,s,level + 1);
        }
        s.deleteCharAt(level-1);
    }
    private static int sumNumbers(TreeNode root) {
        StringBuffer s = new StringBuffer();
         helper(root,s,1);
         return res;
    }



    public int sumNumbers2(TreeNode root) {
        return helper2(root, 0);
    }

    public int helper2(TreeNode root, int i){
        if (root == null) return 0;
        int temp = i * 10 + root.val;
        if (root.left == null && root.right == null)
            return temp;
        return helper2(root.left, temp) + helper2(root.right, temp);
    }
}
