package com.wyb.leetcode;
/*

19. 删除链表的倒数第N个节点
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：给定的 n 保证是有效的。

进阶：你能尝试使用一趟扫描实现吗？
https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        int[] nums = {1};
        ListNode listNode = ListNode.generateListNode(nums);
        ListNode.printListNode(removeNthFromEnd(listNode,1));
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        // 快慢指针求步数
        ListNode slow = head;
        ListNode fast = head.next.next;
        int step = 1;
        while (fast != null && fast.next != null) {
            ++step;

            slow = slow.next;
            fast = fast.next.next;
        }
        // 奇偶判断长度
        int more = fast == null ? 0 :1;
        int len = step * 2 + more;
        System.out.println("len : " + len);
        // 正序顺序
        int nth = len - n + 1;
        System.out.println("nth : " + nth);
        // 构造新ListNode,加个头
        ListNode newList = new ListNode(Integer.MIN_VALUE);
        newList.next = head;
        int i = 1;
        ListNode ptr = newList;
        ListNode prev = ptr;
        ptr = ptr.next;
        while (ptr != null) {
            if ( i == nth){
                prev.next = prev.next.next;
                prev = ptr;
                break;
            }
            prev = ptr;
            if (ptr != null) ptr = ptr.next;
            i++;
        }
        return newList.next;
    }

    /*

    首先我们将添加一个哑结点作为辅助，该结点位于列表头部。
    哑结点用来简化某些极端情况，例如列表中只含有一个结点，或需要删除列表的头部。
    在第一次遍历中，我们找出列表的长度 L。然后设置一个指向哑结点的指针，并移动它遍历列表，直至它到达第(L−n) 个结点那里。
    我们把第(L−n) 个结点的 next 指针重新链接至第(L−n+2) 个结点，完成这个算法。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static ListNode removeNthFromEndOfficial1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
    /*

    上述算法可以优化为只使用一次遍历。我们可以使用两个指针而不是一个指针。
    第一个指针从列表的开头向前移动 n+1 步，而第二个指针将从列表的开头出发。
    现在，这两个指针被 n个结点分开。我们通过同时移动两个指针向前来保持这个恒定的间隔，
    直到第一个指针到达最后一个结点。此时第二个指针将指向从最后一个结点数起的第 n 个结点。
    我们重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static ListNode removeNthFromEndOfficial2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

}
