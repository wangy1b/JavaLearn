package com.wyb.leetcode;

import javafx.scene.control.ListCell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*

437. 路径总和 III
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11


 */
public class PathSumIII {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                10
                ,new TreeNode(5
                    ,new TreeNode(3,new TreeNode(3),new TreeNode(-2))
                    ,new TreeNode(2,null,new TreeNode(1))
                )
                ,new TreeNode(-3,null,new TreeNode(11))
        );

        System.out.println(pathSum(treeNode,8));

    }

    // TODO: 2020/9/4 not finished
    private static int pathSum(TreeNode root, int sum) {
        // List res = new ArrayList<>();

        helper(root,sum);
        return cnt;
    }
    static int cnt = 0;
    // static List<List<Integer>> res = new ArrayList();

    private static void helper(TreeNode node,int target){
        if (node == null) return;
        if (node.val == target) {
            cnt ++;
        }
        helper(node.left,target - node.val);
        helper(node.right,target - node.val);

    }
}
