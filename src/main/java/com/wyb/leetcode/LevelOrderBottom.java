package com.wyb.leetcode;

import java.util.*;

public class LevelOrderBottom {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(levelOrderBottom(treeNode1));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right; }
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        // 两种数据结构： Queue + ArrayList
        Queue<TreeNode> queue = new LinkedList<>();
        // 核心方案：Queue中取出一个元素，再把期左右孩子放入Queue
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每层临时结果
            ArrayList<Integer> temp = new ArrayList<>();
            // 先获取size,防止fast fail
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // Queue中取出一个节点
                TreeNode node = queue.remove();
                // 把值加入list
                temp.add(node.val);
                // 左孩子不空，加入queue
                if(node.left != null) queue.add(node.left);
                // 右孩子不空，加入queue
                if(node.right != null) queue.add(node.right);
            }
            // ans.add(temp);
            stack.push(temp);
        }

        // for (List<Integer> an : ans) {
        //     stack.push(an);
        // }
        // ans.clear();

        while (!stack.isEmpty()){
            ans.add(stack.pop());
        }
        return ans;
    }



}
