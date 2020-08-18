package com.wyb.leetcode;

import java.util.*;

public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        // int[] nums1 = {4,1,8,4,5};
        int[] nums1 = {3};
        ListNode listNode1 = generateListNode(nums1);
        System.out.println("listNode1 : " + listNode1.toString());

        // int[] nums2 = {5,0,1,8,4,5};
        int[] nums2 = {2,3};
        ListNode listNode2 = generateListNode(nums2);
        System.out.println("listNode2 : " + listNode2.toString());

        // ListNode intersectionNode = getIntersectionNode1(listNode1,listNode2);
        // ListNode intersectionNode = getIntersectionNode2(listNode1,listNode2);
        ListNode intersectionNode = getIntersectionNode3(listNode1,listNode2);
        System.out.println("intersectionNode : " + intersectionNode.toString());

    }

    /*
    方法一: 暴力法
    对链表A中的每一个结点 ai，遍历整个链表 B 并检查链表 B 中是否存在结点和 ai相同。

    复杂度分析
    时间复杂度 : (mn)(mn)。
    空间复杂度 : O(1)O(1)。
     */

    private static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode pB = headB;
            while (pB != null) {
                if (headA.equals(pB)) {
                    return headA;
                }
                pB = pB.next;
            }
            headA = headA.next;
        }
        return null;
    }


    /*
    方法二: 哈希表法
    遍历链表 A 并将每个结点的地址引用存储在哈希表中。然后检查链表 B 中的每一个结点bi是否在哈希表中。若在，则 bi为相交结点。

    复杂度分析
    时间复杂度 : O(m+n)。
    空间复杂度 : O(m) 或 O(n)。
     */
    //todo not finished
    private static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();;
        while (headA != null) {
            visited.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            // contains 方法在这不能正常使用
            if (visited.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /*

    方法三：双指针法
    创建两个指针 pA 和 pB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
    当 pA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pB 到达链表的尾部时，将它重定位到链表 A 的头结点。
    若在某一时刻 pA 和 pB 相遇，则 pA/pB 为相交结点。
    想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。
    由于 B.length (=4) < A.length (=6)，pB 比 pA少经过 22 个结点，会先到达尾部。将 pB 重定向到 A 的头结点，pA 重定向到 B 的头结点后，
    pB 要比 pA 多走 2 个结点。因此，它们会同时到达交点。
    如果两个链表存在相交，它们末尾的结点必然相同。因此当 pA/pB 到达链表结尾时，记录下链表 A/B 对应的元素。若最后元素不相同，则两个链表不相交。

    复杂度分析
    时间复杂度 : O(m+n)。
    空间复杂度 : O(1)。
     */

    private static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode pA = headA;
        ListNode pB = headB;
        while (true){
            // if (pA == pB) {
            if (pA.equals(pB)) {
                return pA;
            }

            pA =  pA.next == null ? headB : pA.next;
            pB =  pB.next == null ? headA : pB.next;

            if (pA == headB && pB == headA) return null;

        }
    }


    // * Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            ListNode p = this;
            StringBuffer stringBuffer = new StringBuffer();
            while (p != null) {
                stringBuffer.append(p.val + "->");
                p = p.next;
            }
            return stringBuffer.toString();
        }

        @Override
        public boolean equals(Object obj) {
            ListNode l = (ListNode) obj;
            ListNode c = this;

            if (l == null || c == null) return false;
            return isEquals(l,c);
        }

        private boolean isEquals(ListNode l, ListNode c) {
            if (l == null && c == null) return true;
            if (l == null || c == null) return false;
            if (l.val == c.val) {
                return isEquals(l.next, c.next);
            }
            else
                return false;

        }
    }


    private static ListNode generateListNode(int[] nums) {
        ListNode listNode = new ListNode(Integer.MIN_VALUE);
        ListNode p = listNode;
        for (int i = 0; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        return listNode.next;
    }
}
