package com.wyb.leetcode;

/**
 * 701. 二叉搜索树中的插入操作
 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 返回插入后二叉搜索树的根节点。
 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。


 示例 1：

       4                     4                    5
     /   \                 /   \                 / \
    2     7     =>        2     7     or       2    7
  /  \                  /  \   /              / \
 1    3                1   3  5             1    3
                                                  \
                                                   4

 输入：root = [4,2,7,1,3], val = 5
 输出：[4,2,7,1,3,5]
 解释：另一个满足题目要求的树也可以

 示例 2：

 输入：root = [40,20,60,10,30,50,70], val = 25
 输出：[40,20,60,10,30,50,70,null,null,25]

 示例 3：

 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 输出：[4,2,7,1,3,5]

 提示：

 给定的树上的节点数介于 0 和 10^4 之间
 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 -10^8 <= val <= 10^8
 新值和原始二叉搜索树中的任意节点值都不同

 https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 */
public class BinarySearchTreeAddNode {
    // 二叉搜索树插入的过程有点类似查找操作。
    // 新插入的数据一般都是再叶子节点上，所以需要从根节点开始，依次比较要插入的数据和节点的大小:
    // 1.如果插入的数据比节点的数据大，并且节点右子树为空，就将新数据直接查到右子节点的位置；
    //   如果右子树不为空，就再递归遍历右子树，找到插入位置。
    // 2.同理，如果插入的数据比节点数据小，并且节点左子树为空，就将新数据直接查到左子节点的位置；
    //     如果左子树不为空，就再递归遍历左子树，找到插入位置。
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        TreeNode p = root;
        while (p != null) {
            if (val > p.val) {
                if (p.right == null) {
                    p.right = new TreeNode(val);
                    break;
                }else
                    p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    break;
                }
                p = p.left;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        BinarySearchTreeAddNode b = new BinarySearchTreeAddNode();
        // String nums = "4,2,7,1,3";
        // int val = 5;
        String nums = "40,20,60,10,30,50,70";
        int val = 25;
        TreeNode root = TreeNode.transArrayToTree(nums);
        TreeNode res = b.insertIntoBST(root, val);
    }
}
