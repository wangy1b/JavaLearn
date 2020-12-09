package com.wyb.leetcode;

import jdk.nashorn.internal.ir.BaseNode;

import java.util.*;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permutations {

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backTrack(nums, res, new LinkedList<>());
        return res;
    }


    private static void backTrack(int[] nums, List<List<Integer>> res, LinkedList<Integer> track) {
        if (nums.length == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }

            track.add(nums[i]);
            backTrack(nums, res, track);
            track.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = new LinkedList<>();
        // res = permute(nums);
        res = permuteOfficial(nums);
        System.out.println(res);
    }


    private static List<List<Integer>> permuteOfficial(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }

    }


    /**
     *
     *
     * 使用标记数组来处理填过的数是一个很直观的思路，但是可不可以去掉这个标记数组呢？毕竟标记数组也增加了我们算法的空间复杂度。

     答案是可以的，我们可以将题目给定的 nn 个数的数组 \textit{nums}[]nums[] 划分成左右两个部分，左边的表示已经填过的数，右边表示待填的数，我们在递归搜索的时候只要动态维护这个数组即可。

     具体来说，假设我们已经填到第 \textit{first}first 个位置，那么 \textit{nums}[]nums[] 数组中 [0,\textit{first}-1][0,first−1] 是已填过的数的集合，[\textit{first},n-1][first,n−1] 是待填的数的集合。我们肯定是尝试用 [\textit{first},n-1][first,n−1] 里的数去填第 \textit{first}first 个数，假设待填的数的下标为 ii ，那么填完以后我们将第 ii 个数和第 \textit{first}first 个数交换，即能使得在填第 \textit{first}+1first+1个数的时候 \textit{nums}[]nums[] 数组的[0,first][0,first] 部分为已填过的数，[\textit{first}+1,n-1][first+1,n−1] 为待填的数，回溯的时候交换回来即能完成撤销操作。

     举个简单的例子，假设我们有 [2, 5, 8, 9, 10] 这 5 个数要填入，已经填到第 3 个位置，已经填了 [8,9] 两个数，那么这个数组目前为 [8, 9 | 2, 5, 10] 这样的状态，分隔符区分了左右两个部分。假设这个位置我们要填 10 这个数，为了维护数组，我们将 2 和 10 交换，即能使得数组继续保持分隔符左边的数已经填过，右边的待填 [8, 9, 10 | 2, 5] 。

     当然善于思考的读者肯定已经发现这样生成的全排列并不是按字典序存储在答案数组中的，如果题目要求按字典序输出，那么请还是用标记数组或者其他方法。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
     来源：力扣（LeetCode）
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteOfficial1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrackOfficial1(n, output, res, 0);
        return res;
    }

    public void backtrackOfficial1(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrackOfficial1(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

}
