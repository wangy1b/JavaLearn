package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

 例如:
 给定二叉树: [3,9,20,null,null,15,7],

    3
  /  \
 9   20
    /  \
   15   7

 返回：
 [3,9,20,15,7]

 提示：
 节点总数 <= 1000

 https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 */
public class BinaryTreeLevelOrder {
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList();
        TreeNode h = root;
        queue.add(h);
        while (!queue.isEmpty()) {
            h = queue.poll();
            if (h != null)
                list.add(h.val);
            if (h.left != null)
                queue.add(h.left);
            if (h.right != null)
                queue.add(h.right);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrder b = new BinaryTreeLevelOrder();
        String nums = "3,9,20,null,null,15,7";
        TreeNode root = TreeNode.transArrayToTree(nums);
        int[] res = b.levelOrder(root);
        for (int i = 0;i < res.length;i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
