package com.wyb.leetcode;

import java.util.ArrayList;
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

    /**
     *
     * 执行结果：通过
     执行用时：308 ms , 在所有 Java 提交中击败了7.77%的用户
     内存消耗：40.1 MB, 在所有 Java 提交中击败了65.44%的用户

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

    public ListNode myMergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        ListNode res = new ListNode(Integer.MAX_VALUE);
        ListNode h = res;
        int flag = len;
        while (flag > 0) {
            int minVal = Integer.MAX_VALUE;
            ListNode minPtr = null;
            int minIdx = 0;
            // 每次求一轮的最小值，并把当前的list指向下一个节点
            for (int i = 0; i < len; i++) {
                ListNode ptr = lists[i];
                // ptr不为空
                if (ptr != null) {
                    if (ptr.val < minVal) {
                        minVal = ptr.val;
                        minPtr = ptr;
                        minIdx = i;
                    }
                }
            }
            // 判断minPtr是否为空：
            // 1.当前list一开始就为null
            // 2.当前list的节点已经推到null
            if (minPtr == null) {
                flag--;
                break;
            }

            // 每一轮记录下最小值，并把当前的list指向下一个节点
            minPtr = minPtr.next;
            lists[minIdx] = minPtr;
            // 追加到结果上
            h.next = new ListNode(minVal);
            h = h.next;
        }
        return res.next;
    }

    // 「原地调整链表元素的 next 指针完成合并」
    public ListNode myMergeKLists2(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        // 模仿插入排序，将数组第一个list当成已经排好序的结果，
        // 遍历剩下的list,判断其中的值应该在什么位置
        ListNode res = lists[0];
        ListNode h = res;
        for (int i = 1; i < len; i++) {
            ListNode ptr = lists[i];
            ListNode hPrev = new ListNode(Integer.MAX_VALUE,ptr);
            while (h != null && ptr != null) {
                // h 指针移动到“比他大”的位置停下
                while (h.val <= ptr.val) {
                    hPrev = h;
                    h = h.next;
                }
                // ptr 指针移动到“不比他大”的位置停下
                // todo ptr 比h 小的有部分丢掉
                while (h.val <= ptr.val) ptr = ptr.next;
                hPrev.next = new ListNode(ptr.val ,h);
                h = hPrev.next;
                ptr = ptr.next;


            }
        }
        return res;
    }


    public static void main(String[] args) {
        ListNode listNode1 = ListNode.generateListNode(new int[]{1, 4, 5});
        ListNode listNode2 = ListNode.generateListNode(new int[]{1, 3, 4});
        ListNode listNode3 = ListNode.generateListNode(new int[]{2, 6});
        // ListNode[] lists = new ListNode[]{null, listNode1, null, listNode2, listNode3, null};
        ListNode[] lists = new ListNode[]{listNode3, listNode2};
        MergeKSortedLists m = new MergeKSortedLists();
        // ListNode res = m.myMergeKLists(lists);
        ListNode res = m.myMergeKLists2(lists);
        ListNode.printListNode(res);
    }
}
