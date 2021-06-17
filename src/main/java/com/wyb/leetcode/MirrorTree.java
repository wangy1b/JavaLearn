package com.wyb.leetcode;

/*
剑指 Offer 27. 二叉树的镜像
请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1



示例 1：

输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]


限制：

0 <= 节点个数 <= 1000

注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/

 */
public class MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        // 当节点为空时，直接返回
        if(root == null) return null;
        // 设置一个临时的节点 tmp 用来存储当前节点的左子树
        TreeNode tmp = root.left;
        // 以下两个操作是在交换当前节点的左右子树

        // 当前节点的左子树为节点的右子树
        // 同时递归下去，不停的交互子树中的节点
        root.left = mirrorTree(root.right);

        // 当前节点的右子树为节点的左子树
        // 同时递归下去，不停的交互子树中的节点
        root.right = mirrorTree(tmp);

        // 最后返回根节点
        return root;
    }


    public static void main(String[] args) {
        // [4,2,7,1,3,6,9]
        TreeNode treeNode1 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));

        MirrorTree m = new MirrorTree();
        TreeNode res = m.mirrorTree(treeNode1);

    }
}
