package com.wyb.leetcode;


/*

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
输入: [2,2,3,2]
输出: 3

示例 2:
输入: [0,1,0,1,0,1,99]
输出: 99

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/single-number-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class SingleNumberII {

    public static void main(String[] args) {
        int[] nums = {2,2,3,2};
        // int[] nums = {0,1,0,1,0,1,99};
        System.out.println(singleNumber(nums));
    }


    public static int singleNumber(int[] nums) {
        int one = 0, two = 0;
        for (int i = 0; i < nums.length; i++) {

            // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice

            one = ~two & (nums[i] ^ one);
            two = ~one & (nums[i] ^ two);
        }
        return one;
    }
}
