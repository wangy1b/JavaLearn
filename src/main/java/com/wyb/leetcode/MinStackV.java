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

/***
 * 测试
 * long 用 8 个字节存储 64位
 * int  用 4 个字节存储 32位
 * 使用一个Long数组 前32位存值，后32位存min
 *
 */
class MinStackV {
    private long[] elements;
    private int default_length = 10;
    private int idx = 0;
    private int arr_size = 0;
    private int min_max = 0;
    private int step = 1;


    private void grow() {
        int old_size = arr_size;
        arr_size = arr_size + (arr_size >> 1);
        long[] arr = new long[arr_size];
        System.arraycopy(elements, 0, arr, 0, old_size);
        elements = arr;
    }


    /**
     * initialize your data structure here.
     */
    public MinStackV() {
        this.arr_size = default_length;
        this.elements = new long[default_length];
    }

    public void push(int val) {
        if (idx == arr_size - 1)
            grow();
        int pre_idx = idx - step;

        long pre_val = pre_idx < 0 ? val : elements[pre_idx];
        int pre_min = ((int) (pre_val));// & ((1 << 31) - 1);
        pre_min = pre_val < 0 ? - pre_min : pre_min;
        int min = pre_idx < 0 ? val : Math.min(pre_min, val);
        long c_val = (Long.valueOf(val) << 32) | Long.valueOf(min);
        elements[idx++] = c_val;
    }

    public void pop() {
        elements[--idx] = 0;
    }

    public int top() {
        int pre_idx = idx - step;
        if (pre_idx < 0)
            return min_max;
        long c_val = elements[pre_idx];
        // todo 最高位丢失，符号位
        int f =  (int) (c_val >> 32);
        return ( f );
    }

    public int getMin() {
        int pre_idx = idx - step;
        if (pre_idx < 0)
            return min_max;
        long c_val = elements[pre_idx];
        return ((int) (c_val)) ;//& ((1 << 31) - 1));
    }


    public static void main(String[] args) {
        MinStackV t = new MinStackV();
        t.push(2147483646);
        t.push(2147483646);
        t.push(2147483647);
        System.out.println(t.top());
        t.pop();
        System.out.println(t.getMin());
        t.pop();
        System.out.println(t.getMin());
        t.pop();
        t.push(2147483647);
        System.out.println(t.top());
        System.out.println(t.getMin());
        t.push(-2147483648);
        System.out.println(t.top());
        System.out.println(t.getMin());
        t.pop();
        System.out.println(t.getMin());
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