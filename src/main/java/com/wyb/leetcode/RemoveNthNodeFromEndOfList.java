package com.wyb.leetcode;
/*

19. 删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：给定的 n 保证是有效的。

进阶：你能尝试使用一趟扫描实现吗？

 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode listNode = ListNode.generateListNode(nums);
        ListNode.printListNode(removeNthFromEnd(listNode,2));
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null|| head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        int step = 1;
        int len = 0;
        while (fast != null && fast.next != null) {
            ++step;

            slow = slow.next;
            fast = fast.next.next;
        }

        int more = fast == null ? 0 :1;
        len = step * 2 + more;

        System.out.println("len : " + len);

        int nth = len - n + 1;
        System.out.println("nth : " + nth);
        ListNode ptr =  nth > len/2 ?  head:slow;
        int i = 1;
        while (ptr.next != null) {
            ListNode next = ptr.next;
            if ( i+2 == nth){
                next.next = null;
                ptr.next = ptr.next.next;
            }
            ptr = ptr.next;
            i++;
        }


        return null;
    }
}
