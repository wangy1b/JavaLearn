package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

    // 从左到右，全部放入一个数组
    // 用List来接收放入的值，转为int[]返回
    public static int[] levelOrder1(TreeNode root) {
        if(root == null) return new int[]{};

        // 两种数据结构： Queue + ArrayList
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        // 核心方案：Queue中取出一个元素，再把期左右孩子放入Queue
        queue.add(root);
        while (queue.isEmpty()) {
            // Queue中取出一个节点
            TreeNode node = queue.remove();
            // 把值加入list
            list.add(node.val);
            // 左孩子不空，加入queue
            if(node.left != null) queue.add(node.left);
            // 右孩子不空，加入queue
            if(node.right != null) queue.add(node.right);
        }

        // 把ArrayList类型转换为int[]数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return  res;
    }

    // 每层都放入一个List<Integer>,
    // 通过for循环queue.size来分辨每一层
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        // 两种数据结构： Queue + ArrayList
        Queue<TreeNode> queue = new LinkedList<>();
        // ArrayList<Integer> list = new ArrayList<>();

        // 核心方案：Queue中取出一个元素，再把期左右孩子放入Queue
        queue.add(root);
        while (queue.isEmpty()) {

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
            res.add(temp);
        }

        return  res;
    }

    // z字型，通过list中的尾部追加 -> 头部插入
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        // 两种数据结构： Queue + ArrayList
        Queue<TreeNode> queue = new LinkedList<>();
        // ArrayList<Integer> list = new ArrayList<>();

        // 核心方案：Queue中取出一个元素，再把期左右孩子放入Queue
        queue.add(root);
        Boolean flag = true;
        while (queue.isEmpty()) {

            // 每层临时结果
            LinkedList<Integer> temp = new LinkedList<>();
            // 先获取size,防止fast fail
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // Queue中取出一个节点
                TreeNode node = queue.remove();

                // 把值加入temp
                if(flag) {
                    // 奇数行就尾部追加
                    temp.add(node.val);
                } else {
                    // 偶数行就头部插入
                    temp.add(0,node.val);
                }
                // 左孩子不空，加入queue
                if(node.left != null) queue.add(node.left);
                // 右孩子不空，加入queue
                if(node.right != null) queue.add(node.right);
            }
            flag = !flag;
            res.add(temp);
        }

        return  res;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;};
    }

}



