package com.wyb.leetcode;

import java.util.*;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 请实现一个函数按照之字形顺序打印二叉树，
 即第一行按照从左到右的顺序打印，
 第二层按照从右到左的顺序打印，
 第三行再按照从左到右的顺序打印，其他行以此类推。

 例如:
 给定二叉树: [3,9,20,null,null,15,7],

   3
 /  \
 9  20
   /  \
  15   7
 返回其层次遍历结果：

 [
 [3],
 [20,9],
 [15,7]
 ]


 提示：
 节点总数 <= 1000

 https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 */
public class BinaryTreeLevelOrderIII {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 0)
                level++;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode remove = queue.poll();
                if (remove != null)
                    list.add(remove.val);
                if (remove.left != null) {
                    queue.add(remove.left);
                }
                if (remove.right != null) {
                    queue.add(remove.right);
                }
            }
            if ((level & 1) == 0)
                Collections.reverse(list);
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderIII b = new BinaryTreeLevelOrderIII();
        // String nums = "3,5,1,6,2,0,8,null,null,7,4";
        String nums = "3,9,20,null,null,15,7";
        TreeNode root = TreeNode.transArrayToTree(nums);
        List res = b.levelOrder(root);
        System.out.println(res);
    }
}
