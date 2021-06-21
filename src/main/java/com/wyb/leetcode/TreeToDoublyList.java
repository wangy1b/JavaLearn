package com.wyb.leetcode;

import java.util.*;

/*
剑指 Offer 36. 二叉搜索树与双向链表
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
要求不能创建任何新的节点，只能调整树中节点指针的指向。

为了让您更好地理解问题，以下面的二叉搜索树为例：

      4
     /\
   2   5
  /\
 1  3


我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

 head -> 1 <=> 2 <=> 3 <=> 4 <=> 5

特别地，我们希望可以就地完成转换操作。
当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
还需要返回链表中的第一个节点的指针。

注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

注意：此题对比原题有改动。

https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class TreeToDoublyList {
    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    // 步骤：1.中序遍历二叉搜索树 2.形成双向链表 3.首位相连形成双向循环链表
    // 结果：双向循环链表
    // 记录当前节点
    Node currentNode = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        Node res = new Node(Integer.MIN_VALUE);
        helper(root, res);
        res.right.left = null;
        res = res.right;

        // 变成循环链表
        res.left = currentNode;
        currentNode.right = res;
        return res;
    }


    private void helper(Node root, Node res){
        Node h = res;
        if (root != null) {
            // 左子节点
            if (root.left != null) {
                helper(root.left, h);
            }

            // 遍历到最后一个节点
            while (h.right != null) {
                h = h.right;
            }

            // 增加当前节点
            h.right = new Node(root.val, h, null);
            h = h.right;
            currentNode = h;

            // 右子节点
            if (root.right != null) {
                helper(root.right, h);
            }
        }
    }

    public static void main(String[] args) {
        TreeToDoublyList t = new TreeToDoublyList();
        String nums = "4,2,5,1,3";
        // TreeNode root = TreeNode.transArrayToTree(nums);
        Node root = new Node(4, new Node(2,new Node(1),new Node(3)),new Node(5));
        Node res = t.treeToDoublyList(root);
    }
}
