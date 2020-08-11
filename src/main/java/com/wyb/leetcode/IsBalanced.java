package com.wyb.leetcode;
/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/balanced-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class IsBalanced {
    public static void main(String[] args) {
        // TreeNode treeNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(3,new TreeNode(4),new TreeNode(4)), new TreeNode(3)), new TreeNode(2));


        // TreeNode treeNode = new TreeNode(1, new TreeNode(2),null);

        // System.out.println(getDepth(treeNode));
        // System.out.println(getDepth(treeNode.left));
        // System.out.println(getDepth(treeNode.right));
        // System.out.println(getDepth(treeNode.right.left));
        // System.out.println(getDepth(treeNode.right.right));
        System.out.println(myIsBalanced(treeNode));
        System.out.println(isBalancedOfficial1(treeNode));
        System.out.println(isBalancedOfficial2(treeNode));
    }

    private static int getDepth(TreeNode treeNode){
        if (treeNode == null) return 0;
        return Math.max(getDepth(treeNode.left) ,getDepth(treeNode.right)) + 1;
    }

    // 自顶向下的递归
    private static boolean myIsBalanced(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        boolean res = true;
        TreeNode left = root;
        TreeNode right = root;
        int leftDepth = 0;
        int rightDepth = 0;
        if (root.left != null) {
            left = root.left;
            leftDepth = getDepth(left);
        } else {
            left = null;
        }
        if( root.right != null){
            right = root.right;
            rightDepth = getDepth(right);
        } else {
            right = null;
        }

        int depth = Math.abs( leftDepth - rightDepth);
        System.out.println("depth : " + depth );
        if ( depth > 1) return false;
        res = res && myIsBalanced(left) && myIsBalanced(right);

        return res;
    }


    private static boolean isBalancedOfficial1(TreeNode root) {
        // An empty tree satisfies the definition of a balanced tree
        if (root == null) {
            return true;
        }
        // Check if subtrees have height within 1. If they do, check if the
        // subtrees are balanced
        return Math.abs(getDepth(root.left) - getDepth(root.right)) < 2
                && isBalancedOfficial1(root.left)
                && isBalancedOfficial1(root.right);
    }



/*
    方法二：自底向上的递归思路

    方法一计算 height 存在大量冗余。每次调用 height 时，要同时计算其子树高度。
    但是自底向上计算，每个子树的高度只会计算一次。可以递归先计算当前节点的子节点高度，
    然后再通过子节点高度判断当前节点是否平衡，从而消除冗余。

    算法：
    使用与方法一中定义的height 方法。自底向上与自顶向下的逻辑相反，首先判断子树是否平衡，
    然后比较子树高度判断父节点是否平衡。

    算法如下：
    检查子树是否平衡。如果平衡，则使用它们的高度判断父节点是否平衡，并计算父节点的高度。

 */


    // Return whether or not the tree at root is balanced while also storing
    // the tree's height in a reference variable.
    private  static TreeInfo isBalancedTreeHelper(TreeNode root) {
        // An empty tree is balanced and has height = -1
        if (root == null) {
            return new TreeInfo(-1, true);
        }

        // Check subtrees to see if they are balanced.
        TreeInfo left = isBalancedTreeHelper(root.left);
        if (!left.balanced) {
            return new TreeInfo(-1, false);
        }
        TreeInfo right = isBalancedTreeHelper(root.right);
        if (!right.balanced) {
            return new TreeInfo(-1, false);
        }

        // Use the height obtained from the recursive calls to
        // determine if the current node is also balanced.
        if (Math.abs(left.height - right.height) < 2) {
            return new TreeInfo(Math.max(left.height, right.height) + 1, true);
        }
        return new TreeInfo(-1, false);
    }

    private static boolean isBalancedOfficial2(TreeNode root) {
        return isBalancedTreeHelper(root).balanced;
    }


     // * Definition for a binary tree node.
     private static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
         TreeNode(int x, TreeNode left, TreeNode right) {
             this.val = x;
             this.left = left;
             this.right = right;
         }
     }

}


// Utility class to store information from recursive calls
final class TreeInfo {
    public final int height;
    public final boolean balanced;

    public TreeInfo(int height, boolean balanced) {
        this.height = height;
        this.balanced = balanced;
    }
}

