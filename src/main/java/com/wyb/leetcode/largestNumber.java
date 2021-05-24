package com.wyb.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 最大数
 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

 示例 1：
 输入：nums = [10,2]
 输出："210"

 示例 2：
 输入：nums = [3,30,34,5,9]
 输出："9534330"

 示例 3：
 输入：nums = [1]
 输出："1"

 示例 4：
 输入：nums = [10]
 输出："10"


 提示：
 1 <= nums.length <= 100
 0 <= nums[i] <= 109
 */
public class largestNumber {
    public static void main(String[] args) {
        largestNumber l = new largestNumber();
        int[] nums = {3,30,34,5,9};
        String res = l.largestNumber(nums);
        System.out.println(res);
    }

    /**
     * 考虑输入数组 有相同数字开头 的情况，例如 [4,42] 和 [4,45]。

     对于 [4,42]，比较 442 > 424，需要把 4 放在前面；
     对于 [4,45]，比较 445 < 454，需要把 45 放在前面。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/largest-number/solution/zui-da-shu-by-leetcode-solution-sid5/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        // 处理两个数的排序规则
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for(int x: nums) {
            heap.offer(String.valueOf(x));
        }
        StringBuffer res = new StringBuffer(nums.length);
        while(heap.size() > 0) {
            res.append(heap.poll());
        }
        if(res.charAt(0) == '0') {
            return "0";
        }
        return res.toString();
    }
}
