package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 204. 计数质数
 统计所有小于非负整数 n 的质数的数量。

 示例 1：
 输入：n = 10
 输出：4
 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

 示例 2：
 输入：n = 0
 输出：0

 示例 3：
 输入：n = 1
 输出：0

 提示：
 0 <= n <= 5 * 10^6

 */
public class CountPrimes {

    // 普通法：枚举
    // 超出时间限制!!!

    /**
     * 方法一：枚举
     很直观的思路是我们枚举每个数判断其是不是质数。

     考虑质数的定义：在大于 1 的自然数中，除了 1 和它本身以外不再有其他因数的自然数。
     因此对于每个数 x，我们可以从小到大枚举 [2,x−1] 中的每个数 y，判断 y 是否为 x 的因数。
     但这样判断一个数是否为质数的时间复杂度最差情况下会到 O(n)，无法通过所有测试数据。

     考虑到如果 y 是 x 的因数，那么 x/y 也必然是 x 的因数，因此我们只要校验 y 或者 x/y 即可。
     而如果我们每次选择校验两者中的较小数，则不难发现较小数一定落在 [2,sqrt{x}]的区间中，
     因此我们只需要枚举 [2,sqrt{x}]中的所有数即可，这样单次检查的时间复杂度从 O(n) 降低至了 O(sqrt{n})。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    private boolean isPrime(int num){
        if (num <= 1) return false;
        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
        // to avoid repeatedly calling an expensive function sqrt().
        // 除了1和本身，其他能整除的都不算质数
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CountPrimes c = new CountPrimes();
        System.out.println("res : "+ c.countPrimesOfficial1(2000));
        System.out.println("res : "+ c.countPrimesOfficial2(2000));
    }

    /**
     * 方法二：埃氏筛
     枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。
     接下来我们介绍一个常见的算法，该算法由希腊数学家厄拉多塞（Eratosthenes）提出，称为厄拉多塞筛法，简称埃氏筛。

     我们考虑这样一个事实：如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。

     我们设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。
     从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），
     即 0，这样在运行结束的时候我们即能知道质数的个数。

     这种方法的正确性是比较显然的：这种方法显然不会将质数标记成合数；
     另一方面，当从小到大遍历到数 x 时，倘若它是合数，则它一定是某个小于 x 的质数 y 的整数倍，
     故根据此方法的步骤，我们在遍历到 y 时，就一定会在此时将 x 标记为 isPrime[x]=0。
     因此，这种方法也不会将合数标记为质数。

     当然这里还可以继续优化，对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，应该直接从 x⋅x 开始标记，
     因为 2x,3x,… 这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public int countPrimesOfficial1(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 方法三：线性筛
     此方法不属于面试范围范畴，本节只做简单讲解。

     埃氏筛其实还是存在冗余的标记操作，比如对于 45 这个数，它会同时被 3,5 两个数标记为合数，
     因此我们优化的目标是让每个合数只被标记一次，这样时间复杂度即能保证为 O(n)，这就是我们接下来要介绍的线性筛。

     相较于埃氏筛，我们多维护一个 primes 数组表示当前得到的质数集合。
     我们从小到大遍历，如果当前的数 x 是质数，就将其加入 primes 数组。

     另一点与埃氏筛不同的是，「标记过程」不再仅当 x 为质数时才进行，而是对每个整数 x 都进行。
     对于整数 x，我们不再标记其所有的倍数 x⋅x,x⋅(x+1),…，而是只标记质数集合中的数与 x 相乘的数，
     即 x⋅primes_0,x⋅primes_1,…，且在发现 x mod primes_i = 0 的时候结束当前标记。

     核心点在于：如果 x 可以被 primes_i 整除，那么对于合数 y=x⋅primes_i+1
     ​
     而言，它一定在后面遍历到(x/primes_i ) ⋅ primes_(i+1)
     ​
     这个数的时候会被标记，其他同理，这保证了每个合数只会被其「最小的质因数」筛去，即每个合数被标记一次。

     线性筛还有其他拓展用途，有能力的读者可以搜索关键字「积性函数」继续探究如何利用线性筛来求解积性函数相关的题目。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int countPrimesOfficial2(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }


}
