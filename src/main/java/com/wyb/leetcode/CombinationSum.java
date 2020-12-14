package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 加速剪枝
        // 值1 遍历过后，值2 不会再使用1了，因为如果有情况，值1已经在包含值2的时候遍历过了
        // 所以idx 用来控制不会有多种相似的排列
        Arrays.sort(candidates);
        backTrack(candidates, res, new LinkedList<Integer>(), target, 0);
        return res;
    }

    /**
     * @param candidates 输入数组
     * @param res        返回结果
     * @param track      当前遍历的路径
     * @param target     目标值
     * @param idx        遍历值的下标
     */
    private static void backTrack(int[] candidates, List<List<Integer>> res, LinkedList<Integer> track, int target, int idx) {
        //将符合条件的组合加入res中
        if (target == 0) {
            res.add(new ArrayList<Integer>(track));
            return;
        }
        //如果上一个元素大于target，往回走，去尝试别的元素
        if (target < 0) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            //满足条件，提前结束
            if (target - candidates[i] < 0) {
                break;
            }
            track.add(candidates[i]);
            backTrack(candidates, res, track, target - candidates[i], i);
            track.removeLast();
        }

    }

    public static void main(String[] args) {
        // int[] nums = {2, 3, 6, 7};
        // int target = 7;

        int[] nums = {2,7,6,3,5,1};
        int target = 9;
        List<List<Integer>> res = combinationSum(nums, target);
        System.out.println(res);
    }


    public List<List<Integer>> combinationSumOfficial(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
