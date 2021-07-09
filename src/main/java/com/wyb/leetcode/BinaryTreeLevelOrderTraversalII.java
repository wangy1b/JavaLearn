package com.wyb.leetcode;

import java.util.*;

/*
* 107. 二叉树的层序遍历 II
给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层序遍历为：

[
  [15,7],
  [9,20],
  [3]
]

https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
* */
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversalII l = new BinaryTreeLevelOrderTraversalII();
        String nums = "3,9,20,null,null,15,7";
        TreeNode root = TreeNode.transArrayToTree(nums);
        System.out.println(l.levelOrderBottom(root));
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
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
                if (node.left != null) queue.add(node.left);
                // 右孩子不空，加入queue
                if (node.right != null) queue.add(node.right);
            }
            // ans.add(temp);
            stack.push(temp);
        }

        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans;
    }

    /*
    * 方法一：广度优先搜索
    * 树的层次遍历可以使用广度优先搜索实现。从根节点开始搜索，每次遍历同一层的全部节点，使用一个列表存储该层的节点值。

    * 如果要求从上到下输出每一层的节点值，做法是很直观的，在遍历完一层节点之后，将存储该层节点值的列表添加到结果列表的尾部。
    * 这道题要求从下到上输出每一层的节点值，只要对上述操作稍作修改即可：在遍历完一层节点之后，将存储该层节点值的列表添加到结果列表的头部。

    * 复杂度分析

    * 时间复杂度：O(n)，其中 n 是二叉树中的节点个数。每个节点访问一次，结果列表使用链表的结构时，
    * 在结果列表头部添加一层节点值的列表的时间复杂度是 O(1)，因此总时间复杂度是 O(n)。

    * 空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度取决于队列开销，队列中的节点个数不会超过 n。

    * 作者：LeetCode-Solution
    * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/er-cha-shu-de-ceng-ci-bian-li-ii-by-leetcode-solut/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    * */
    public List<List<Integer>> levelOrderBottomOfficial(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            levelOrder.add(0, level);
        }
        return levelOrder;
    }


}
