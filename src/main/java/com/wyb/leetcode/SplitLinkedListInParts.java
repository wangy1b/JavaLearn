package com.wyb.leetcode;

/*
725. 分隔链表

给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。

每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。

这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。

返回一个符合上述规则的链表的列表。

举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]

示例 1：

输入:
root = [1, 2, 3], k = 5
输出: [[1],[2],[3],[],[]]
解释:
输入输出各部分都应该是链表，而不是数组。
例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
示例 2：

输入:
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
解释:
输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 

提示:

root 的长度范围： [0, 1000].
输入的每个节点的大小范围：[0, 999].
k 的取值范围： [1, 50].

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class SplitLinkedListInParts {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 4;

        // int[] nums = {1, 2, 3, 4};
        // int k = 5;
        ListNode listNode = ListNode.generateListNode(nums);
        ListNode[] res = splitListToParts(listNode, k);
        for (ListNode re : res) {
            ListNode.printListNode(re);
        }

    }


    private static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode p = root;
        while (p != null) {
            p = p.next;
            len++;
        }
        int avg = len / k;
        int reminder = len % k;
        int maxSize = avg, maxCnt = k, minSize = 0, minCnt = 0;
        if (reminder > 0) {
            maxSize = avg + 1;
            maxCnt = reminder;
            minSize = avg;
            minCnt = k - maxCnt;
        }


        int[] nums_res = new int[k];
        int temp = 0;
        for (int i = 1; i <= k; i++) {
            if (i <= maxCnt) {
                temp = i * maxSize - 1;
            } else {
                temp = maxCnt * maxSize + (i - maxCnt) * minSize - 1;
                temp = temp > len - 1 ? len - 1 : temp;
            }
            nums_res[i - 1] = temp;
        }

        // todo
        p = root;
        for (int i = 0, j = 0; i < len; i++) {
            ListNode head = root;
            if (i == nums_res[j]) {
                // 这个地方累进保留测了半天，还没搞懂
                res[j] = head;
                ListNode q = p;
                p = p.next;
                q.next = null;
                j++;
                root = p;
                continue;
            }
            p = p.next;
        }
        return res;
    }
}
