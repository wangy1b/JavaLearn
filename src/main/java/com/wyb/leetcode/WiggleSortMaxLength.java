package com.wyb.leetcode;

/**
 * 376. 摆动序列
 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
 第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。

 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。

 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。

 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。

 示例 1：
 输入：nums = [1,7,4,9,2,5]
 输出：6
 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。

 示例 2：
 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 输出：7
 解释：这个序列包含几个长度为 7 摆动序列。
 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。

 示例 3：
 输入：nums = [1,2,3,4,5,6,7,8,9]
 输出：2

 提示：

 1 <= nums.length <= 1000
 0 <= nums[i] <= 1000

 https://leetcode-cn.com/problems/wiggle-subsequence/
 */
public class WiggleSortMaxLength {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    public static void main(String[] args) {
        WiggleSortMaxLength w = new WiggleSortMaxLength();
        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println(w.wiggleMaxLength(nums));
    }

    /**
     * 方法三：贪心
     思路及解法

     观察这个序列可以发现，我们不断地交错选择「峰」与「谷」，可以使得该序列尽可能长。证明非常简单：如果我们选择了一个「过渡元素」，那么在原序列中，这个「过渡元素」的两侧有一个「峰」和一个「谷」。不失一般性，我们假设在原序列中的出现顺序为「峰」「过渡元素」「谷」。如果「过渡元素」在选择的序列中小于其两侧的元素，那么「谷」一定没有在选择的序列中出现，我们可以将「过渡元素」替换成「谷」；同理，如果「过渡元素」在选择的序列中大于其两侧的元素，那么「峰」一定没有在选择的序列中出现，我们可以将「过渡元素」替换成「峰」。这样一来，我们总可以将任意满足要求的序列中的所有「过渡元素」替换成「峰」或「谷」。并且由于我们不断地交错选择「峰」与「谷」的方法就可以满足要求，因此这种选择方法就一定可以达到可选元素数量的最大值。

     这样，我们只需要统计该序列中「峰」与「谷」的数量即可（注意序列两端的数也是「峰」或「谷」），但需要注意处理相邻的相同元素。

     在实际代码中，我们记录当前序列的上升下降趋势。每次加入一个新元素时，用新的上升下降趋势与之前对比，如果出现了「峰」或「谷」，答案加一，并更新当前序列的上升下降趋势。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/wiggle-subsequence/solution/bai-dong-xu-lie-by-leetcode-solution-yh2m/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int wiggleMaxLengthOfficial3(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }


}
