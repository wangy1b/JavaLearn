package com.wyb.leetcode;

import java.util.List;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public ListNode merge2Lists(ListNode[] lists) {
        ListNode res = new ListNode(Integer.MAX_VALUE);
        ListNode h = res;
        ListNode h1 = lists[0];
        ListNode h2 = lists[1];
        // 均不为空
        while (h1 != null && h2 != null) {
            int minVal = Integer.MAX_VALUE;
            if (h1.val < h2.val) {
                minVal = h1.val;
                h1 = h1.next;
            } else {
                minVal = h2.val;
                h2 = h2.next;
            }
            h.next = new ListNode(minVal);
            h = h.next;

        }
        if (h1 == null) { // h1 为空
            h.next = h2;
        } else // h2 为空
            h.next = h1;
        return res.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode(Integer.MAX_VALUE);
        ListNode h = res;
        int j = lists.length;
        while (j > 0) {
            int minVal = Integer.MAX_VALUE;
            // 每次求一轮的最小值，并把当前的list指向下一个节点
            for (int i = 0; i < lists.length; i++) {
                ListNode ptr = lists[i];
                ListNode minPtr = ptr;
                int minIdx = i;
                // ptr不为空
                if (ptr != null) {
                    if (ptr.val <= minVal) {
                        minVal = ptr.val;
                        minPtr = ptr;
                        minIdx = i;
                    }
                } else {
                    j--;
                    break;
                }
                // 每一轮记录下最小值，并把当前的list指向下一个节点
                if (i == lists.length -1 ) {
                    minPtr = minPtr.next;
                    lists[minIdx] = minPtr;
                }
            }

            h.next = new ListNode(minVal);
            h = h.next;
        }
        return res.next;
    }


    public static void main(String[] args) {
        ListNode listNode1 = ListNode.generateListNode(new int[]{1, 4, 5});
        ListNode listNode2 = ListNode.generateListNode(new int[]{1, 3, 4});
        ListNode listNode3 = ListNode.generateListNode(new int[]{2, 6});
        // ListNode[] lists = new ListNode[]{listNode1, listNode2, listNode3};
        ListNode[] lists = new ListNode[]{listNode1, listNode2};
        MergeKSortedLists m = new MergeKSortedLists();
        ListNode res = m.mergeKLists(lists);
        ListNode.printListNode(res);
    }
}
