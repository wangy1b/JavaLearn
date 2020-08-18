package com.wyb.leetcode;

/*
793. 阶乘函数后K个零

f(x) 是 x! 末尾是0的数量。（回想一下 x! = 1 * 2 * 3 * ... * x，且0! = 1）

例如， f(3) = 0 ，因为3! = 6的末尾没有0；而 f(11) = 2 ，因为11!= 39916800末端有2个0。给定 K，找出多少个非负整数x ，有 f(x) = K 的性质。

示例 1:
输入:K = 0
输出:5
解释: 0!, 1!, 2!, 3!, and 4! 均符合 K = 0 的条件。

示例 2:
输入:K = 5
输出:0
解释:没有匹配到这样的 x!，符合K = 5 的条件。
注意：

K是范围在 [0, 10^9] 的整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/preimage-size-of-factorial-zeroes-function
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class PreimageSizeOfFactorialZeroesFunction {
    public static void main(String[] args) {
        System.out.println(countDigitOne(5));
    }

    private static int trailingZeroes(int n) {
        int zeroCount = 0;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }
    private static int countDigitOne(int n) {
        int cnt = 0;
        for (int i = 0; i < 1000; i++) {
            if (trailingZeroes(i) == n ) {
                cnt++;
            };
        }
        return cnt;
    }
}
