package com.wyb.leetcode;

/*
* 1008. 前序遍历构造二叉搜索树
返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。

(回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，
对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。
此外，前序遍历首先显示节点 node 的值，然后遍历 node.left，接着遍历 node.right。）

题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。

示例：

输入：[8,5,1,7,10,12]
输出：[8,5,10,1,7,null,12]

提示：

1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
preorder 中的值互不相同

https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
* */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        int len = preorder.length;
        if (len == 0) return null;
        return helper(preorder, 0, len - 1);
    }

    private TreeNode helper(int[] preorder, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(preorder[l]);
        if (l == r) return root;
        int mid = r;
        while (l < r && preorder[l] < preorder[mid]) mid--;

        root.left = helper(preorder, l + 1, mid);
        root.right = helper(preorder, mid + 1, r);

        return root;
    }

    public static void main(String[] args) {
        ConstructBinarySearchTreeFromPreorderTraversal c = new ConstructBinarySearchTreeFromPreorderTraversal();
        // int[] nums = {8, 5, 1, 7, 10, 12};
        int[] nums = { 1, 3};
        TreeNode res = c.bstFromPreorder(nums);
    }

}
