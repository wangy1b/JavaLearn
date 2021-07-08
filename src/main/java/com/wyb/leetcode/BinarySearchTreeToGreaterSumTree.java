package com.wyb.leetcode;

/**
 * 1038. 把二叉搜索树转换为累加树
 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

 提醒一下，二叉搜索树满足下列约束条件：

 节点的左子树仅包含键 小于 节点键的节点。
 节点的右子树仅包含键 大于 节点键的节点。
 左右子树也必须是二叉搜索树。
 注意：该题目与 538: https://leetcode-cn.com/problems/convert-bst-to-greater-tree/  相同
                              4(30)
                           /      \
                        1(36)      6(21)
                      /   \        /    \
                   0(36)  2(35)   5(26)  7(15)
                           \              \
                           3(33)           8(8)


 示例 1：



 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 示例 2：

 输入：root = [0,null,1]
 输出：[1,null,1]
 示例 3：

 输入：root = [1,0,2]
 输出：[3,3,2]
 示例 4：

 输入：root = [3,2,4,1]
 输出：[7,9,4,10]


 提示：

 树中的节点数介于 1 和 100 之间。
 每个节点的值介于 0 和 100 之间。
 树中的所有值 互不相同 。
 给定的树为二叉搜索树。

 https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/
 */
public class BinarySearchTreeToGreaterSumTree {
    // 用反过来的中序遍历累加当前值
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        InOrderReverse(root);
        return root;
    }

    int accSum = 0;
    private void InOrderReverse(TreeNode root){
        if (root.right != null)
            InOrderReverse(root.right);
        accSum += root.val;
        root.val = accSum;
        if (root.left != null)
            InOrderReverse(root.left);
    }

    public static void main(String[] args) {
        BinarySearchTreeToGreaterSumTree b =
                new BinarySearchTreeToGreaterSumTree();
        String nums = "3,2,4,1";
        TreeNode root = TreeNode.transArrayToTree(nums);
        TreeNode res = b.bstToGst(root);
    }

    // 简化一下：反中序遍历累加当前值
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
