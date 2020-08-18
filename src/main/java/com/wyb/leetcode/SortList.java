package com.wyb.leetcode;


/*

在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */

public class SortList {


    // Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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
    }


    private static ListNode sortList(ListNode head) {
        ListNode p = head;
        int j = Integer.MAX_VALUE;
        if (p == null) return head;
        while (true) {
            if ( p.next == null) return head;
            int i = 0;
            while (p != null ) {
                // swap
                if (p.val > p.next.val) {
                    int tmp = p.next.val;
                    p.next.val = p.val;
                    p.val = tmp;
                }
                // next
                p = p.next;
                i++;
                // back to head
                if (p.next == null || i == j) {
                    j = i;
                    p = head;
                    break;
                }
            }
            j--;
            if (j == 0){
                break;
            }

        }
        return head;
    }


    public static void main(String[] args) {
        // int[] nums = {4,2,1,3};
        // int[] nums = {-1, 5, 3, 4, 0};
        int[] nums = {-1};
        // ListNode listNode = generateListNode(nums);
        // System.out.println(sortList(listNode).toString());
        ListNode listNode = null;
        sortList( listNode).toString();
    }


    private static ListNode generateListNode(int[] nums) {
        ListNode listNode = new ListNode(Integer.MAX_VALUE);
        ListNode p = listNode;
        for (int i = 0; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        return listNode.next;
    }


}
