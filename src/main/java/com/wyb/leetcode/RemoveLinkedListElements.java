package com.wyb.leetcode;
/*

203. 移除链表元素
删除链表中等于给定值 val 的所有节点。

示例:
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5

 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        // int[] nums = {1,2,6,3,4,5,6};
        int[] nums = {};
        ListNode listNode = ListNode.generateListNode(nums);
        ListNode res = removeElements(listNode,6);
        ListNode.printListNode(res);
    }

    private static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode newList = new ListNode(Integer.MIN_VALUE);
        newList.next = head;
        ListNode p = newList;
        while (p.next != null ) {
            ListNode t = p.next;
            if (t.val == val) {
                p.next = t.next;
            } else
                p = p.next;
        }
        return newList.next;
    }

}
