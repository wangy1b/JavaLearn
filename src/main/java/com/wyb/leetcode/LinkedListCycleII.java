package com.wyb.leetcode;

/*

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
说明：不允许修改给定的链表。

 

示例 1：

输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。


示例 2：

输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。


示例 3：

输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        // ListNode listNode = new ListNode(1);
        // ListNode listNode1 = new ListNode(2);
        // ListNode listNode2 = new ListNode(3);
        // ListNode listNode3 = new ListNode(4);
        //
        // listNode.next = listNode1;
        // listNode1.next = listNode2;
        // listNode2.next = listNode3;
        // listNode3.next = listNode1;
        // listNode2.next = listNode3;

        // int[] nums = new int[]{1,2,3};
        int[] nums = new int[]{-1,-7,7,-4,19,6,-9,-5,-2,-5};
        GenerateArrayToListNode generateArrayToListNode= new GenerateArrayToListNode();
        // ListNode listNode = generateArrayToListNode.GenerateArrayToListNode(nums);
        ListNode listNode = generateArrayToListNode.GenerateArrayToListNode(nums,9);

        // System.out.println(detectCycle(listNode).val);
        System.out.println(detectCycleOfficial(listNode).val);
    }

    // todo 超出时间限制
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("有环");

        boolean f = true;
        while( f ) {
            while (fast.next != null && f ) {
                if( head != fast) {
                    fast = fast.next;
                }
                else f = false;
                if ( slow == fast) break;
            }
            if (f) head = head.next;
        }

        return head;
    }



    private static ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

    public static ListNode detectCycleOfficial(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
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

    private static class GenerateArrayToListNode{
        ListNode GenerateArrayToListNode(int[] array) {
            ListNode listNode = new ListNode(-1);
            ListNode p = listNode;
            for (int i = 0; i < array.length ; i++) {
                p.next = new ListNode(array[i]);
                p = p.next;
            }
            return listNode.next;
        }

        ListNode GenerateArrayToListNode(int[] array, int pos) {
            ListNode listNode = new ListNode(Integer.MIN_VALUE);
            ListNode p = listNode;
            ListNode entrence = null;
            for (int i = 0; i < array.length ; i++) {
                p.next = new ListNode(array[i]);
                p = p.next;
                if (i == pos ) {
                    entrence = p;
                }

            }
            p.next = entrence;
            return listNode.next;
        }
    }
}
