package com.wyb.leetcode;

/**
 * 700. 二叉搜索树中的搜索
 给定二叉搜索树（BST）的根节点和一个值。
 你需要在BST中找到节点值等于给定值的节点。
 返回以该节点为根的子树。
 如果节点不存在，则返回 NULL。

 例如，

 给定二叉搜索树:

        4
      / \
    2   7
  / \
 1   3

 和值: 2

 你应该返回如下子树:

   2
  / \
 1   3

 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。

 https://leetcode-cn.com/problems/search-in-a-binary-search-tree/

 */
public class BinarySearchTreeSearch {
    // 通用二叉树
    public TreeNode searchBT(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode resLeft = searchBT(root.left, val);
        return resLeft != null ? resLeft : searchBT(root.right, val);
    }

    // 方法一：递归
    // 利用二叉搜索树本身的大小特性
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) return root;
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    // 方法二：迭代
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null || val == root.val) return root;
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTreeSearch b = new BinarySearchTreeSearch();
        String nums = "4,2,7,1,3";
        int val = 1;
        TreeNode root = TreeNode.transArrayToTree(nums);
        TreeNode res = b.searchBST(root, val);
    }
}
