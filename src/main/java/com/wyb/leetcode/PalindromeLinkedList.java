package com.wyb.leetcode;
/*

234. 回文链表
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

https://leetcode-cn.com/problems/palindrome-linked-list/

 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        int[] nums = {1,0,1};
        ListNode listNode = ListNode.generateListNode(nums);
        System.out.println(isPalindrome(listNode));

    }


    // 判断两个ListNode是否相等
    private static boolean equals(ListNode A,ListNode B) {
        if (A == null || B == null) return false;
        ListNode p = A;
        ListNode q = B;
        Boolean res = true;
        while ( p != null && q != null) {
            if (p.val != q.val ) res = false;
            p = p.next;
            q = q.next;
        }

        if (( p == null && q != null) || (p != null && q == null)) {
            res = false;
        }
        return res;
    }

    private static boolean isPalindrome(ListNode head) {
        if (head == null||head.next == null) return true;
        ListNode p = head;
        // 新ListNode
        ListNode q = null;
        while (p != null) {
            // 新ListNode生成
            ListNode s = new ListNode(p.val);
            s.next = q;
            q = s;
            // 指针p迭代
            p = p.next;
        }

        // 新ListNode 和指针p是否相等
        if ( q != null && head.val == q.val && equals(head,q)) {
            return true;
        }


        return false;
    }

}
