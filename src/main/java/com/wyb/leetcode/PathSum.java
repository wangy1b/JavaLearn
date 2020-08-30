package com.wyb.leetcode;

/*

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */

public class PathSum {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7)));
        // TreeNode treeNode = new TreeNode(1);
        // TreeNode treeNode = new TreeNode(1, new TreeNode(2), null);
        System.out.println(hasPathSum(treeNode.right,27));
    }

/*

思路及算法

观察要求我们完成的函数，我们可以归纳出它的功能：
询问是否存在从当前节点 root 到叶子节点的路径，满足其路径和为 sum。

假定从根节点到当前节点的值之和为 val，我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。

不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断 sum 是否等于 val 即可
（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。
若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。

 */

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        // 目标值递减上一个节点值，剩下与子节点值比较是否一样
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
    }


    // * Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }
}
