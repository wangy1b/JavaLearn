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
class MyLinkedList2 {

    private static class Node {
        int val;
        Node next;
        Node prev;

        private Node() {
        }

        public Node(int val, Node next,Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public Node head;
    public Node tail;

    public int size = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList2() {
        head = tail = null;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        int res = -1;
        if (index >= size || index < 0) return res;

        int half = (size-1) >> 1;
        // add from head
        if (index <= half ){
            Node h = head;
            for (int idx = 0; h.next != null && idx < index; idx++) {
                h = h.next;
            }
            res = h.val;
        }
        // add from tail
        else {
            Node t = tail;
            for (int idx = size -1; t.prev != null && idx > index; idx--) {
                t = t.prev;
            }
            res = t.val;
        }
        return res;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        if (head == null)
            head = tail = new Node(val, null, null);
        else
            head = head.prev = new Node(val, head,null);
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (tail == null) {
            head = tail = new Node(val, null,null);
        } else
            tail = tail.next = new Node(val, null,tail);
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
        int half = (size - 1) >> 1;
        // add from head
        if (index <= half ){
            Node h = head;
            for (int idx = 0; h.next != null && idx < index; idx++) {
                h = h.next;
            }
            // m 指向其所在位置前后节点
            Node m = new Node(val, h.next, h);
            // 下一节点指向m
            h.next.next = m;
            h.next = m;
        }
        // add from tail
        else {
            Node t = tail;
            for (int idx = size -1; t.prev != null && idx > index; idx--) {
                t = t.prev;
            }
            // m 指向其所在位置前后节点
            Node m = new Node(val, t, t.prev);
            // 前一节点指向m
            t.prev.next = m;
            t.prev = m;

        }
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        // if (index == 0) {
        //     head = head.next;
        //     head.prev = null;
        //     size--;
        //     return;
        // } else

        if (index >= size || index < 0)
            return;

        int half = (size - 1) >> 1;
        // delete from head
        if (index <= half ) {
            Node h = head;
            for (int idx = 0; h.next != null && idx < index; idx++) {
                h = h.next;
            }

            // a(h.prev) -> h -> c (h.next)


            // todo 怎么删除head
            // c -> a
            if (h.next != null)
                h.next.prev = h.prev != null ? h.prev : null;

            // a -> c
            if (h != head)
                h.prev.next = h.next;
            else
                h = h.next;
        }
        // delete from tail
        else {
            Node t = tail;
            for (int idx = size -1; t.prev != null && idx >= index ; idx--) {
                t = t.prev;
            }
            if (t.prev != null)
                t.prev.next = t.next != null ? t.next : null;
            else

            if (t != tail)
                t.next.prev = t.prev ;
            else
                tail.prev = t.prev.prev ;
        }
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList2 linkedList = new MyLinkedList2();
        // linkedList.addAtHead(1);
        // linkedList.addAtHead(2);
        // linkedList.addAtHead(3);
        // linkedList.get(2);
        // linkedList.deleteAtIndex(2);
        //
        // linkedList.addAtTail(10);
        // linkedList.addAtTail(20);
        // linkedList.addAtTail(30);
        // linkedList.get(3);
        // linkedList.addAtHead(10);
        // linkedList.addAtTail(30);
        // linkedList.addAtIndex(1,20);
        // linkedList.get(1);
        // linkedList.deleteAtIndex(1);
        // linkedList.get(1);


        linkedList.addAtHead(10);
        linkedList.deleteAtIndex(0);
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