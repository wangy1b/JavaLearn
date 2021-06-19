package com.wyb.leetcode;

import java.util.*;


/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * <p>
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class LowestCommonAncestor {
    /*
    方法二：一次遍历
    思路与算法

    在方法一中，我们对从根节点开始，通过遍历找出到达节点 p 和 q 的路径，
    一共需要两次遍历。我们也可以考虑将这两个节点放在一起遍历。

    整体的遍历过程与方法一中的类似：

    ·我们从根节点开始遍历；
    ·如果当前节点的值大于 p 和 q 的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
    ·如果当前节点的值小于 p 和 q 的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
    ·如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。
      此时，p 和 q 要么在当前节点的不同的子树中，要么其中一个就是当前节点。

    可以发现，如果我们将这两个节点放在一起遍历，我们就省去了存储路径需要的空间。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-26/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }


    public static void main(String[] args) {
        LowestCommonAncestor l = new LowestCommonAncestor();
        String nums = "6,2,8,0,4,7,9,null,null,3,5";
        TreeNode root = TreeNode.transArrayToTree(nums);
        TreeNode res = l.lowestCommonAncestor(root,new TreeNode(3),new TreeNode(5));
        System.out.println(res.val);
    }

}
