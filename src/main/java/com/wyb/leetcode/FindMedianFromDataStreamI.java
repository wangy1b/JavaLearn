package com.wyb.leetcode;

import java.util.*;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * <p>
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 */


/**
 * 方法二： 插入排序
 * <p>
 * 保持输入容器始终排序
 * <p>
 * 算法：
 * 哪种算法允许将一个数字添加到已排序的数字列表中，但仍保持整个列表的排序状态？插入排序！
 * <p>
 * 我们假设当前列表已经排序。当一个新的数字出现时，我们必须将它添加到列表中，同时保持列表的排序性质。
 * 这可以通过使用二分搜索找到插入传入号码的正确位置来轻松实现。（记住，列表总是排序的）。
 * 一旦找到位置，我们需要将所有较高的元素移动一个空间，以便为传入的数字腾出空间。
 * <p>
 * 当插入查询的数量较少或者中间查找查询的数量大致相同。 此方法会很好地工作。
 * <p>
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream/solution/shu-ju-liu-de-zhong-wei-shu-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class FindMedianFromDataStreamI {
    int[] store;
    int size;

    /**
     * initialize your data structure here.
     */
    // MedianFinder
    public FindMedianFromDataStreamI() {
        // 默认给大小为10的数组
        store = new int[4];
        size = 0;
    }

    private void grow() {
        if (size == store.length) {
            int newSize = size + (size >> 1);
            int[] newStore = new int[newSize];
            System.arraycopy(store, 0, newStore, 0, size);
            store = newStore;
        }
    }

    private int binarySearchIdx(int num, int start, int end) {
        if (start == end && num >= store[start]) return start + 1;
        if (start == end && num < store[start]) return start;
        int medianIdx = (start + end) >> 1;
        if (num > store[medianIdx])
            return binarySearchIdx(num, medianIdx + 1, end);
        else if (num < store[medianIdx])
            return binarySearchIdx(num, 0, medianIdx);
        return medianIdx + 1;
    }

    public void addNum(int num) {
        int moveIdx = size == 0 ? 0 : binarySearchIdx(num, 0, size - 1);
        int moveNums = size - 1 - moveIdx + 1;
        if (moveNums > 0) {
            // 拷贝后半段
            System.arraycopy(store, moveIdx, store, moveIdx + 1, moveNums);
        }
        // 最后赋值
        store[moveIdx] = num;
        size++;
        grow();
    }

    public double findMedian() {
        int n = size;
        return (n & 1) == 1 ? store[n / 2] : (store[n / 2 - 1] + store[n / 2]) * 0.5;
    }

    public static void main(String[] args) {
        FindMedianFromDataStreamI f = new FindMedianFromDataStreamI();
        f.addNum(6);
        f.addNum(10);
        System.out.println(f.findMedian()); // -> 1.5
        f.addNum(2);
        f.addNum(6);
        f.addNum(5);
        f.addNum(0);
        System.out.println(f.findMedian()); // -> 2
    }

}
