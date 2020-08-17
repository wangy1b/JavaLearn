package com.wyb.leetcode;

/*

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-colors
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class SortColors {

    public static void main(String[] args) {
        // int[] nums = {2,0,2,1,1,0};
        // int[] nums = {2,0,1};
        // int[] nums = {1,2,0};
        int[] nums = {2, 1, 2};
        sortColors(nums);
        printArr(nums);
    }

    private static void printArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] nums, int s, int e) {
        if (s != e) {
            int tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
        }
    }

    private static void sortColors(int[] nums) {
        int s = 0, i = 0;
        int e = nums.length - 1;
        while (i < nums.length) {
            if (nums[i] == 0) {
                swap(nums, s, i);
                s++;
                i++;
            } else if (nums[i] == 2) {
                if (e < i) break;
                swap(nums, e, i);
                e--;
            } else {
                i++;
            }

        }
        System.out.println("s : " + s);
        System.out.println("e : " + e);

    }

    /*
    方法一: 一次遍历
    直觉本问题被称为 荷兰国旗问题，最初由 Edsger W. Dijkstra提出。
    其主要思想是给每个数字设定一种颜色，并按照荷兰国旗颜色的顺序进行调整。
    我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素。
    解法的思路是沿着数组移动 curr 指针，若nums[curr] = 0，则将其与 nums[p0]互换；若 nums[curr] = 2 ，则与 nums[p2]互换。

    算法:

    初始化0的最右边界：p0 = 0。在整个算法执行过程中 nums[idx < p0] = 0.
    初始化2的最左边界 ：p2 = n - 1。在整个算法执行过程中 nums[idx > p2] = 2.
    初始化当前考虑的元素序号 ：curr = 0.

    While curr <= p2 :
    若 nums[curr] = 0 ：交换第 curr个 和 第p0个 元素，并将指针都向右移。
    若 nums[curr] = 2 ：交换第 curr个和第 p2个元素，并将 p2指针左移 。
    若 nums[curr] = 1 ：将指针curr右移。


    作者：LeetCode
    链接：https://leetcode-cn.com/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    */


    /*
  荷兰三色旗问题解
  */
    public void sortColorsOfficial(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else curr++;
        }
    }

}
