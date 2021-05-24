package com.wyb.leetcode;


import com.wyb.Algorithm.QuickSort;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/*

理解错了，擦。。。。


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
class MinStack {
    int[] elements;
    int default_length = 10;
    int idx = 0;
    int arr_size = 0;
    int min_idx = 0;


    private void grow() {
        int old_size = arr_size;
        arr_size <<= 1;
        int[] arr = new int[arr_size];
        System.arraycopy(elements,0,arr,0,old_size);
        elements = arr;
    }


    /** initialize your data structure here. */
    public MinStack() {
        this.arr_size = default_length;
        this.elements = new int[default_length];
    }

    public MinStack(int size) {
        this.arr_size = size;
        this.elements = new int[size];

    }

    public void push(int val) {
        if(idx == arr_size-1)
            grow();
        min_idx = elements[min_idx] <= val ? min_idx : idx;
        elements[idx++] = val;
    }

    public void pop() {
        elements[--idx] = 0;
        // pop出数据的，并且当前的idx与最小值的min_idx相同的时候，需要重新求最小值
        if (idx - 1 == min_idx) {
            for (int i = 0; i < idx; i++) {
                min_idx = elements[i] < elements[min_idx] ? i : min_idx;
            }
        }
    }

    public int top() {
        return elements[idx-1];
    }

    public int getMin() {
        return elements[min_idx];
    }
    public static void main(String[] args) {
        MinStack obj = new MinStack();
        MinStack t = new MinStack();
        // for (int i = 0; i < 10; i++) {
        //     t.push(1);
        // }
        // t.push(22);
        // t.push(33);
        t.push(2);
        t.push(0);
        t.push(3);
        t.push(0);
        System.out.println(t.top());
        t.pop();
        System.out.println(t.top());
        t.pop();
        System.out.println(t.getMin());
        t.pop();
        System.out.println(t.getMin());
        // System.out.println(t.top());
        // System.out.println(t.getMin());
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