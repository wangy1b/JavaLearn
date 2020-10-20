package com.wyb.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/*

215. 数组中的第K个最大元素
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        // int[] nums = {3,2,1,5,6,4};
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        // int res = findKthLargest(nums,4);
        // int res = findKthLargestOfficial2(nums,4);
        // int res = findKthLargestOfficialtest(nums,4);
        int res = findKthLargestOfficialtest2(nums,4);
        System.out.println("res : " + res);
        // BubbleSort(nums);
        // InsertionSort(nums);
        // SelectionSort(nums);
        // for (int i = 0; i < nums.length; i++) {
        //     System.out.print(nums[i] + " ");
        // }
        // System.out.println();
    }

    private static int findKthLargest(int[] nums, int k) {
        // Arrays.sort(nums);
        BubbleSort(nums);
        return nums[nums.length - k];
    }

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private static void BubbleSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 从开始到已经排完的前一个
            for (int j = 0; j < len - i - 1; j++) {
                // 就近两个如果大小不一就交换，一直到最后
                if (nums[j] > nums[j + 1])
                    swap(nums, j, j + 1);
            }
        }
    }

    private static void InsertionSort(int[] nums) {
        int len = nums.length;
        // 拿出一个值
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                //if(a[j] < a[j-1]) {
                swap(nums, j, j - 1);
                //}
            }
        }
    }


    private static void SelectionSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < len; j++) {
                minPos = nums[j] < nums[minPos] ? j : minPos;
            }
            swap(nums, i, minPos);
        }
    }

    /*

    方法一：基于快速排序的选择方法

    思路和算法

    我们可以用快速排序来解决这个问题，先对原数组排序，再返回倒数第 k 个位置，这样平均时间复杂度是O(nlogn)，但其实我们可以做的更快。

    首先我们来回顾一下快速排序，这是一个典型的分治算法。

    我们对数组 a[l⋯r] 做快速排序的过程是（参考《算法导论》）：

    分解： 将数组 a[l⋯r] 「划分」成两个子数组 a[l⋯q−1]、a[q+1⋯r]，使得 a[l⋯q−1] 中的每个元素小于等于 a[q]，
           且 a[q] 小于等于 a[q+1⋯r] 中的每个元素。其中，计算下标 q 也是「划分」过程的一部分。
    解决： 通过递归调用快速排序，对子数组 a[l⋯q−1] 和 a[q+1⋯r] 进行排序。
    合并： 因为子数组都是原址排序的，所以不需要进行合并操作，a[l⋯r] 已经有序。

    上文中提到的 「划分」 过程是：从子数组 a[l⋯r] 中选择任意一个元素 x 作为主元，
    调整子数组的元素使得左边的元素都小于等于它，右边的元素都大于等于它， x 的最终位置就是 q。
    由此可以发现每次经过「划分」操作后，我们一定可以确定一个元素的最终位置，即 x 的最终位置为 q，
    并且保证 a[l⋯q−1] 中的每个元素小于等于 a[q]，且 a[q] 小于等于 a[q+1⋯r] 中的每个元素。
    所以只要某次划分的 q 为倒数第 k 个下标的时候，我们就已经找到了答案。
     我们只关心这一点，至于 a[l⋯q−1] 和 a[q+1⋯r] 是否是有序的，我们不关心。

    因此我们可以改进快速排序算法来解决这个问题：
    在分解的过程当中，我们会对子数组进行划分，如果划分得到的 q 正好就是我们需要的下标，就直接返回a[q]；
    否则，如果 q 比目标下标小，就递归右子区间，否则递归左子区间。
    这样就可以把原来递归两个区间变成只递归一个区间，提高了时间效率。
    这就是「快速选择」算法。

    我们知道快速排序的性能和「划分」出的子数组的长度密切相关。
    直观地理解如果每次规模为 n 的问题我们都划分成 1 和 n−1，每次递归的时候又向 n−1 的集合中递归，这种情况是最坏的，时间代价是 O(n ^ 2)。
    我们可以引入随机化来加速这个过程，它的时间代价的期望是 O(n)，证明过程可以参考「《算法导论》9.2：期望为线性的选择算法」。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    Random random = new Random();

    public int findKthLargestOfficial(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    private int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }


    /*

    方法二：基于堆排序的选择方法
    思路和算法

    我们也可以使用堆排序来解决这个问题——建立一个大根堆，做 k−1 次删除操作后堆顶元素就是我们要找的答案。
    在很多语言中，都有优先队列或者堆的的容器可以直接使用，但是在面试中，面试官更倾向于让更面试者自己实现一个堆。
    所以建议读者掌握这里大根堆的实现方法，在这道题中尤其要搞懂「建堆」、「调整」和「删除」的过程。

    友情提醒：「堆排」在很多大公司的面试中都很常见，不了解的同学建议参考《算法导论》或者大家的数据结构教材，一定要学会这个知识点哦! ^_^

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


     */

    private static int findKthLargestOfficial2(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private static void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    private static void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }


    private static int findKthLargestOfficialtest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 遍历数组，pq中保存最大的k个数
        for (int n : nums) {
            // 当达到k的个数的时候，最小的值如果大于 当前遍历的值n（那这个值就不该进入pq）
            if (pq.size() == k && pq.peek() >= n) {
                continue;
            }
            // 当达到k的个数的,并且大于最小值，那就要把最小值拉出，加入当前值
            if (pq.size() == k) {
                pq.poll();
            }

            // // 当前的值大于峰值的时候？
            // if (pq.size() == k && pq.peek() >= n)
            pq.add(n);
        }
        return pq.peek();
    }

    private static int findKthLargestOfficialtest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 遍历数组，pq中保存最大的k个数
        for (int n : nums) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }




}
