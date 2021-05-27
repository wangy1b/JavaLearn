package com.wyb.leetcode;

/**
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。
 * 单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。
 * 假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。
 * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 * <p>
 * https://leetcode-cn.com/problems/design-linked-list/
 */
class MyLinkedList {

    private static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public Node head = null;

    public int size = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size || index < 0) return -1;
        Node h = head;

        for (int idx = 0; h.next != null && idx < index; idx++) {
            h = h.next;
        }
        return h.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node h = head;
        Node t = new Node(val, h);
        head = t;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (head == null) {
            head = new Node(val, null);
            size++;
            return;
        }
        Node h = head;
        while (h.next != null) {
            h = h.next;
        }
        h.next = new Node(val, null);
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        // 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
        // 如果 index 大于链表长度，则不会插入节点。
        // 如果index小于0，则在头部插入节点。
        if (index > size) return;
        else if (index <= 0) {
            addAtHead(val);
            return;
        } else if (index == size) {
            addAtTail(val);
            return;
        }
        Node h = head;
        for (int idx = 0; h.next != null && idx < index - 1; idx++) {
            h = h.next;
        }
        h.next = new Node(val, h.next);
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index == 0) {
            head = head.next;
            size--;
            return;
        } else if (index >= size || index < 0)
            return;

        Node h = head;
        for (int idx = 0; h.next != null && idx < index - 1; idx++) {
            h = h.next;
        }

        h.next = h.next.next != null ? h.next.next : null;
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        // linkedList.addAtHead(1);
        // linkedList.addAtTail(3);
        // linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        // linkedList.get(1);            //返回2
        // linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        // linkedList.get(1);            //返回3
        // linkedList.addAtHead(1);
        // linkedList.addAtIndex(0,10);
        // linkedList.addAtIndex(0,20);
        // linkedList.addAtIndex(1,30);
        linkedList.addAtTail(10);
        // linkedList.addAtTail(20);
        // linkedList.deleteAtIndex(2);
        linkedList.get(0);
    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */