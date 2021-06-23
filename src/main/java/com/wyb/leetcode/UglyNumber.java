package com.wyb.leetcode;

/***
 * 263. 丑数
 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。

 丑数 就是只包含质因数 2、3 和/或 5 的正整数。

 示例 1：
 输入：n = 6
 输出：true
 解释：6 = 2 × 3

 示例 2：
 输入：n = 8
 输出：true
 解释：8 = 2 × 2 × 2

 示例 3：
 输入：n = 14
 输出：false
 解释：14 不是丑数，因为它包含了另外一个质因数 7 。

 示例 4：
 输入：n = 1
 输出：true
 解释：1 通常被视为丑数。

 提示：
 -2^31 <= n <= 2^31 - 1

 https://leetcode-cn.com/problems/ugly-number/
 */
public class UglyNumber {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }

    public boolean isUgly1(int n) {
        if (n <= 0) return false;
        if (n <= 6) return true; // 1,2,3,4,5,6 都是丑数

        if (n % 2 == 0) return isUgly1(n / 2);
        if (n % 3 == 0) return isUgly1(n / 3);
        if (n % 5 == 0) return isUgly1(n / 5);
        return false;
    }


    public static void main(String[] args) {
        UglyNumber u = new UglyNumber();
        System.out.println(u.isUgly(0));
        System.out.println(u.isUgly1(0));
    }
}
