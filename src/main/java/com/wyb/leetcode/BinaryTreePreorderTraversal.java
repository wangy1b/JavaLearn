package com.wyb.leetcode;

import java.util.*;

/*

144. 二叉树的前序遍历
给定一个二叉树，返回它的 前序 遍历。

 示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,2,3]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

https://leetcode-cn.com/problems/binary-tree-preorder-traversal/

 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1,new TreeNode(2,new TreeNode(3),new TreeNode(4)),new TreeNode(5));
        System.out.println(preorderTraversal1(treeNode));
        System.out.println(preorderTraversal11(treeNode));
        // System.out.println(preorderTraversal2(treeNode));
    }

    // 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root,res);
        return res;

    }

    // 前序遍历
    // 中 -> 左 -> 右
    private void helper(TreeNode root ,List<Integer> res){
        if (root != null) {
            res.add(root.val);
            if (root.left != null) {
                helper(root.left,res);
            }
            if (root.right != null) {
                helper(root.right,res);
            }
        }

    }


    // 方法 1：迭代
    // stack + list
    private static List<Integer> preorderTraversal1(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }

    // todo queue + list
    private static List<Integer> preorderTraversal11(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            output.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }

        }
        return output;
    }

    // 方法 2：莫里斯遍历

    /*
    算法的思路是从当前节点向下访问先序遍历的前驱节点，每个前驱节点都恰好被访问两次。
    首先从当前节点开始，向左孩子走一步然后沿着右孩子一直向下访问，
    直到到达一个叶子节点（当前节点的中序遍历前驱节点），
    所以我们更新输出并建立一条伪边 predecessor.right = root 更新这个前驱的下一个点。
    如果我们第二次访问到前驱节点，由于已经指向了当前节点，我们移除伪边并移动到下一个顶点。

    如果第一步向左的移动不存在，就直接更新输出并向右移动。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    private static List<Integer> preorderTraversal2(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                output.add(node.val);
                node = node.right;
            }
            else {
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    output.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                }
                else{
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return output;
    }

}
