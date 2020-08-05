package com.wyb.leetcode;

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

}
