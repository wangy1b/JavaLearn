package com.wyb.leetcode;

import java.util.Arrays;

/*

324. 摆动排序 II
给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

示例 1:

输入：nums = [1,5,1,1,6,4]
输出：[1,6,1,5,1,4]
解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。

示例 2:

输入: nums = [1, 3, 2, 2, 3, 1]
输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
说明:
你可以假设所有输入都会得到有效的结果。

提示：
1 <= nums.length <= 5 * 104
0 <= nums[i] <= 5000
题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果

进阶:
你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wiggle-sort-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WiggleSortII {
    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        // int[] nums = {2, 5, 1, 4, 3, 6};
        // int[] nums = {1, 3, 2, 2, 3, 1};
        // int[] nums = {5, 5, 5, 4, 4, 4, 4};

        WiggleSortII w = new WiggleSortII();
        // w.wiggleSort1(nums);
        // w.wiggleSort2(nums);
        w.wiggleSort3(nums);
        // w.qsort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }


    /**
     * 解法I:
     * O(nlogn)时间排序
     * O(n)空间辅助数组解法：
     * <p>
     * 1.对原数组排序，得到排序后的辅助数组tmp
     * 2.对原数组的偶数位下标填充tmp的末尾元素
     * 3.对原数组的奇数位下标填充tmp的末尾元素
     *
     * @param nums
     */
    private void wiggleSort1(int[] nums) {
        int len = nums.length;
        if (len <= 1) return;
        Arrays.sort(nums);
        int[] tmp = nums.clone();

        //比如123456
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = tmp[--len]; //遍历完成后 x 6 x 5 x 4
        }
        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = tmp[--len]; //便利完成后 3 6 2 5 1 4
        }
    }

    /**
     * 解法II
     * O(n)时间复杂度
     * O(1)空间复杂度解法：
     *
     * 使用O(n)时间复杂度的quickSelect算法，从未经排序的数组nums中选出中位数mid
     *
     * 参照解法I的思路，将nums数组的下标x通过函数idx()从[0, 1, 2, … , n - 1, n] 映射到 [1, 3, 5, … , 0, 2, 4, …]，得到新下标ix
     *
     * 以中位数mid为界，将大于mid的元素排列在ix的较小部分，而将小于mid的元素排列在ix的较大部分。
     *
     * @param nums
     */
    int n = 0;
    private void wiggleSort2(int[] nums) {
        int len = nums.length;
        n = len;

        // Find a median.
        int midIdx = partition2(nums, 0, len - 1);
        int mid = nums[midIdx];

        // 3-way-partition-to-wiggly in O(n) time with O(1) space.
        int i = 0, j = 0, k = len - 1;
        while (j <= k) {
            if (nums[idx(j)] > mid)
                swap(nums, idx(i++), idx(j++));
            else if (nums[idx(j)] < mid)
                swap(nums, idx(j), idx(k--));
            else
                j++;
        }
    }

    // #define A(i) nums[(1+2*(i)) % (n|1)]
    // i是虚拟地址，(1+2*(i)) % (n|1)是实际地址。
    // 其中n为数组长度，‘|’为按位或，
    // 如果n为偶数，(n|1)为n+1，
    // 如果n为奇数，(n|1)仍为n。
    private int idx(int i){
        return (1 + 2 * (i)) % (n|1);
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) return;
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    // bug!!!
    private int partition(int[] nums, int startIdx, int endIdx) {
        int pivot = nums[endIdx];
        int i = startIdx;
        for (int j = startIdx; j < endIdx - 1; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, endIdx);
        return i;
    }

    private int partition2(int[] arr, int leftBound, int rightBound){
        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while (left <= right) {
            // 从左往右找到第一个比pivot大的数据
            while (left <= right && arr[left] <= pivot) left ++;
            // 从右往左找到第一个比pivot小或等的数据
            while (left <= right && arr[right] > pivot) right --;
            // 找到之后就交换
            if (left < right) swap(arr, left, right);
        }

        swap(arr, left, rightBound);
        return left;
    }

    private void qsort(int[] nums, int startIdx, int endIdx){
        if (startIdx >= endIdx) return;
        // int mid = partition(nums, startIdx, endIdx);
        int mid = partition2(nums, startIdx, endIdx);
        qsort(nums, startIdx,mid - 1);
        qsort(nums, mid + 1, endIdx);
    }


    // 桶排序
    public void wiggleSort3(int[] nums) {
        int[] bucket = new int[5001];
        for (int num : nums) bucket[num]++;
        int len = nums.length;
        int small, big;//穿插数字时的上界
        //总长度为奇数时，“小 大 小 大 小”边界左右都为较小的数；
        //总长度为偶数时，“小 大 小 大”边界左为较小的数，边界右为较大的数
        if ((len & 1) == 1) {
            small = len - 1;
            big = len - 2;
        } else {
            small = len - 2;
            big = len - 1;
        }
        int j = 5000; //从后往前，将桶中数字穿插到数组中，后界为j
        //桶中大的数字在后面，小的数字在前面，所以先取出较大的数字，再取出较小的数字
        //先将桶中的较大的数字穿插放在nums中
        for (int i = 1; i <= big; i += 2) {
            while (bucket[j] == 0) j--;//找到不为0的桶
            nums[i] = j;
            bucket[j]--;
        }
        //再将桶中的较小的数字穿插放在nums中
        for (int i = 0; i <= small; i += 2) {
            while (bucket[j] == 0) j--;//找到不为0的桶
            nums[i] = j;
            bucket[j]--;
        }
    }


}
