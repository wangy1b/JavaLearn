package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/*

99. 恢复二叉搜索树
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2

示例 2:

输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
进阶:

使用 O(n) 空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？

https://leetcode-cn.com/problems/recover-binary-search-tree/

 */
public class RecoverBinarySearchTree {

    public void recoverTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int x = -1, y = -1;
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i)  > list.get(i + 1) ) {
                y = list.get(i + 1);
                if (x == -1) {
                    x = list.get(i);
                } else {
                    break;
                }
            }
        }

        recover(root,2, x, y);

    }

    private void inorder(TreeNode root,List<Integer> res){
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    private void recover(TreeNode root, int cnt, int x, int y){
        if (cnt == 0 || root == null) return;
        if (root.val == x || root.val == y) {
            root.val = root.val == x ?  y : x;
            cnt --;
        }
        recover(root.left, cnt, x, y);
        recover(root.right, cnt, x, y);
    }


    public static void main(String[] args) {
        RecoverBinarySearchTree r = new RecoverBinarySearchTree();
        String nums = "1,3,null,null,2";
        // String nums = "3,1,4,null,null,2";
        TreeNode root = TreeNode.transArrayToTree(nums);
        r.recoverTree(root);
    }
}