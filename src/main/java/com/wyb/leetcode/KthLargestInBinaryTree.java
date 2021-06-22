package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 给定一棵二叉搜索树，请找出其中第k大的节点。

 示例 1:
 输入: root = [3,1,4,null,2], k = 1
       3
     / \
    1   4
     \
      2
 输出: 4

 示例 2:
 输入: root = [5,3,6,2,4,null,null,1], k = 3
           5
         / \
        3   6
      / \
    2   4
  /
 1
 输出: 4


 限制：
 1 ≤ k ≤ 二叉搜索树元素个数

 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/

 */
public class KthLargestInBinaryTree {
    // 二叉搜索树，中序遍历的结果就是顺序
    // 普通方法。先遍历出结果，然后找出对应值
    public int kthLargest1(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        helpInorderDFS(root, res);
        return res.get(res.size() - k);
    }
    //
    private void helpInorderDFS(TreeNode root,List<Integer> res){
        if (root != null) {
            if (root.left != null)
                helpInorderDFS(root.left, res);
            res.add(root.val);
            if (root.right != null)
                helpInorderDFS(root.right, res);
        }
    }

    // 遍历过程中遇到值直接输出
    // 寻找第k大节点,
    // 如果将中序遍历先访问右节点，根节点，再访问左节点，顺序就是逆序的
    // 就是第k个节点
    int res, k;
    public int kthLargest2(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.right);
        if(k == 0) return;
        if(--k == 0) res = root.val;
        dfs(root.left);
    }

    public static void main(String[] args) {
        String nums = "3,1,4,null,2";
        int k = 1;
        TreeNode root = TreeNode.transArrayToTree(nums);
        KthLargestInBinaryTree b = new KthLargestInBinaryTree();
        System.out.println(b.kthLargest1(root, k));
        System.out.println(b.kthLargest2(root, k));
    }
}
