package com.wyb.leetcode;
/*

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
输入: [2,2,1]
输出: 1

示例 2:
输入: [4,1,2,1,2]
输出: 4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/single-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class SingleNumber {
    public static void main(String[] args) {
        // int[] nums = {2, 2, 1};
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));
    }


    private static int mySingleNumber(int[] nums) {
        int s = nums[0];
        for (int i = 0; i < nums.length ; i++) {
            boolean f = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i] && i != j) {
                    f = true;
                    // i++;
                    continue;
                }
                // System.out.println(f);
            }
            if (!f) {
                s = nums[i];
                break;
            }
        }

        return s;
    }

    private static int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }
}
