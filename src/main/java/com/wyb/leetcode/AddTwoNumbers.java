package com.wyb.leetcode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        int[] nums1 = {5};
        int[] nums2 = {5};
        ListNode l1 = ListNode.generateListNode(nums1);
        ListNode l2 = ListNode.generateListNode(nums2);
        ListNode res = addTwoNumbers(l1,l2);
        ListNode.printListNode(res);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode res = new ListNode(Integer.MAX_VALUE);
        ListNode p3 = res;
        int overfloor = 0 ;
        int sum = 0;
        while (p1 != null || p2 != null) {
            int v1 = p1 != null ? p1.val:0;
            int v2 = p2 != null ? p2.val:0;
            sum = v1 + v2  + overfloor ;
            overfloor = sum / 10 > 0 ? 1 : 0;
            sum %= 10;
            p3.next = new ListNode(sum);
            p3 = p3.next;

            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        if (overfloor == 1) {
            p3.next = new ListNode(overfloor);
        }
        return res.next;
    }
}
