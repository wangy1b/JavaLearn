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
    // todo 20210625 bst
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        pred = root;
        TreeNode h = root;
        while (h != null) {
            // 找到跟值相等的地方
            if (key == h.val) {
                // 优先找左子节点
                if (h.left != null) {
                    //有左子节点,上提左子节点
                    siftUpLeft(h);
                } else { // 找右子节点
                    // 若没有右子节点
                    if (h.right == null) {
                        if (pred.left.val == h.val)
                            pred.left = null;
                        else
                            pred.right = null;
                        h = null;
                    }
                    // 有右子节点,上提右子节点
                    siftUpRight(h);
                }
                h = null;
            }
            // 值小于root的值就向左
            else if (key < h.val)
                h = deleteNode(h.left, key);
            else // 值大于root的值就向右
                h = deleteNode(h.right, key);
        }
        return root;
    }

    /**
     * 如果在左子节中找，需要在左子节点里找到最大的值，赋值给root即可，然后最大节点设置为null即可；
     * 否则，则需要在右子节点里找到最小的值，赋值给root，然后最小节点设置为? TBD；
     *
     * 由于二叉搜索树的特性，左子节点最大值肯定在最后一个叶子节点，因此返回的只要是最大节点的前一个节点即可;
     */

    TreeNode pred = null;
    private TreeNode findMaxNode(TreeNode root){
        if (root.left == null &&  root.right == null)
            return root;
        TreeNode h = root;
        // 优先选择右子节点
        if (h.right != null) {
            pred = h;
            h = findMaxNode(h.right);
        }
        return h;
    }

    private TreeNode findMinNode(TreeNode root){
        if (root.left == null &&  root.right == null)
            return root;
        TreeNode h = root;
        // 优先选择左子节点
        if (h.left != null) {
            pred = h;
            h = findMinNode(h.left);
        }
        return h;
    }


    // 将其左子节点往上移动
    // 需要在左子节点里找到最大的值，赋值给root即可，然后最大节点设置为null即可；
    private void siftUpLeft(TreeNode root){
        TreeNode leftMax = findMaxNode(root.left);
        // 若为左子节点中的，肯定为最后一个节点
        // 只要先将值改为最大节点的值
        root.val = leftMax.val;
        // 然后将最大节点(即前一个节点，pred的下一个节点)设置为null
        if (leftMax == pred.left)
            pred.left = leftMax.left;
        else
            pred.right = null;
    }

    // 将其右子节点往上移动
    // 需要在右子节点里找到最小的值，赋值给root，然后最小节点设置为? TBD
    private void siftUpRight(TreeNode root){
        // 有子节点
        TreeNode rightMin = findMinNode(root.right);
        root.val = rightMin.val;
        TreeNode rr = rightMin;
        // 递归将子节点上移
        if (rr.right != null) {
            while (rr.right != null) {
                rr = rr.right;
            }
        } else
            pred.left = null;
    }

    public static void main(String[] args) {
        BinarySearchTreeDeleteNode b = new BinarySearchTreeDeleteNode();
        // String nums = "5,3,6,2,4,null,7";
        // String nums = "3,2,4";
        String nums = "1,null,2,null,3,null,4,null,5";
        // String nums = "5,4,null,3,null,2,null,1";
        int key = 3;
        TreeNode root = TreeNode.transArrayToTree(nums);
        TreeNode res = b.deleteNode(root, key);
    }
}
