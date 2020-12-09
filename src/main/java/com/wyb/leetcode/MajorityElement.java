package com.wyb.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/*

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        // int[] nums = {3};
        System.out.println(majorityElementOfficial(nums));
    }

    private  static int majorityElement(int[] nums) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i],hashMap.getOrDefault(nums[i],0)+1);
        }

        for (Integer key : hashMap.keySet()) {
            if (hashMap.get(key) > nums.length / 2) {
                return  key;
            }
        }

        return 0;

    }

    // 方法五：Boyer-Moore 投票算法
    // 思路
    //
    // 如果我们把众数记为 +1+1，把其他数记为 -1−1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    private static int majorityElementOfficial(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
