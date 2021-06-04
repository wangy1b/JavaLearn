package com.wyb.leetcode;

/**
 * 1206. 设计跳表
 * 不使用任何库函数，设计一个跳表。
 * <p>
 * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * <p>
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * <p>
 * 在本题中，你的设计应该要包含这些函数：
 * <p>
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * <p>
 * 样例:
 * <p>
 * Skiplist skiplist = new Skiplist();
 * <p>
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * 约束条件:
 * <p>
 * 0 <= num, target <= 20000
 * 最多调用 50000 次 search, add, 以及 erase操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-skiplist
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Skiplist {
    class Node {
        int val;
        Node next;
        Node nextLevelNode;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
            this.nextLevelNode = null;
        }
    }

    int level;
    int length;
    Node head;


    public Skiplist() {

    }

    public boolean search(int target) {
        return false;
    }

    /**
     * 结构
     * level 1:  14 -------------------> 50
     *           |					     |
     * level 2: 14 -------> 34 -------> 50 -------------> 72
     *          |			|			| 				  |
     * level 3: 14 -> 23 -> 34 -> 43 -> 50 -> 59 -> 66 -> 72
     */
    public void add(int num) {
        Node h = head;
        // head == null
        if (h == null)
            h = new Node(num);
        else { //head != null
            // 添加在右侧
            if (num >= h.val && h.next == null)
                h.next = new Node(num);
                // 添加在左侧
            else if (num < h.val && h.next == null) {
                Node tmp = h;
                h = new Node(num, tmp);
            }
            // 以上为开始两个节点的增加过程

            // 以下为已经有多个节点存在的情况下新节点的增加过程
            // h.next != null
            Node lastLevelHead = h;
            Node lastLevelTail = h.next;
            // 首尾节点没有下一层节点，那就需要把节点加入中间，生成新一层
            if (lastLevelHead.nextLevelNode == null && lastLevelTail.nextLevelNode == null) {
                while (h.next != null) {
                    // 在当前这个节点和下一个节点之间
                    // 添加在右侧
                    if (num >= h.val && num < h.next.val) {
                        Node currentLevelHead = h;
                        // 找到当前节点该放的位置
                        while (num >= h.val && h != lastLevelTail) {
                            h = h.next;
                        }
                        // 如果不是最后一个节点
                        if (h.next != lastLevelTail) {
                            Node t = h.next;
                            h.next = new Node(num, t);
                        }
                        // 如果当前节点为最后一个节点
                        // 那把当前节点的头节点添加到这层节点的头节点的下一层节点
                        else if (h.next == lastLevelTail) {
                            Node t = h.next;
                            h.next = new Node(num, t);
                            lastLevelHead.nextLevelNode = currentLevelHead;
                        }

                    } else if (num < h.val) {  // 在当前这个节点左边
                        Node o = h;
                        h = new Node(num, o);
                        // 如果当前节点为最后一个节点
                        // 那把当前节点的头节点添加到这层节点的头节点的下一层节点
                        if (h.next == null) {
                            lastLevelHead.nextLevelNode = h;
                            // 把以上所有层级的head都改为以当前节点开始的
                            Node preHead = head;
                            while (preHead.nextLevelNode != null && preHead.val != num) {
                                preHead.val = num;
                                preHead = preHead.nextLevelNode;
                            }

                        }

                    }

                    h = h.next;
                }

            } else { // 否则就应该加在这一层

            }

        }
        length++;

    }

    public boolean erase(int num) {
        return false;
    }


    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();

        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.search(0);   // 返回 false
        skiplist.add(4);
        skiplist.search(1);   // 返回 true
        skiplist.erase(0);    // 返回 false，0 不在跳表中
        skiplist.erase(1);    // 返回 true
        skiplist.search(1);   // 返回 false，1 已被擦除
    }
}
