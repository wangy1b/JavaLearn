package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 * https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backTrack(candidates, res, new LinkedList<Integer>(), target, 0);
        return res;
    }


    /**
     * @param candidates 输入数组
     * @param res        返回结果
     * @param track      当前遍历的路径
     * @param target     目标值
     * @param idx        遍历值的下标，
     *                   // 值1 遍历过后，值2 不会再使用1了，因为如果有情况，值1已经在包含值2的时候遍历过了
     *                   // 所以idx 用来控制不会有多种相似的排列
     */
    private static void backTrack(int[] candidates, List<List<Integer>> res, LinkedList<Integer> track, int target, int idx) {
        //将符合条件的组合加入res中
        if (target == 0 && !res.contains(track)) {
            res.add(new ArrayList<Integer>(track));
            return;
        }
        //如果上一个元素大于target，往回走，去尝试别的元素
        if (target < 0 || idx == candidates.length - 1 ) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            //满足条件，提前结束
            if (target - candidates[i] < 0) {
                break;
            }
            track.add(candidates[i]);
            backTrack(candidates, res, track, target - candidates[i], i + 1);
            track.removeLast();
        }

    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        // List<List<Integer>> res = combinationSum2(nums, target);
        List<List<Integer>> res = new CombinationSumII().combinationSum2Official(nums, target);
        System.out.println(res);
    }


    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2Official(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }

        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

}
