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
    // 复杂度分析
    // 时间复杂度：O(n)，其中 n 是二叉搜索树的节点数。每一个节点恰好被遍历一次。
    // 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。
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

    /*
    * 方法二：Morris 遍历
    思路及算法

    有一种巧妙的方法可以在线性时间内，只占用常数空间来实现中序遍历，
    这种方法由 J. H. Morris 在 1979 年的论文「Traversing Binary Trees Simply and Cheaply」中首次提出。因此被称为 Morris 遍历。

    Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。
    其反序中序遍历规则总结如下：

    1.如果当前节点的右子节点为空，处理当前节点，并遍历当前节点的左子节点；

    2.如果当前节点的右子节点不为空，找到当前节点右子树的最左节点（该节点为当前节点中序遍历的前驱节点）；
      2.1.如果最左节点的左指针为空，将最左节点的左指针指向当前节点，遍历当前节点的右子节点；
      2.2.如果最左节点的左指针不为空，将最左节点的左指针重新置为空（恢复树的原状），处理当前节点，并将当前节点置为其左节点；

    3.重复步骤 1 和步骤 2，直到遍历结束。

    这样我们利用 Morris 遍历的方法，反序中序遍历该二叉搜索树，即可实现线性时间与常数空间的遍历。

    复杂度分析:
    时间复杂度：O(n)，其中 n 是二叉搜索树的节点数。没有左子树的节点只被访问一次，有左子树的节点被访问两次。
    空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/solution/cong-er-cha-sou-suo-shu-dao-geng-da-he-shu-by-leet/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    * */

    public TreeNode convertBSTMorris(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
}
