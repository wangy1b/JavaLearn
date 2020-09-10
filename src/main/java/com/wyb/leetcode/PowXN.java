package com.wyb.leetcode;

/*

50. Pow(x, n)
实现 pow(x, n) ，即计算 x 的 n 次幂函数。

示例 1:

输入: 2.00000, 10
输出: 1024.00000
示例 2:

输入: 2.10000, 3
输出: 9.26100
示例 3:

输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
说明:

-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。

https://leetcode-cn.com/problems/powx-n/

 */
public class PowXN {
    public static void main(String[] args) {
        System.out.println(myPow(2.00000, 5));
    }

    /*

    我们把这些贡献相乘，x * x^4 * x^8 * x^64 恰好等于 x^77。
    而这些贡献的指数部分又是什么呢？它们都是 2 的幂次，这是因为每个额外乘的 x 在之后都会被平方若干次。
    而这些指数 1，4，8 和 64，恰好就对应了 77 的二进制表示 (1001101)中的每个 1！

    因此我们借助整数的二进制拆分，就可以得到迭代计算的方法，一般地，如果整数 nn 的二进制拆分为

    n = 2^{i_0} + 2^{i_1} + ... + 2^{i_k}

    那么

    x^n = x^{2^{i_0}} * x^{2^{i_1}} * ... * x^{2^{i_k}}

    这样以来，我们从 xx 开始不断地进行平方，得到 x^2, x^4, x^8, x^{16},⋯，
    如果 n 的第 k 个（从右往左，从 0 开始计数）二进制位为 1，那么我们就将对应的贡献 x^{2^k} 计入答案。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    private static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }


}
