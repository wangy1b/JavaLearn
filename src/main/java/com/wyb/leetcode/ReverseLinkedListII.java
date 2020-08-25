package com.wyb.leetcode;
/*

92. 反转链表 II
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL

https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode listNode = ListNode.generateListNode(nums);
        ListNode res = reverseBetween(listNode,1,5);
        ListNode.printListNode(res);
    }

    private static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;
        ListNode nListNode = new ListNode(Integer.MIN_VALUE);
        nListNode.next = head;
        ListNode ptr = nListNode;
        int i = 0;
        ListNode res = new ListNode(ptr.val);
        ListNode newPtr = res;
        ListNode tmpReverse = null;
        ListNode r = tmpReverse;
        while (ptr.next != null) {
            ptr = ptr.next;
            i++;
            if (i >= m && i <= n ) {
                ListNode s = new ListNode(ptr.val);
                s.next = tmpReverse;
                tmpReverse = s;
                // 倒序后最后一个节点
                if (i == m + 1) {
                    r = tmpReverse.next;
                }
                // 正序最后一个节点的指针指向倒序结果
                // 指针同时指向最后一个节点，也就是倒序最后一个节点
                if (i== n) {
                    newPtr.next = tmpReverse;
                    newPtr = r;
                }
            } else {
                newPtr.next = new ListNode(ptr.val);
                newPtr = newPtr.next;
            }
        }
        return res.next;
    }

    public static ListNode reverseBetweenOfficialIteration(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
}
