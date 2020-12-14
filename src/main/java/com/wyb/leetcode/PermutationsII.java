package com.wyb.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *  47. 全排列 II
 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

 示例 1：

 输入：nums = [1,1,2]
 输出：
 [[1,1,2],
 [1,2,1],
 [2,1,1]]
 示例 2：

 输入：nums = [1,2,3]
 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


 提示：

 1 <= nums.length <= 8
 -10 <= nums[i] <= 10

 https://leetcode-cn.com/problems/permutations-ii/
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        Arrays.sort(nums);
        backTrack(nums, res, new LinkedList<>(),used);
        return res;
    }

    private void backTrack(int[] nums, List<List<Integer>> res, LinkedList<Integer> track,boolean[] used) {
        if (nums.length == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }

            track.add(nums[i]);
            used[i] = true;
            backTrack(nums, res, track,used);
            track.removeLast();
            used[i] = false;

        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> res = new LinkedList<>();
        PermutationsII p = new PermutationsII();
        res = p.permuteUnique(nums);
        System.out.println(res);
    }
}
