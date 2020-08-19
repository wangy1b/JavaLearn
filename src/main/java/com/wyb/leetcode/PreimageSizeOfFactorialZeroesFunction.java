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
        // System.out.println(preimageSizeFZF(1000000000));
        System.out.println(preimageSizeFZF1(1000000000));
    }

    private static long trailingZeroes(long n) {
        long zeroCount = 0;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }


    // 递归内存占用更小
    private static long zeta(long x) {
        if (x == 0) return 0;
        return x/5 + zeta(x/5);
    }



    /*

    二分查找【通过】
思路和算法

令 zeta(x) 为 x! 末尾零的个数。如果 x! 可以分解为素数的乘积，如 的形式，那么 x! 末尾零的个数为 min(a, b) = b。

zeta(x) 就是 x 除以 5 的次数之和，即 zeta(x) 等于

可以看出，zeta(x) 是一个单调递增函数，因此可以使用二分查找求解。

使用二分查找找出满足 zeta(x) = K 的最大 x 和最小 x。
由于一定存在 zeta(5a-1) < zeta(5a) = zeta(5a+1) = zeta(5a+2) = zeta(5a+3) = zeta(5a+4) < zeta(5a+5)，
即如果存在某个 x 使得 zeta(x) = K，那么一定存在连续 5 个数的阶乘末尾零的个数都为 K；
如果不存在这样的 x，那么阶乘末尾零的个数为 K 的数字只有 0 个。

作者：LeetCode
链接：https://leetcode-cn.com/problems/preimage-size-of-factorial-zeroes-function/solution/jie-cheng-han-shu-hou-kge-ling-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static int preimageSizeFZF(long K) {
        long lo = K, hi = 10*K + 1;
        while (lo < hi) {
            long mi = lo + (hi - lo) / 2;
            // long zmi = trailingZeroes(mi);
            long zmi = zeta(mi);
            if (zmi == K) {
                return 5;
            }
            else if (zmi < K) {
                lo = mi + 1;
                }
            else {
                hi = mi;
                }
        }
        return 0;
    }

    // unkonwn wrong
    public  static int preimageSizeFZF1(int K) {
        long high = 10 * K + 1;
        long low = K;
        while(low < high) {
            // long mid = (high + low)/2;
            long mid = low + (high - low)/2;
            long f = findZero(mid);
            if (f == K) {
                return 5;
            } else if ( f < K) {
                low = mid + 1;
            } else {
                high = mid ;
            }
        }
        return 0;
    }

    private static long findZero(long n){
        if (n ==0 ) return 0;
        return n/5 + findZero(n/5);
    }

}
