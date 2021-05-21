package com.wyb.leetcode;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

 示例 1:
 输入: nums = [1,1,1,2,2,3], k = 2
 输出: [1,2]
 示例 2:

 输入: nums = [1], k = 1
 输出: [1]


 提示：
 1 <= nums.length <= 105
 k 的取值范围是 [1, 数组中不相同的元素的个数]
 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的


 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 https://leetcode-cn.com/problems/top-k-frequent-elements/

 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        // int[] nums = {1,1,1,2,2,2,2,3};
        // int k = 2;
        int[] nums = {1};
        int k = 2;
        TopKFrequentElements topKF = new TopKFrequentElements();
        int[] res = topKF.topKFrequent(nums,k);
        // int[] res = topKF.topKFrequentHeap(nums,k);
        // int[] res = topKF.topKFrequentQS(nums,k);
        for (int re : res) {
            System.out.println(re);
        }
    }


    public int[] topKFrequent(int[] nums, int k) {
        k = k > nums.length ? nums.length : k;
        // 结果
        int[] res = new int[k];
        // 出现次数
        // 负数数组出问题
        int[] occurrences = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            occurrences[nums[i]] += 1;
        }
        // list中包含 数组[num, count]
        List<int[]> values = new ArrayList<int[]>();
        for (int i = 0; i < occurrences.length; i++) {
            int num = i, count = occurrences[i];
            if (count>0) {
                values.add(new int[]{num, count});
            }
        }
        // 利用Collections中排序对list排序
        Collections.sort(values, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 倒叙
                // 数组中的第二个数字为count排序
                return -Integer.valueOf(o1[1]).compareTo(o2[1]);
            }
        });

        for (int i = 0; i < k; i++) {
            res[i] = values.get(i)[0];
        }
        return res;
    }


    // 方法一：堆
    // 首先遍历整个数组，并使用哈希表记录每个数字出现的次数，并形成一个「出现次数数组」。
    //     找出原数组的前 k 个高频元素，就相当于找出「出现次数数组」的前 k 大的值。
    // 其次将数值和出现次数直接作为数字存入堆中，重写Comparator方法;
    //     然后就是对堆排的操作，满足k了就先判断当前值是否大于peek,是的话就poll出最小的再offer，否则直接offer

    // 复杂度分析
    //
    // 时间复杂度：O(Nlogk)，其中 N 为数组的长度。我们首先遍历原数组，并使用哈希表记录出现次数，每个元素需要 O(1) 的时间，共需 O(N) 的时间。
    //           随后，我们遍历「出现次数数组」，由于堆的大小至多为 k，因此每次堆操作需要 O(logk) 的时间，共需 O(Nlogk) 的时间。二者之和为O(Nlogk)。
    // 空间复杂度：O(N)。哈希表的大小为 O(N)，而堆的大小为 O(k)，共计为O(N)。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();

        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }


    // 方法二：基于快速排序
    // 思路与算法
    //
    // 我们可以使用基于快速排序的方法，求出「出现次数数组」的前 k 大的值。
    //
    // 在对数组 arr[l…r] 做快速排序的过程中，我们首先将数组划分为两个部分 arr[i…q−1] 与 arr[q+1…j]，
    // 并使得arr[i…q−1] 中的每一个值都不超过 arr[q]，且 arr[q+1…j] 中的每一个值都大于 arr[q]。
    //
    // 于是，我们根据 k 与左侧子数组 arr[i…q−1] 的长度（为q−i）的大小关系：
    //
    // 如果 k≤q−i，则数组 arr[l…r] 前 k 大的值，就等于子数组 arr[i…q−1] 前 k 大的值。
    // 否则，数组 arr[l…r] 前 k 大的值，就等于左侧子数组全部元素，加上右侧子数组 arr[q+1…j] 中前 k−(q−i) 大的值。
    // 原版的快速排序算法的平均时间复杂度为 O(NlogN)。
    // 我们的算法中，每次只需在其中的一个分支递归即可，因此算法的平均时间复杂度降为O(N)。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public int[] topKFrequentQS(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);


        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}
