package com.wyb.leetcode;

/*
189. 旋转数组

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的 原地 算法。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        // int[] nums = {-1,-100,3,99};
        // rotate1(nums,3);
        rotate2(nums,3);
        prrintArr(nums);
    }

    private static  void  prrintArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print( nums[i] + " ");
        }
        System.out.println();
    }

    private static  void  swap(int[] nums, int s, int t) {
        int tmp = nums[s];
        nums[s] = nums[t];
        nums[t] = tmp;
    }

    private static void reverse(int[] nums, int s, int e){
        while (s < e) {
            int tmp = nums[s];
            nums[s] = nums[e] ;
            nums[e] = tmp;
            s++;
            e--;
        }
    }

    // 这个方法基于这个事实：当我们旋转数组 k 次， k\%nk%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
    //
    // 在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-kn−k 个元素，就能得到想要的结果。
    //
    // 作者：LeetCode
    // 链接：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    private static void rotate1(int[] nums, int k) {
        // 处理k>nums.length
        k %= nums.length;
        // 下面的顺序很重要，只有这种顺序可以同时解决nums.length为奇偶的情况
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);

    }

    private static void rotate2(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            exchange(nums,i,k);
        }
    }

    // todo
    private static void exchange(int[] nums, int s,int k) {
        k %= nums.length;
        int fst = s;
        int sec = s + k > nums.length -1 ? s + k - nums.length + 1: s + k;
        int thd = s + 2*k > nums.length -1 ? s + 2*k - nums.length + 1: s + 2*k;

        int tmp3 = nums[thd];
        int tmp2 = nums[sec];

        nums[sec] = nums[fst];
        nums[thd] = tmp2;
        nums[fst] = tmp3;

    }


}
