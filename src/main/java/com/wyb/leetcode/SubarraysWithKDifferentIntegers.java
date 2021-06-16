package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
/*

992. K 个不同整数的子数组

给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。

（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）

返回 A 中好子数组的数目。

示例 1：

输入：A = [1,2,1,2,3], K = 2
输出：7
解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
示例 2：

输入：A = [1,2,1,3,4], K = 3
输出：3
解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 

提示：

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class SubarraysWithKDifferentIntegers {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        SubarraysWithKDifferentIntegers s = new SubarraysWithKDifferentIntegers();
        System.out.println(s.mySubarraysWithKDistinct(nums, k));
        System.out.println(s.subarraysWithKDistinct(nums, k));
    }

    // 超出时间限制, 普通暴力法
    public int mySubarraysWithKDistinct(int[] nums, int k) {
        // 用来判断控制元素个数
        HashSet elementK = new HashSet();
        int res = 0;
        // window
        for (int i = k; i <= nums.length; i++) {
            elementK.clear();
            // 移动window
            ArrayList<Integer> window = new ArrayList<>(i);
            int j = 0;
            while (j <= nums.length - i) {
                // 首次的时先添加window个数的元素
                if (elementK.isEmpty()) {
                    for (int q = 0; q < i; q++) {
                        window.add(nums[q]);
                    }
                    elementK.addAll(window);
                } else {// 剩下每次移动window，删除上一个窗口的最后一个数，加上新的一个数
                    // 只有保证删除的值不出现在当前window里
                    int deleteElm = nums[j - 1];
                    int addElm = nums[j + i - 1];
                    window.remove(0);
                    if (!window.contains(deleteElm))
                        elementK.remove(deleteElm);
                    window.add(addElm);
                    elementK.add(addElm);
                }
                // 只有当个数等于K的结果才会被保存
                if (elementK.size() == k) {
                    res += 1;
                    System.out.println(window);
                }
                j++;
            }
        }
        return res;
    }


    // 官方
    // 双指针（滑动窗口）
    // https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/k-ge-bu-tong-zheng-shu-de-zi-shu-zu-by-l-ud34/
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * @param A
     * @param K
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];

        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }
}
