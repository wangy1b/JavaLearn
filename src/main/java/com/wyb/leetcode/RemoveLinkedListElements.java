package com.wyb.leetcode;

public class RemoveLinkedListElements {
    public static void main(String[] args) {
        // int[] nums = {1,2,6,3,4,5,6};
        int[] nums = {6,6,1};
        ListNode listNode = ListNode.generateListNode(nums);
        ListNode res = removeElements(listNode,6);
        ListNode.printListNode(res);
    }

    private static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode p = head;
        while (p != null ) {
            ListNode t = p.next;
            if (t.val == val && t != null) {
                p.next = t.next;
            }
            p = p.next;
        }
        return head;
    }

}
