package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/*

94. 二叉树的中序遍历
给定一个二叉树，返回它的中序遍历。

[在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。]
示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

https://leetcode-cn.com/problems/binary-tree-inorder-traversal/

 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));

        // System.out.println(inorderTraversal1(treeNode));
        System.out.println(inorderTraversal2(treeNode));
    }

    // 方法一：递归
    // 第一种解决方法是使用递归。这是经典的方法，直截了当。我们可以定义一个辅助函数来实现递归。
    private static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    // 在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树
    private static void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            // 左
            if (root.left != null) {
                helper(root.left, res);
            }
            // 中
            res.add(root.val);
            // 右
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }

    // 方法二：基于栈的遍历
    // 本方法的策略与上衣方法很相似，区别是使用了栈。
    private static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    // 方法三：莫里斯遍历
    // 本方法中，我们使用一种新的数据结构：线索二叉树。方法如下：
    // Step 1: 将当前节点current初始化为根节点
    // Step 2: While current不为空，
    //       若current没有左子节点
    //       a. 将current添加到输出
    //       b. 进入右子树，亦即, current = current.right
    //       否则
    //       a. 在current的左子树中，令current成为最右侧节点的右子节点
    //       b. 进入左子树，亦即，current = current.left

    private static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }


}
