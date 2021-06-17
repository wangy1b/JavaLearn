package com.wyb.leetcode;

import java.util.Arrays;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {


    // 前序：根节点,[左子节点前序],[右子节点前序]
    // 中序：[左子节点中序],根节点,[右子节点中序]
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;
        TreeNode res = new TreeNode(preorder[0]);
        if (len == 1) return res;
        // 根据前序确定的根节点，找出左子树
        int preIdx = 0;
        for (int i = 0; i < len; i++) {
            if (inorder[i] == preorder[0]) {
                preIdx = i;
                break;
            }
        }

        int[] subPrePreHalf = Arrays.copyOfRange(preorder, 1, preIdx + 1);
        int[] subInPreHalf = Arrays.copyOfRange(inorder, 0, preIdx);
        res.left = buildTree(subPrePreHalf, subInPreHalf);

        int[] subPrePosHalf = Arrays.copyOfRange(preorder, preIdx + 1, len);
        int[] subInPosHalf = Arrays.copyOfRange(inorder, preIdx + 1, len);
        res.right = buildTree(subPrePosHalf, subInPosHalf);

        return res;
    }


    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal c =
                new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode res = c.buildTree(preorder, inorder);

    }
}
