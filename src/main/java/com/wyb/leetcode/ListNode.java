package com.wyb.leetcode;


// * Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int x,ListNode next) { this.val = x;this.next = next; }

    static ListNode generateListNode(int[] nums) {
        ListNode listNode = new ListNode(Integer.MAX_VALUE);
        ListNode p = listNode;
        for (int i = 0; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        return listNode.next;
    }

    static void printListNode(ListNode head){
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println();
    }
}

