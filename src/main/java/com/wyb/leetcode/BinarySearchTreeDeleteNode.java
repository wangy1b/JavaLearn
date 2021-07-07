package com.wyb.leetcode;

import java.util.*;

/*

450. 删除二叉搜索树中的节点
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
说明： 要求算法时间复杂度为 O(h)，h 为树的高度。

示例:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7

https://leetcode-cn.com/problems/delete-node-in-a-bst/

 */
public class BinarySearchTreeDeleteNode {
    TreeNode pred;

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        root = new TreeNode(Integer.MAX_VALUE, root, null);
        TreeNode h = root;
        pred = h;
        while (h != null) {
            // 找到跟值相等的地方
            if (key == h.val) {
                helper(h);
                break;
            } else if (key < h.val) {  // 值小于root的值就向左
                pred = h;
                h = h.left;
            } else {// 值大于root的值就向右
                pred = h;
                h = h.right;
            }
        }
        return root.left;
    }

    // 针对要删除的节点子节点个数不同，需要分三种情况来处理：
    // 第一种，如果要删除的节点没有子节点，只需要将父节点指向null
    // 第二种，如果要删除的节点只有一个子节点，只需更新父节点中，指向要删除的节点的指针的子节点即可
    // 第三种，如果要删除的节点有两个子节点。
    //     1.需要找到这个节点的右子树上的最小节点，把它替换到要删除的节点上，然后再去删除最小节点，
    //       因为这个节点肯定没有左子节点，所以可以利用上面两种情况来删除这个节点
    // 或者：
    //     2.需要找到这个节点的左子树上的最大节点，把它替换到要删除的节点上，然后再去删除最大节点，
    //       因为这个节点肯定没有右子节点，所以可以利用上面两种情况来删除这个节点
    private void helper(TreeNode h) {
        //  第一种，如果要删除的节点没有子节点，只需要将父节点指向null
        if (h.left == null && h.right == null) {
            if (pred.left != null && pred.left.val == h.val)
                pred.left = null;
            else
                pred.right = null;
            // 第二种，如果要删除的节点只有一个子节点，只需更新父节点中，指向要删除的节点的指针的子节点即可
        } else if (h.left == null || h.right == null) {
            if (pred.left != null && pred.left.val == h.val)
                pred.left = h.left != null ? h.left : h.right;
            else
                pred.right = h.left != null ? h.left : h.right;
        } else { //第三种，如果要删除的节点有两个子节点。
            // 1.需要找到这个节点的右子树上的最小节点，把它替换到要删除的节点上，然后再去删除最小节点，
            siftUpRight(h);
            // 2.需要找到这个节点的左子树上的最大节点，把它替换到要删除的节点上，然后再去删除最大节点，
            // siftUpLeft(h);
        }
    }

    /**
     * 如果在左子树中找，需要在左子树里找到最大的值，赋值给root即可，然后最大节点设置为null即可；
     * 由于二叉搜索树的特性，左子节点中的最大值肯定在最后一个叶子节点;
     */
    private TreeNode findMaxNode(TreeNode h) {
        if (h.left == null && h.right == null)
            return h;
        // 优先选择右子节点
        while (h.right != null) {
            pred = h;
            h = h.right;
        }
        return h;
    }

    /**
     * 如果在右子树中找，需要在右子树里找到最小的值，赋值给root即可，然后最小节点设置为null即可；
     * 由于二叉搜索树的特性，右子树中的最大值肯定在最后一个叶子节点;
     */
    private TreeNode findMinNode(TreeNode root) {
        if (root.left == null && root.right == null)
            return root;
        // 优先选择左子节点
        while (root.left != null) {
            pred = root;
            root = root.left;
        }
        return root;
    }


    // 将其左子节点往上移动
    // 需要在左子节点里找到最大的值，赋值给root即可，然后最大节点设置为null即可；
    private void siftUpLeft(TreeNode root) {
        // 需要找到这个节点的左子树上的最大节点，把它替换到要删除的节点上，然后再去删除最大节点，
        pred = root;
        TreeNode leftMax = findMaxNode(root.left);
        root.val = leftMax.val;
        // 因为 leftMax 这个节点肯定没有右子节点
        //  类似第一种，如果要删除的节点没有子节点，只需要将父节点指向null
        if (leftMax.left == null) {
            if (pred.left != null && pred.left.val == leftMax.val)
                pred.left = null;
            else
                pred.right = null;
            // 类似第二种，如果要删除的节点只有一个子节点，只需更新父节点中，指向要删除的节点的指针的子节点即可
        } else if (leftMax.left != null) {
            if (pred.left != null && pred.left.val == leftMax.val)
                pred.left = leftMax.left;
            else
                pred.right = leftMax.left;
        }
    }

    // 将其右子节点往上移动
    // 需要在右子节点里找到最小的值，赋值给root，然后最小节点设置为? TBD
    private void siftUpRight(TreeNode root) {
        //2.需要找到这个节点的左子树上的最大节点，把它替换到要删除的节点上，然后再去删除最大节点，
        pred = root;
        TreeNode rightMin = findMinNode(root.right);
        root.val = rightMin.val;
        // 因为 rightMin 这个节点肯定没有左子节点，所以可以利用上面两种情况来删除这个节点
        //  类似第一种，如果要删除的节点没有子节点，这里只需判断右子节点在不在，只需要将父节点指向null
        if (rightMin.right == null) {
            if (pred.left != null && pred.left.val == rightMin.val)
                pred.left = null;
            else
                pred.right = null;
            // 类似第二种，如果要删除的节点只有一个子节点，只需更新父节点中，指向要删除的节点的指针的子节点即可
        } else if (rightMin.right != null) {
            if (pred.left != null && pred.left.val == rightMin.val)
                pred.left = rightMin.right;
            else
                pred.right = rightMin.right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeDeleteNode b = new BinarySearchTreeDeleteNode();
        // String nums = "5,3,6,2,4,null,7";
        // String nums = "3,2,4";
        // String nums = "1,null,2,null,3,null,4,null,5";
        // String nums = "5,4,null,3,null,2,null,1";
        // String nums = "3";
        String nums = "1,null,2";
        int key = 1;
        TreeNode root = TreeNode.transArrayToTree(nums);
        TreeNode res = b.deleteNode(root, key);
    }
}
