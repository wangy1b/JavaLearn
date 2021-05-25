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

/***
 * 使用一个数组[val,min]保存当前值和最小值,放进一个大数组里
 *
 */
class MinStackII {
    private int[][] elements;
    private int default_length = 10;
    private int idx = 0;
    private int arr_size = 0;
    private int min_max = 0;


    private void grow() {
        int old_size = arr_size;
        arr_size = arr_size + (arr_size >> 1);
        int[][] arr = new int[arr_size][2];
        System.arraycopy(elements, 0, arr, 0, old_size);
        elements = arr;
    }


    /**
     * initialize your data structure here.
     */
    public MinStackII() {
        this.arr_size = default_length;
        this.elements = new int[default_length][2];
    }

    public void push(int val) {
        if (idx == arr_size - 1)
            grow();
        int pre_idx = idx - 1;
        int min = pre_idx < 0 ? val : Math.min(elements[pre_idx][1], val);
        elements[idx++] = new int[]{val, min};
    }

    public void pop() {
        elements[--idx] = null;
    }

    public int top() {
        int pre_idx = idx - 1;
        if (pre_idx < 0)
            return min_max;
        return elements[pre_idx][0];
    }

    public int getMin() {
        int pre_idx = idx - 1;
        if (pre_idx < 0)
            return min_max;
        return elements[pre_idx][1];
    }


    public static void main(String[] args) {
        MinStackII t = new MinStackII();
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