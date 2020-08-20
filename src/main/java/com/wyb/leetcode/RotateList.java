package com.wyb.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*

61. 旋转链表

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
示例 2:

输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class RotateList {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        // int[] nums = {1,2};
        ListNode listNode = ListNode.generateListNode(nums);
        // System.out.println(listNode.toString());
        // System.out.println(rotateRight(listNode,1).toString());
        ListNode.printListNode(rotateRight(listNode,2));
       // rotateRightOfficial(listNode,2);
        // System.out.println(test(listNode,2).toString());
    }

    private static ListNode test(ListNode head, int k){
        ListNode curr = head;
        int n = 0;
        HashMap<Integer,ListNode> hash = new HashMap();
        ListNode p = head;
        // 遍历并将数据存入map
        while (curr != null ) {
            hash.put(++n, curr);
            p = curr;
            curr = curr.next;
        }
        k = k % n; // 去重
        // 通过查找map对链表进行操作
        // hash.get(n).next = head; // 链表最后一项指向头部形成环
        p.next = head; // 链表最后一项指向头部形成环
        head = hash.get(n - k).next; // 定位新的头节点
        hash.get(n - k).next = null; // 打断链表环
        return head;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null|| k == 0 ) return head;
        // 遍历一遍找出最后一个节点和长度
        int len = 1;
        ListNode p = head;
        while (p.next != null){
            p = p.next;
            len++;
        }
        // k  = (k == len) ? k :  k%len;
        k  = k % len;

        if (k == 0) return head;

        // 形成环形ListNode
        p.next = head;

        // 找到开始节点前一个节点
        ListNode s = head;
        int j = 0;
        while (j < len - k -1 ){
            // System.out.println(j);
            s = s.next;
            j++;
        }
        // 下一节点为开始节点
        ListNode res = s.next ;
        // 环形节点下一个为空，断开环
        s.next = null;
        return res;
    }

    public static ListNode rotateRightOfficial(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for(n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }
}
