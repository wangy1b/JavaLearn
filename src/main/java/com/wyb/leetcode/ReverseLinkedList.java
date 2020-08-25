package com.wyb.leetcode;

/*

206. 反转链表
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode listNode = ListNode.generateListNode(nums);
        // ListNode res = reverseList(listNode);
        ListNode res = reverseListRecursive(listNode);
        ListNode.printListNode(res);

    }

    // 迭代
    private static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode p = head;
        ListNode res = new ListNode(p.val);
        while (p.next != null) {
            p = p.next;

            ListNode t = new ListNode(p.val);
            t.next = res;
            res = t;
        }
        return res;
    }


    //  不太明白
    /*

    不妨假设链表为1，2，3，4，5。
    按照递归，当执行reverseList（5）的时候返回了5这个节点，
    reverseList(4)中的p就是5这个节点，
    我们看看reverseList（4）接下来执行完之后，5->next = 4, 4->next = null。
    这时候返回了p这个节点，也就是链表5->4->null，
    接下来执行reverseList（3），代码解析为4->next = 3,3->next = null，这个时候p就变成了，5->4->3->null,
    reverseList(2), reverseList(1)依次类推，p就是:5->4->3->2->1->null

     */
    private static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p;

    }



}
