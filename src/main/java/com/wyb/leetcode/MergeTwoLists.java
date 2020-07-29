package com.wyb.leetcode;

public class MergeTwoLists {
    public static void main(String[] args) {
        // 1->2->4, 1->3->4
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node111 = new ListNode(4);

        //节点指向下一个节点
        node1.next = node11;
        node11.next = node111;

        ListNode node2 = new ListNode(1,new ListNode(3,new ListNode(4)));

        // printNode(node1);

        // mergeTwoLists(node1,node2);
        printNode(mergeTwoLists(node1,node2));

    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //打印输出方法
    public static void printNode(ListNode node){
        while (node != null) {
            System.out.print(node.val + " -> " );
            node = node.next;
        }
        System.out.println();
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode prehead = new ListNode(-1);
        // prev 为prehead 的指针
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
            // printNode(prehead);
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

}
