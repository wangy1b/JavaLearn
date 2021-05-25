package com.wyb.leetcode;


/*



设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) —— 将元素 x 推入栈中。
pop() —— 删除栈顶的元素。
top() —— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。
 

示例:

输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
 

提示：

pop、top 和 getMin 操作总是在 非空栈 上调用。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/min-stack
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 使用一个数组[val,min]保存当前值和最小值,放进一个链表里
 *
 */
class MinStackIII {

    private class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            this.value = x;
            this.min = min;
            next = null;
        }
    }

    private Node stack;
    private int int_min = Integer.MIN_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStackIII() {
    }

    public void push(int val) {
        Node h = stack;
        if (h == null) {
            stack = new Node(val, val);
        } else {
            int tmp_min = val < h.min ? val : h.min;
            Node c = new Node(val, tmp_min);
            c.next = h;
            stack = c;
        }
    }

    public void pop() {
        Node h = stack;
        if (h != null) {
            Node c = h.next;
            h.next = null;
            stack = c;
        }
    }

    public int top() {
        Node h = stack;
        if (h != null) {
            return h.value;
        }
        return int_min;
    }

    public int getMin() {
        Node h = stack;
        if (h != null) {
            return h.min;
        }
        return int_min;
    }


    public static void main(String[] args) {
        MinStackIII t = new MinStackIII();
        // for (int i = 0; i < 10; i++) {
        //     t.push(1);
        // }
        t.push(22);
        t.push(33);
        // t.push(2);
        // t.push(0);
        // t.push(3);
        // t.push(0);
        // System.out.println(t.top());
        t.pop();
        System.out.println(t.top());
        t.pop();
        System.out.println(t.getMin());
        // t.pop();
        // System.out.println(t.getMin());
        // System.out.println(t.top());
        // System.out.println(t.getMin());

        // t.push(-10);
        // t.push(14);
        // System.out.println(t.getMin());
        // System.out.println(t.getMin());
        // t.push(-20);
        // System.out.println(t.getMin());
        // System.out.println(t.getMin());
        // t.top();
        // System.out.println(t.getMin());
        // t.pop();
        // t.push(10);
        // t.push(-7);
        // System.out.println(t.getMin());
        // t.push(-7);
        // t.pop();
        // t.top();
        // System.out.println(t.getMin());
        // t.pop();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */