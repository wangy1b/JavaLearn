package com.wyb.leetcode;

import java.util.*;

/*

113. 路径总和 II
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

https://leetcode-cn.com/problems/path-sum-ii/

 */
public class PathSumII {

    public static void main(String[] args) {
        // int[] nums = {5,4,8,11,(Integer)null,13,4,7,2,(Integer)null,(Integer)null,5,1};
        TreeNode treeNode = new TreeNode(
                5
                ,new TreeNode(4
                                ,new TreeNode(11
                                                ,new TreeNode(7)
                                                ,new TreeNode(2))
                                ,null)
                ,new TreeNode(8
                                ,new TreeNode(13)
                                ,new TreeNode(4
                                                ,new TreeNode(5)
                                                ,new TreeNode(1)))
        );

        System.out.println(pathSum(treeNode,22));
    }

    // todo bfs
    private static List<List<Integer>> pathSum(TreeNode root, int sum) {
        Queue<TreeNode> queue = new LinkedList<>();
        List res = new ArrayList();
        if (root == null) {
            return res;
        }
        queue.add(root);
        int ttl = 0;
        TreeNode node ;
        while (!queue.isEmpty()){
            int size = queue.size();
            List tmp = new ArrayList();
            int tmpTtl = 0;
            while ( size-- > 0 ) {
                node = queue.poll();
                if ( ttl + tmpTtl + node.val == sum ) {
                    tmp.add(node.val);
                    res.add(tmp);
                    break;
                } else {
                    tmpTtl += node.val;
                    ttl += tmpTtl;
                    tmp.add(node.val);
                }

                // 加左节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                // 加右节点
                if (node.right != null) {
                    queue.add(node.right);
                }
            }



        }



        return res;
    }
}
