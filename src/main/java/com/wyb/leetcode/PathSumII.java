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

        // System.out.println(pathSum(treeNode,22));
        System.out.println(pathSumII(treeNode,22));
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


    /*

    此题在112题上，有以下两点变化：

    要返回成功路径；
    此问题只需传入一个list，递归同时记录路径；
    有多条成功路径；
    此问题只需全盘遍历即可，而不是有一个结果即返回；

    作者：lonepwq
    链接：https://leetcode-cn.com/problems/path-sum-ii/solution/java-dfsdi-gui-yi-dong-10000-by-lonepwq/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    // 全局变量来获取所有的结果路径
    static List<List<Integer>> res = new ArrayList();

    private static List<List<Integer>> pathSumII(TreeNode root, int sum) {
        LinkedList<Integer> temp = new LinkedList<>();// 定义一个有序list来存储路径
        helper(root, sum, temp);
        return res;
    }

    private static void helper(TreeNode node, int sum, LinkedList<Integer> temp) {
        if (node == null) {
            return;
        }
        // 记录路径
        temp.addLast(node.val);
        if (node.left == null && node.right == null && sum == node.val) {
            res.add(new ArrayList(temp));
        }
        helper(node.left, sum - node.val, temp);
        helper(node.right, sum - node.val, temp);
        // 重点，遍历完后，需要把当前节点remove出去，因为用的是同一个list对象来存所有的路径
        temp.removeLast();
    }

}
