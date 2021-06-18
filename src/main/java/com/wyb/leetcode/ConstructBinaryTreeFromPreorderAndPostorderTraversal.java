package com.wyb.leetcode;

import java.util.Arrays;

/*

889. 根据前序和后序遍历构造二叉树
返回与给定的前序和后序遍历匹配的任何二叉树。

 pre 和 post 遍历中的值是不同的正整数。



示例：

输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
输出：[1,2,3,4,5,6,7]


提示：

1 <= pre.length == post.length <= 30
pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。

https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
       int[] pre = {1,2,4,5,3,6,7}, post = {4,5,2,6,7,3,1};
        ConstructBinaryTreeFromPreorderAndPostorderTraversal c =
                new ConstructBinaryTreeFromPreorderAndPostorderTraversal();
       // TreeNode res = c.constructFromPrePost(pre,post);
       TreeNode res = c.myBuildTree(pre,post);
    }

    // 前序：根节点,[左子节点前序],[右子节点前序]
    // 后序：[左子节点中序],[右子节点中序],根节点
    // 	pre子数组的第一个为左子节点的头节点
    // 	post子数组的最后一个为右子节点的头节点
    public TreeNode myBuildTree(int[] preorder, int[] postorder) {
        int len = preorder.length;
        if (len == 0) return null;
        TreeNode res = new TreeNode(preorder[0]);
        if (len == 1) return res;
        // 根据前序确定的根节点，找出左子树
        int preLeftIdx = 1;
        int preRightIdx = len - 1;
        for (int i = 1; i < len; i++) {
            if (preorder[i] == postorder[len - 2]) {
                preRightIdx = i;
                break;
            }
        }


        int postLeftIdx = preLeftIdx - 1;
        int postRightIdx = preRightIdx - 1;

        int[] subPreHalf1 = Arrays.copyOfRange(preorder, preLeftIdx, preRightIdx);
        int[] subPostHalf1 = Arrays.copyOfRange(postorder, postLeftIdx, postRightIdx);
        res.left = myBuildTree(subPreHalf1, subPostHalf1);

        int[] subPreHalf2 = Arrays.copyOfRange(preorder, preRightIdx , len);
        int[] subPosHalf2 = Arrays.copyOfRange(postorder, postRightIdx, len - 1);
        res.right = myBuildTree(subPreHalf2, subPosHalf2);

        return res;
    }


    /*

    算法

    我们令左分支有 L 个节点。我们知道左分支的头节点为 pre[1]，
    但它也出现在左分支的后序表示的最后。所以 pre[1] = post[L-1]（因为结点的值具有唯一性），
    因此 L = post.indexOf(pre[1]) + 1。

    现在在我们的递归步骤中，左分支由 pre[1 : L+1] 和 post[0 : L] 重新分支，
    而右分支将由 pre[L+1 : N] 和 post[L : N-1] 重新分支。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/solution/gen-ju-qian-xu-he-hou-xu-bian-li-gou-zao-er-cha-sh/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if (N == 1) return root;

        int L = 0;
        for (int i = 0; i < N; ++i)
            if (post[i] == pre[1])
                L = i+1;

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L+1),
                Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L+1, N),
                Arrays.copyOfRange(post, L, N-1));
        return root;
    }

    /*

    方法二：递归（节约空间的变体）
    说明

    我们这里提出一个方法一的变体，使用索引指代子数组 pre 和 post，
    而不是通过那些子数组的副本。这里，(i0, i1, N) 指的是 pre[i0:i0+N], post[i1:i1+N].

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/solution/gen-ju-qian-xu-he-hou-xu-bian-li-gou-zao-er-cha-sh/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */


    private int[] pre, post;
    private TreeNode constructFromPrePost2(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0, 0, pre.length);
    }

    private TreeNode make(int i0, int i1, int N) {
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) return root;

        int L = 1;
        for (; L < N; ++L)
            if (post[i1 + L-1] == pre[i0 + 1])
                break;

        root.left = make(i0+1, i1, L);
        root.right = make(i0+L+1, i1+L, N-1-L);
        return root;
    }
}
