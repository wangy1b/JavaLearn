package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

95. 不同的二叉搜索树 II
给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。

示例：

输入：3
输出：
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释：
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


提示：

0 <= n <= 8

https://leetcode-cn.com/problems/unique-binary-search-trees-ii/

 */
public class UniqueBinarySearchTreesII {
    public static void main(String[] args) {
        int n = 3;
        List<TreeNode> res = generateTrees(n);
    }

    private static List<TreeNode> myGenerateTrees(int n) {
        if (n <= 0) return null;
        List<TreeNode> res = new ArrayList<>();
        // preorder
        for (int mid = 1; mid <= n; mid++) {
            int l = mid, r = mid;
            while (r <= n|| l>0) {
                TreeNode tmp = new TreeNode(mid);
                // left
                helper(tmp, 1, --l, mid);
                // right
                helper(tmp, ++r, n, mid);
                // result
                if (tmp.left != null || tmp.right != null) res.add(tmp);
            }
        }

        return res;
    }

    /*

    二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，
    小于右子树所有节点的值，且左子树和右子树也同样为二叉搜索树。
    因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 n，
    如果我们枚举根节点的值为 i，那么根据二叉搜索树的性质我们可以知道左子树的节点值的集合为 [1…i−1]，
    右子树的节点值的集合为 [i+1…n]。而左子树和右子树的生成相较于原问题是一个序列长度缩小的子问题，
    因此我们可以想到用递归的方法来解决这道题目

     */
    // TODO: 2020/9/14 No Reverse Order

    private static void helper(TreeNode root, int start, int end, int mid) {
        // 超过边界
        if (start > end || start < 0) return;

        if (root.val > end) { // 左边
            root.left = new TreeNode(end);
            helper(root.left, start , end-1, mid);
        } else if (root.val < start) { // 右边
            root.right = new TreeNode(start);
            helper(root.right, start + 1, end, mid);
        }

    }


    private static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    private static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

}
