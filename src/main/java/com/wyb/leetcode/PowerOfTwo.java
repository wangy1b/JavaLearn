package com.wyb.leetcode;

/*

231. 2的幂
给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:

输入: 1
输出: true
解释: 20 = 1
示例 2:

输入: 16
输出: true
解释: 24 = 16
示例 3:

输入: 218
输出: false

https://leetcode-cn.com/problems/power-of-two/

 */
public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        return n>0 && Integer.bitCount(n) == 1;
    }

    /*

    方法一：位运算：获取二进制中最右边的 1
    算法：

    获取最右边的 1：
    首先讨论为什么 x & (-x) 可以获取到二进制中最右边的 1，且其它位设置为 0。

    在补码表示法中，−x=~x+1。换句话说，要计算 −x，则要将 x 所有位取反再加 1。

    在二进制表示中，~x+1 表示将该 1 移动到 ~x 中最右边的 0 的位置上，并将所有较低位的位设置为 0。而 ~x 最右边的 0 的位置对应于 x 最右边的 1 的位置。

    总而言之，-x=~x+1，此操作将 x 所有位取反，但是最右边的 1 除外。
    因此，x 和 −x 只有一个共同点：最右边的 1。这说明 x & (-x) 将保留最右边的 1。并将其他的位设置为 0。

    检测是否为 2 的幂：

    我们通过 x & (-x) 保留了最右边的 1，并将其他位设置为 0 若 x 为 2 的幂，则它的二进制表示中只包含一个 1，则有 x & (-x) = x。

    若 x 不是 2 的幂，则在二进制表示中存在其他 1，因此 x & (-x) != x。

    因此判断是否为 2 的幂的关键是：判断 x & (-x) == x。



    作者：LeetCode
    链接：https://leetcode-cn.com/problems/power-of-two/solution/2de-mi-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    public boolean isPowerOfTwo2(int n) {
        long x = (long) n;
        return x>0 && (x & (-x)) == x;
    }

    /*

    方法二：位运算：去除二进制中最右边的 1
    算法：

    去除二进制中最右边的 1：

    首先讨论为什么 x & (x - 1) 可以将最右边的 1 设置为 0。

    (x - 1) 代表了将 x 最右边的 1 设置为 0，并且将较低位设置为 1。

    再使用与运算：则 x 最右边的 1 和就会被设置为 0，因为 1 & 0 = 0。

    检测是否为 2 的幂：

    2 的幂二进制表示只含有一个 1。
    x & (x - 1) 操作会将 2 的幂设置为 0，因此判断是否为 2 的幂是：判断 x & (x - 1) == 0。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/power-of-two/solution/2de-mi-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    public boolean isPowerOfTwo3(int n) {
        long x = (long) n;
        return x>0 && (x & (x-1)) == 0;
    }
}
