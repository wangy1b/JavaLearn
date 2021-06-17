package com.wyb.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*

101. 对称二叉树
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3


但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3


进阶：

你可以运用递归和迭代两种方法解决这个问题吗？

https://leetcode-cn.com/problems/symmetric-tree/

 */
public class IsSymmetric {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        //[1,2,2,null,3,null,3]
        TreeNode treeNode2 = new TreeNode(1, new TreeNode(2,null, new TreeNode(3)), new TreeNode(2,null, new TreeNode(3)));

        // System.out.println(isSymmetric(treeNode1));
        System.out.println(isSymmetric(treeNode2));

    }


    private static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
         TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right; }
     }

     /*
     方法一：递归
    思路和算法
    如果一个树的左子树与右子树镜像对称，那么这个树是对称的。

      */

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode l, TreeNode r){
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return (l.val == r.val) &&
                isMirror(l.left , r.right) &&
                isMirror(l.right , r.left);
    }


    /**
     * 方法二：迭代
     思路和算法

     「方法一」中我们用递归的方法实现了对称性的判断，那么如何用迭代的方法实现呢？
     首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。
     初始化时我们把根节点入队两次。
     每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），
     然后将两个结点的左右子结点按相反的顺序插入队列中。
     当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    public boolean isSymmetricOfficial2(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

}
