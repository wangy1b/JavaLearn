package com.wyb.leetcode;
/*

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

 
示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。


示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。


示例 3：
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/linked-list-cycle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        // listNode2.next = listNode;
        listNode2.next = listNode3;
        System.out.println(hasCycle(listNode));


    }

    private static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        int cnt = 1;

        ListNode slow = new ListNode(Integer.MAX_VALUE);
        slow.next = head;
        // fast1.next = head;
        ListNode fast = head;


        while (slow.next != null && fast.next  != null) {
            if (cnt > 2000) break;
            // System.out.print(head.val + "->");
            if (slow.val == fast.val) {
                System.out.println(cnt);
                return true;
            }

            if (slow.next == null || fast.next.next == null) {
                System.out.println(cnt);
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

            cnt++;
        }

        System.out.println(cnt);
        return false;

    }

    private static boolean hasCycleOfficial(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    // * Definition for singly-linked list.
    private static  class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {

            val = x;

            next = null;
        }
    }

}
