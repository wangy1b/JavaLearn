package com.wyb.leetcode;

/*

给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

示例 :
输入: [1,2,1,3,2,5]
输出: [3,5]

注意：
结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/single-number-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class SingleNumberIII {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,2,5};
        System.out.println(singleNumber(nums));
    }

    private static int[] singleNumber(int[] nums) {
        // 找出只出现一个的异或结果
        int xor_res = 0;
        for (int i = 0; i < nums.length ; i++) {
            xor_res = xor_res ^ nums[i];
        }


        int first = 0;
        int second = xor_res ^ first;

        System.out.println("first : " + first);
        System.out.println("second : " + second);
        return new int[]{first,second};

    }

    private static int[] singleNumberOfficial(int[] nums) {
        // 找出只出现一个的异或结果
        int xor_res = 0;
        for (int i = 0; i < nums.length ; i++) {
            xor_res = xor_res ^ nums[i];
        }

        // 取反保留最右边1位
        int rightmost1bit = xor_res & (-xor_res);

        // 把上面的结果与每个值再xx找出最右边为1的原始数
        int first = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((rightmost1bit & nums[i]) == 0) first ^= nums[i];
        }

        int second = xor_res ^ first;

        System.out.println("first : " + first);
        System.out.println("second : " + second);
        return new int[]{first,second};

    }
}
