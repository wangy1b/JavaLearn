package com.wyb.leetcode;

/*

给定一个整数 n，返回 n! 结果尾数中零的数量。

示例 1:

输入: 3
输出: 0
解释: 3! = 6, 尾数中没有零。
示例 2:

输入: 5
输出: 1
解释: 5! = 120, 尾数中有 1 个零.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class FactorialTrailingZeroes {

    public static void main(String[] args) {
        int n = 47;
        // System.out.println(trailingZeroes1(n));
        System.out.println(trailingZeroes2(n));
        System.out.println(trailingZeroes2Efficient(n));
        System.out.println(trailingZeroes3(n));
        System.out.println(trailingZeroes3Efficient(n));
    }

    private static long Factorial(int n) {
        if (n==1) return 1;
        return (long) n* Factorial(n-1);

    }

    private static int trailingZeroes1(int n) {
        long res = Factorial(n);
        System.out.println("res = "+ res);
        String s = String.valueOf(res);
        int cnt = 0;
        int i = s.length() - 1;
        while (s.charAt(i) == '0'){
            cnt++;
            i--;
        }
        return cnt;
    }

    /*

    方法二：计算因子 5
    这种方法也太慢了，但是在解决问题的过程中，它很可能是提出对数方法的一个步骤。

    与方法 1 中那样计算阶乘不同，我们可以认识到阶乘末尾的每个 00 表示乘以 1010。

    那么，我们在计算 n!时乘以 10 的次数是多少？两个数字 aa 和 bb 相乘。例如，要执行 42⋅75=3150，可以重写如下：

    42 = 2⋅3⋅7
    75 =3⋅5⋅5
    442⋅75=2⋅3⋅7⋅3⋅5⋅5

    现在，为了确定最后有多少个零，我们应该看有多少对 22 和 55 的因子。在上面的例子中，我们有一对 2 和 5 的因子。

    那么，这和阶乘有什么关系呢？在一个阶乘中，我们把所有 11 和 nn 之间的数相乘，这和把所有 11 和 nn 之间所有数字的因子相乘是一样的。

    例如，如果 n=16，我们需要查看 1 到 16 之间所有数字的因子。我们只对 2 和 5 有兴趣。包含 5 因子的数字是 {5，10，15}包含因子 2 的数字是 {2、4、6、8、10、12、14、16}。
    因为只三个完整的对，因此 16!后有三个零

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/jie-cheng-hou-de-ling-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    private static int trailingZeroes2(int n) {
        int five_cnt = 0,two_cnt = 0;
        int i = n;
        while (i > 0) {
            int cur = i;
            while (cur % 5 == 0)  {
                five_cnt++;
                cur /= 5;
            }
            i--;
        }

        int j = n;
        while (j > 0) {
            if (j % 2 == 0)  {
                two_cnt++;
            }
            j--;
        }

        return Math.min(five_cnt,two_cnt);
    }


    private static int trailingZeroes2Efficient(int n) {
        int five_cnt = 0;
        for (int i = 5; i <= n; i += 5) {
            int cur_i = i;
            // 因为 25 有两个因子 5
            while (cur_i % 5 == 0)  {
                five_cnt++;
                cur_i = cur_i / 5;
            }
        }
        return five_cnt;
    }


    /*

    在方法 2 中，我们找到了一种计算阶乘后的零的个数的方法，而不需要实际计算阶乘。
    这是通过在 5 的每个倍数上循环，从 5 到 n，并计算 5 的每个倍数中有多少个因子。
    我们把所有这些数字加起来得到最终结果。

    然而，无论是实际上还是对问题的要求来说，方法 2 仍然太慢。
    为了得出一个足够快的算法，我们需要做进一步改进，这个改进能使我们在对数时间内计算出我们的答案。

    思考我们之前简化的算法（但不正确），它不正确是因为对于有多个因子 5 时会计算出错，例如 25。
        fives = 0
        for i from 1 to n inclusive:
            if i is divisible by 5:
                fives += 1
    你会发现这是执行 n/5 的低效方法。我们只对 5 的倍数感兴趣，不是 5 的倍数可以忽略,因此可以简化成:
        fives = n / 5
        tens = fives
    那么，如何解决有多重因子的数字呢？所有包含两个及以上的因子 5 的数字都是 25 的倍数。
    所以我们可以简单的除以 25 来计算 25 的倍数是多少。另外，我们在 n/5 已经计算了 25 一次，
    所以我们只需要再计算额外因子一次 n/25（而不是 2 * (n/25)）

    所以结合起来我们得到：
        fives = n / 5 + n / 25
        tens = fives
    但是如果有三个因子 55 呢！为了得到最终的结果，我们需要将所有的相加。
    得到：fives=n/5 +n/25 +n/125+n/625+n/3125+ ...

    例如，如果 n=12345，我们得到：
    fives= 12345/5 + 12345/25 + 12345/125 + 12345/625 + 12345/3125 + 12345/16075 + 12345/80375 +...

    等于：
    fives = 2469 + 493 + 98 + 19 + 3 + 0 + 0 + ...=3082

    在代码中，我们可以通过循环 5 的幂来计算。

    fives = 0
    power_of_5 = 5
    while n >= power_of_5:
        fives += n / power_of_5
        power_of_5 *= 5

    tens = fives

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/jie-cheng-hou-de-ling-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static int trailingZeroes3(int n) {
        int zeroCount = 0;
        // We need to use long because currentMultiple can potentially become
        // larger than an int.
        long currentMultiple = 5;
        while (n >= currentMultiple) {
            zeroCount += (n / currentMultiple);
            currentMultiple *= 5;
        }
        return zeroCount;
    }

    /*

    编写此算法的另一种方法是，我们不必每次尝试 5 的幂，而是每次将 n 本身除以 5。
    这也是一样的，因为我们最终得到的序列是：
    fives=n/5+(n/5)/5+((n/5)/5)/5+...
    注意，在第二步中，我们有 (n/5)/5。这是因为前一步将 n 本身除以5等等。
    如果你熟悉分数规则，你会发现(n/5)/5 和 n/5.5是一样的。这意味着序列与：
    n/5+n/25+n/125+...

    因此，这种编写算法的替代方法是等价的。
    复杂度分析

    时间复杂度：O(logn)。在这种方法中，我们将 n 除以 5 的每个幂。根据定义，5 的 log5n 幂小于或等于 n。
        由于乘法和除法在 32 位整数范围内，我们将这些计算视为 O(1)。
        因此，我们正在执行 log 5n⋅O（1）=logn 操作
    空间复杂度：O(1)，只是用了常数空间。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/jie-cheng-hou-de-ling-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static int trailingZeroes3Efficient(int n) {
        int zeroCount = 0;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }


    private static int trailingZeroes4Efficient(int n) {
        if (n == 0 ) return 0;
        return n/5 + trailingZeroes4Efficient(n/5);
    }

}

