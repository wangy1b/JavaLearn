package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*

145. 二叉树的后序遍历
给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
https://leetcode-cn.com/problems/binary-tree-postorder-traversal/

 */
public class BinaryTreePostorderTraversal {

    // 递归
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root,res);
        return res;
    }
    // 后序遍历
    //  左 -> 右 -> 中
    private void helper(TreeNode root,List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left,res);
            }
            if (root.right != null) {
                helper(root.right,res);
            }
            res.add(root.val);
        }
    }


    // 迭代
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }

}
