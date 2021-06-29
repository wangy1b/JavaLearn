package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

102. 二叉树的层序遍历
给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。



示例：
二叉树：[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

https://leetcode-cn.com/problems/binary-tree-level-order-traversal/

 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal b = new BinaryTreeLevelOrderTraversal();
        TreeNode root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(3,
                                new TreeNode(2,
                                        new TreeNode(1),
                                        null),
                                null),
                        null),
                null);

        List<List<Integer>> res = b.levelOrderBSF(root);
        System.out.println(res);

    }

    List<List<Integer>> res = new ArrayList<>();
    /*

    可以用宽度优先搜索解决这个问题。
    我们可以想到最朴素的方法是用一个二元组 (node, level) 来表示状态，
    它表示某个节点和它所在的层数，每个新进队列的节点的 level 值都是父亲节点的 level 值加一。
    最后根据每个点的 level 对点进行分类，分类的时候我们可以利用哈希表，维护一个以 level 为键，
    对应节点值组成的数组为值，宽度优先搜索结束以后按键 level 从小到大取出所有值，组成答案返回即可。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

   */
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        if (root == null) return res;
        helper(root,0);
        return res;
    }

    public void helper(TreeNode node,int level) {
        if (res.size() == level ) {
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(node.val);

        if (node.left != null) {
            helper(node.left,level + 1 );
        }
        if (node.right != null) {
            helper(node.right,level + 1 );
        }
    }



    public List<List<Integer>> levelOrderBSF(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        List<Integer> tmp = new ArrayList<Integer>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode remove = queue.remove();
                list.add(remove.val);
                if (remove.left != null) {
                    queue.add(remove.left);
                }
                if (remove.right != null) {
                    queue.add(remove.right);
                }
            }
            res.add(list);
        }




        return res;
    }

}
