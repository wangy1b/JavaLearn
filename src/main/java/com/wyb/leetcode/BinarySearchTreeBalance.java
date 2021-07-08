package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1382. 将二叉搜索树变平衡
 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。

 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。

 如果有多种构造方法，请你返回任意一种。
             1                                       2
              \                                    /  \
               2                  =>             1     3
                \                                       \
                 3                                       4
                  \
                   4
 示例：



 输入：root = [1,null,2,null,3,null,4,null,null]
 输出：[2,1,3,null,null,null,4]
 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。


 提示：

 树节点的数目在 1 到 10^4 之间。
 树节点的值互不相同，且在 1 到 10^5 之间。

 https://leetcode-cn.com/problems/balance-a-binary-search-tree/

 */
public class BinarySearchTreeBalance {
    /*
    * 「平衡」要求它是一棵空树或它的左右两个子树的高度差的绝对值不超过 1，
    * 这很容易让我们产生这样的想法——左右子树的大小越「平均」，这棵树会不会越平衡？
    * 于是一种贪心策略的雏形就形成了：
    *    我们可以通过中序遍历将原来的二叉搜索树转化为一个有序序列，然后对这个有序序列递归建树，对于区间 [L, R]：
    *     1.取 mid=(L+R)/2，即中心位置作为当前节点的值；
    *     2.如果 L≤mid−1，那么递归地将区间 [L,mid−1] 作为当前节点的左子树；
    *     3.如果 mid+1≤R，那么递归地将区间 [mid+1,R] 作为当前节点的右子树。
    */

    List<Integer> inorderSeq = new ArrayList<Integer>();
    public TreeNode balanceBST(TreeNode root) {
        getInorder(root);
        return build(0, inorderSeq.size() - 1);
    }

    public void getInorder(TreeNode o) {
        if (o.left != null) {
            getInorder(o.left);
        }
        inorderSeq.add(o.val);
        if (o.right != null) {
            getInorder(o.right);
        }
    }

    public TreeNode build(int l, int r) {
        int mid = (l + r) >> 1;
        TreeNode o = new TreeNode(inorderSeq.get(mid));
        if (l <= mid - 1) {
            o.left = build(l, mid - 1);
        }
        if (mid + 1 <= r) {
            o.right = build(mid + 1, r);
        }
        return o;
    }
    public static void main(String[] args) {

    }
}
