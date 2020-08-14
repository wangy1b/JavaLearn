package com.wyb.leetcode;


import com.wyb.Algorithm.QuickSort;

import java.util.Arrays;
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
    private int initsize = 10;
    private int[] stack = new int[initsize];
    private AtomicInteger size = new AtomicInteger(0);

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    // 将元素 x 推入栈中。
    public void push(int x) {
        if (size.get() >= initsize) {
            int[] temp = new int[initsize * 2];
            System.arraycopy(stack, 0, temp, 0, initsize);
            stack = temp;
            initsize = initsize * 2;
        }

        this.stack[size.get()] = x;
        size.incrementAndGet();
    }

    // 删除栈顶的元素。
    public void pop() {
        this.stack[size.get()-1] = 0;
        size.decrementAndGet();
    }

    // 获取栈顶元素。
    public int top() {
        return this.stack[size.get()-1];
    }

    // 检索栈中的最小元素。
    public int getMin() {
        if (size.get() == 0) return 0;
        int[] temp = new int[size.get()];
        System.arraycopy(this.stack,0,temp,0,size.get());
        QuickSort.sort(temp, 0, size.get()-1);
        return temp[0];
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        // for (int i = 0; i < 10; i++) {
        //     obj.push(i);
        // }
        // obj.push(10);
        // obj.push(11);
        // obj.pop();
        // obj.push(2);
        // obj.push(3);
        //
        // int param_3 = obj.top();
        // int param_4 = obj.getMin();


        obj.getMin();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        obj.getMin();
        obj.pop();
        obj.top();
        obj.getMin();
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