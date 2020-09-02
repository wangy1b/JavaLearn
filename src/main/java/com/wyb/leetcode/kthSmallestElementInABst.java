package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

/*

230. 二叉搜索树中第K小的元素
给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
进阶：
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？

https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/

 */
public class kthSmallestElementInABst {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                5
                , new TreeNode(3, new TreeNode(2,new TreeNode(1),null), new TreeNode(4))
                , new TreeNode(6)
        );
        System.out.println(kthSmallest(treeNode,3));
    }

    private static int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);

    }

    // 方法一：递归
    // 算法：
    //通过构造 BST 的中序遍历序列，则第 k-1 个元素就是第 k 小的元素。
    private static ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    // 方法二：迭代
    // 算法：
    //
    // 在栈的帮助下，可以将方法一的递归转换为迭代，这样可以加快速度，因为这样可以不用遍历整个树，可以在找到答案后停止。

    public int kthSmallest2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }


}
