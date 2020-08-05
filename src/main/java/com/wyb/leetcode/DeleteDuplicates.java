package com.wyb.leetcode;

import java.util.Collections;
import java.util.HashMap;

public class DeleteDuplicates {

    public static void main(String[] args) {
        // ListNode listNode1 = new ListNode(1);
        // ListNode listNode2 = new ListNode(1);
        // ListNode listNode3 = new ListNode(2);
        // listNode1.next = listNode2;
        // listNode2.next = listNode3;

        // ListNode listNode1 = new ListNode(1);
        // ListNode listNode2 = new ListNode(1);
        // ListNode listNode3 = new ListNode(2);
        // ListNode listNode4 = new ListNode(3);
        // ListNode listNode5 = new ListNode(3);
        // listNode1.next = listNode2;
        // listNode2.next = listNode3;
        // listNode3.next = listNode4;
        // listNode4.next = listNode5;
        // [-3,-1,0,0,0,3,3]
        ListNode listNode1 = new ListNode(-3);
        ListNode listNode2 = new ListNode(-1);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(0);
        ListNode listNode6 = new ListNode(3);
        ListNode listNode7 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;


        // printListNode(listNode1);
        printListNode(deleteDuplicates(listNode1));

    }


     // * Definition for singly-linked list.
     private static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

     private static void printListNode(ListNode head){
        while (head != null){
            System.out.print( head.val +" -> " );
            head = head.next;
        }
     }


    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode p1 = head;
        while (p1.next != null){
            if (p1.val == p1.next.val) {
                p1.next = p1.next.next;
            } else {
                p1 = p1.next;
            }
        }
        return head;
    }
}
