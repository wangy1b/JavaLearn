package com.wyb.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/***
 * 264. 丑数 II
 给你一个整数 n ，请你找出并返回第 n 个 丑数 。

 丑数 就是只包含质因数 2、3 和/或 5 的正整数。

 示例 1：
 输入：n = 10
 输出：12
 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。

 示例 2：
 输入：n = 1
 输出：1
 解释：1 通常被视为丑数。


 提示：
 1 <= n <= 1690

 https://leetcode-cn.com/problems/ugly-number-ii/

 */
public class UglyNumberII {

    public static int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] arr = new int[n];
        arr[0] = 1;
        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(arr[multiply2] * 2,
                    Math.min(arr[multiply3]* 3,arr[multiply5] * 5));
            arr[i] = min;
            if (arr[multiply2] * 2 == min) {
                multiply2++;
            }
            if (arr[multiply3] * 3 == min) {
                multiply3++;
            }
            if (arr[multiply5] * 5 == min) {
                multiply5++;
            }
        }
        return arr[n - 1];
    }


    /**
     *
     * 方法一：最小堆
     要得到从小到大的第 n 个丑数，可以使用最小堆实现。

     初始时堆为空。首先将最小的丑数 1 加入堆。

     每次取出堆顶元素 x，则 x 是堆中最小的丑数，由于 2x, 3x, 5x 也是丑数，因此将 2x, 3x, 5x 加入堆。

     上述做法会导致堆中出现重复元素的情况。为了避免重复元素，可以使用哈希集合去重，避免相同元素多次加入堆。

     在排除重复元素的情况下，第 nn 次从最小堆中取出的元素即为第 nn 个丑数。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    public int nthUglyNumberOfficial1(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    /**
     * 方法二：动态规划
     方法一使用最小堆，会预先存储较多的丑数，导致空间复杂度较高，维护最小堆的过程也导致时间复杂度较高。
     可以使用动态规划的方法进行优化。

     定义数组 dp，其中 dp[i] 表示第 i 个丑数，第 n 个丑数即为 dp[n]。

     由于最小的丑数是 1，因此 dp[1]=1。

     如何得到其余的丑数呢？定义三个指针 p2,p3,p5，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。
     初始时，三个指针的值都是 1。

     当 2≤i≤n 时，令 dp[i]=min(dp[p2]×2,dp[p3]×3,dp[p5]×5)，
     然后分别比较dp[i] 和 dp[p2],dp[p3],dp[p5] 是否相等，如果相等则将对应的指针加 1。

     正确性证明
     对于 i>1，在计算 dp[i] 时，指针 px(x∈{2,3,5}) 的含义是使得 dp[j]×x>dp[i−1] 的最小的下标 j，
     即当 j≥px 时 dp[j]×x>dp[i−1]，当 j<px 时 dp[j]×x≤dp[i−1]。

     因此，对于 i>1，在计算 dp[i] 时，dp[p2]×2,dp[p3]×3,dp[p5]×5 都大于
     dp[i−1],dp[p2−1]×2,dp[p3−1]×3,dp[p5−1]×5 都小于或等于dp[i−1]。
     令 dp[i]=min(dp[p2]×2,dp[p3]×3,dp[p5]×5)，则 dp[i]>dp[i−1] 且 dp[i] 是大于 dp[i−1] 的最小的丑数。

     在计算 dp[i] 之后，会更新三个指针 p2,p3,p5，更新之后的指针将用于计算 dp[i+1]，同样满足 dp[i+1]>dp[i] 且 dp[i+1] 是大于 dp[i] 的最小的丑数。


     官方题解里提到的三个指针p2，p3，p5，但是没有说明其含义，实际上pi的含义是有资格同i相乘的最小丑数的位置。
     这里资格指的是：
     如果一个丑数nums[pi]通过乘以i可以得到下一个丑数，
     那么这个丑数nums[pi]就永远失去了同i相乘的资格（没有必要再乘了），我们把pi++让nums[pi]指向下一个丑数即可。

     不懂的话举例说明：

     一开始，丑数只有{1}，1可以同2，3，5相乘，取最小的1×2=2添加到丑数序列中。

     现在丑数中有{1，2}，在上一步中，1已经同2相乘过了，所以今后没必要再比较1×2了，我们说1失去了同2相乘的资格。

     现在1有与3，5相乘的资格，2有与2，3，5相乘的资格，但是2×3和2×5是没必要比较的，
     因为有比它更小的1可以同3，5相乘，所以我们只需要比较1×3，1×5，2×2。

     依此类推，每次我们都分别比较有资格同2，3，5相乘的最小丑数，选择最小的那个作为下一个丑数，
     假设选择到的这个丑数是同i（i=2，3，5）相乘得到的，所以它失去了同i相乘的资格，把对应的pi++，让pi指向下一个丑数即可。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int nthUglyNumberOfficial2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    // 2^x * 3^y * 5^z = uglyNumber
    public static void main(String[] args) {
        UglyNumberII u = new UglyNumberII();
        System.out.println(u.nthUglyNumber(14));
        System.out.println(u.nthUglyNumberOfficial1(14));
        System.out.println(u.nthUglyNumberOfficial2(14));
    }
}
