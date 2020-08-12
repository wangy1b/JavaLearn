package com.wyb.leetcode;

/*
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

给定的有序链表： [-10, -3, 0, 5, 9],
一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

众所周知，一棵二叉搜索树是一棵有根二叉树并且对于所有节点满足特殊的性质：
对于树中任意一个点，它的权值必然 ≥ 所有左子树节点的权值，≤ 所有右子树节点的权值。
因为二叉树具有递归的子结构，二叉搜索树也同理：所有子树也是二叉搜索树。

 */
public class SortedListToBST {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(-10, new ListNode(-3
                , new ListNode(0, new ListNode(5, new ListNode(9)))));
        // printTree(mySortedListToBST(listNode));
        // printTree(sortedListToBSTTwoPtr(listNode));
        printTree(sortedListToBST3(listNode));
    }

    /*
        双指针遍历ListNode找到中间节点，递归处理
     */
    public static ListNode findMid(ListNode head) {
        if (head == null) return null;
        // The pointer used to disconnect the left half from the mid node.
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        ListNode prevPtr = null;
        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }
        return slowPtr;
    }

    /*
     双指针遍历ListNode找到中间节点，递归处理
  */
    private static TreeNode sortedListToBSTTwoPtr(ListNode head) {
        // If the head doesn't exist, then the linked list is empty
        if (head == null) return null;
        // Find the middle element for the list.
        ListNode mid = findMid(head);

        // The mid becomes the root of the BST.
        TreeNode root = new TreeNode(mid.val);
        // Base case when there is just one element in the linked list
        if (head == mid) {
            return root;
        }
        // Recursively form balanced BSTs using the left and right halves of the original list.
        root.left = sortedListToBSTTwoPtr(head);
        root.right = sortedListToBSTTwoPtr(mid.next);
        return root;
    }


    /*
        直接将ListNode转为List，然后转Array
     */
    private static TreeNode mySortedListToBST(ListNode head) {
        List list = new LinkedList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Object[] nums = list.toArray();
        return buildTree(nums, 0, nums.length - 1);
    }


    private static TreeNode buildTree(Object[] nums, int start, int end) {
        if (start > end) return null;
        // mid
        // int mid = (start + end) / 2;
        // int mid = (start + end) >> 1;
        // int mid = (start + end + 1) / 2;
        int mid = (start + end + 1) >> 1;
        TreeNode root = new TreeNode((int) nums[mid]);
        root.left = buildTree(nums, start, mid - 1);
        root.right = buildTree(nums, mid + 1, end);
        return root;
    }


    // 方法 3：中序遍历模拟
    /*
    中序遍历一棵二叉搜索树会有一个非常有趣的结论。
        <中序遍历一棵二叉搜索树的结果是得到一个升序序列。>
    这个方法模拟了二叉搜索树的构造过程，因为我们已经获得有序的链表，所以自然的产生了这样的想法。
    在描述算法之前，先看一下中序遍历是如何获得有序值的。
    基于解决这个问题的中序遍历的思想：
        我们知道中序遍历最左边的元素一定是给定链表的头部，类似地下一个元素一定是链表的下一个元素，以此类推。这是肯定的因为给定的初始链表保证了升序排列。
    在了解了中序遍历二叉搜索树和有序数组的关系之后，让我们来看看算法。

     */


    private static int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }
    private static ListNode publicHead;

    // niubi 但是不懂
    private static TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r + 1) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(publicHead.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        publicHead = publicHead.next;

        // Recurse on the right hand side and form BST out of them
        node.right = convertListToBST(mid + 1, r);
        return node;
    }

    private static TreeNode sortedListToBST3(ListNode head) {
        // Get the size of the linked list first
        int size = findSize(head);

        publicHead = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }

    // ====================================================================================


    private static void printTree(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 核心方案：Queue中取出一个元素，再把期左右孩子放入Queue
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                System.out.print(node.val + " -> ");
                // 左孩子不空，加入queue
                if (node.left != null) queue.add(node.left);
                // 右孩子不空，加入queue
                if (node.right != null) queue.add(node.right);
            }

        }

    }

    // Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }


    // Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
