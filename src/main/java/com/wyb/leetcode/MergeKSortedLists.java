package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
    // 合并两个list
    public ListNode merge2Lists(ListNode listNode1,ListNode listNode2) {
        if (listNode1 == null || listNode2 == null)
            return listNode1 != null ? listNode1 : listNode2;
        ListNode res = new ListNode(Integer.MAX_VALUE);
        ListNode h = res;
        ListNode h1 = listNode1;
        ListNode h2 = listNode2;
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

    public ListNode myMergeKLists0(ListNode[] lists){
        ListNode res = null;
        for (int i = 0; i < lists.length; i++) {
            res = merge2Lists(res, lists[i]);
        }
        return res;
    }

    /**
     * 执行结果：通过
     * 执行用时：308 ms , 在所有 Java 提交中击败了7.77%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了65.44%的用户
     */
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
    /**
     *
     执行用时：114 ms , 在所有 Java 提交中击败了30.37%的用户
     内存消耗：39.9 MB, 在所有 Java 提交中击败了82.10%的用户
     *
     */
    public ListNode myMergeKLists2(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        // 模仿插入排序，将数组第一个list当成已经排好序的结果，
        // 遍历剩下的list,判断其中的值应该在什么位置
        ListNode res = new ListNode(Integer.MAX_VALUE, lists[0]);
        for (int i = 1; i < len; i++) {
            ListNode ptr = lists[i];
            ListNode h = res;
            if (ptr == null) continue;
            if (h.next == null) {
                h.next = ptr;
                ptr = null;
            }
            while (h.next != null && ptr != null) {
                // h 指针移动到“不比他不小”的位置停下，那h指向的下一个节点，就是ptr对应的节点
                while (h.next != null && h.next.val < ptr.val)
                    h = h.next;

                // ptr 指针移动到“不比他大”的位置停下
                while (h.next != null && h.next.val < ptr.val) ptr = ptr.next;

                ListNode hbk = h;
                h.next = new ListNode(ptr.val, hbk.next);
                h = h.next;
                ptr = ptr.next;
            }
            if (h.next == null && ptr != null)
                h.next = ptr;
        }
        return res.next;
    }


    /*
    方法一：顺序合并 思路

    我们可以想到一种最朴素的方法：用一个变量 ans 来维护以及合并的链表，第 ii 次循环把第 ii 个链表和 ans 合并，答案保存到 ans 中。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public ListNode mergeTwoListsOfficial(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    public ListNode mergeKListsOfficial1(ListNode[] lists){
        ListNode res = null;
        for (int i = 0; i < lists.length; i++) {
            res = mergeTwoListsOfficial(res, lists[i]);
        }
        return res;
    }


    /**
     * 方法二：分治合并 思路
     考虑优化方法一，用分治的方法进行合并。

     将 k 个链表配对并将同一对中的链表合并；
     第一轮合并以后， k 个链表被合并成了  k/2 个链表，平均长度为 2n/k，
     然后是  k/4 个链表， k/8 个链表等等；
     重复这一过程，直到我们得到了最终的有序链表。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public ListNode mergeKListsOfficial2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoListsOfficial(merge(lists, l, mid), merge(lists, mid + 1, r));
    }


    /*
    方法三：使用优先队列合并
    思路

    这个方法和前两种方法的思路有所不同，我们需要维护当前每个链表没有被合并的元素的最前面一个，
    k 个链表就最多有 k 个满足这样条件的元素，每次在这些元素里面选取 val 属性最小的元素合并到答案中。
    在选取最小元素的时候，我们可以用优先队列来优化这个过程。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKListsOfficial3(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = ListNode.generateListNode(new int[]{1, 2, 3});
        ListNode listNode2 = ListNode.generateListNode(new int[]{4, 5, 6});
        ListNode listNode3 = ListNode.generateListNode(new int[]{2, 6});
        // ListNode[] lists = new ListNode[]{null, listNode1, null, listNode2, listNode3, null};
        // ListNode[] lists = new ListNode[]{listNode3, listNode2,listNode1};
        // ListNode[] lists = new ListNode[]{null, new ListNode(2), null, listNode3, null, null, null, null, listNode2, listNode1};
        ListNode[] lists = new ListNode[]{listNode1,listNode2};
        MergeKSortedLists m = new MergeKSortedLists();
        ListNode res = m.myMergeKLists0(lists);
        // ListNode res = m.myMergeKLists(lists);
        // ListNode res = m.myMergeKLists2(lists);
        ListNode.printListNode(res);
    }
}
