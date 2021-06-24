package com.wyb.leetcode;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 98. 验证二叉搜索树
 给定一个二叉树，判断其是否是一个有效的二叉搜索树。

 假设一个二叉搜索树具有如下特征：

 节点的左子树只包含小于当前节点的数。
 节点的右子树只包含大于当前节点的数。
 所有左子树和右子树自身必须也是二叉搜索树。

 示例 1:
 输入:
    2
  / \
 1   3
 输出: true

 示例 2:
 输入:
       5
     / \
    1   4
   / \
  3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
 根节点的值为 5 ，但是其右子节点值为 4 。

 */
public class BinarySearchTreeValidate {
    // 普通遍历的办法
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i+1))
                return false;
        }
        return true;
    }

    private void helper(TreeNode root,List<Integer> res){
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }

    public static void main(String[] args) {
        String nums = "3,1,4,null,null,2";
        TreeNode root = TreeNode.transArrayToTree(nums);
        BinarySearchTreeValidate b = new BinarySearchTreeValidate();
        // System.out.println(b.isValidBST(root));
        System.out.println(b.isValidBSTOfficial1(root));
        System.out.println(b.isValidBSTOfficial2(root));
    }

    public boolean isValidBSTOfficial1(TreeNode root) {
        return helper1(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helper1(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return helper1(node.left, lower, node.val) && helper1(node.right, node.val, upper);
    }


    public boolean isValidBSTOfficial2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


}
