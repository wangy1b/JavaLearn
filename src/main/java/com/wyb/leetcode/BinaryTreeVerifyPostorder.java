package com.wyb.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

 参考以下这颗二叉搜索树：

      5
    / \
   2   6
  / \
 1   3

 示例 1：
 输入: [1,6,3,2,5]
 输出: false

 示例 2：
 输入: [1,3,2,6,5]
 输出: true


 提示：
 数组长度 <= 1000

 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class BinaryTreeVerifyPostorder {

    // 后序：[左子节点中序],[右子节点中序],根节点
    // 二叉搜索树：[左子节点中序] < 根节点 < [右子节点中序]
    public boolean verifyPostorder(int[] postorder) {
        int len = postorder.length;
        if (len == 0) return true;
        return helper(postorder, 0, len - 1);
    }

    // 没完成！！
    private boolean helper(int[] postorder, int startIdx, int endIdx) {
        if (startIdx == endIdx)
            return true;
        int curr = postorder[endIdx];
        // 左子树最后一个节点的位置
        int leftEndIdx = endIdx;
        // 根据root节点 小于 右节点，依次递归找到左节点的最后位置
        while (curr <= postorder[leftEndIdx] && leftEndIdx > startIdx) leftEndIdx--;
        // 递归查看左子树 和 右子树
        boolean l = true, r = true;
        if (startIdx <= leftEndIdx)
            l = helper(postorder, startIdx, leftEndIdx);
        if (leftEndIdx + 1 <= endIdx - 1)
            r = helper(postorder, leftEndIdx + 1, endIdx - 1);
        return startIdx == leftEndIdx && l && r;
    }


    public static void main(String[] args) {
        BinaryTreeVerifyPostorder v = new BinaryTreeVerifyPostorder();
        int[] nums = {1,3,2,6,5}; // true
        // int[] nums = {1,2,3,6,5}; // true
        // int[] nums = {4,6,7,5}; // true
        // int[] nums = {1, 2, 3, 4, 5}; // true
        // int[] nums = {1,2,5,10,6,9,4,3}; // false
        // int[] nums = {5, 4, 3, 2, 1};// true
        System.out.println(v.verifyPostorder(nums));
    }


    public boolean verifyPostorder1(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root) return false;
            while(!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }
}
