package com.wyb.leetcode;

import java.util.*;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // 前序：根节点,[左子节点前序],[右子节点前序]
    // 中序：[左子节点中序],根节点,[右子节点中序]
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;
        TreeNode res = new TreeNode(preorder[0]);
        if (len == 1) return res;
        // 根据前序确定的根节点，找出左子树
        int preIdx = 0;
        for (int i = 0; i < len; i++) {
            if (inorder[i] == preorder[0]) {
                preIdx = i;
                break;
            }
        }

        int[] subPrePreHalf = Arrays.copyOfRange(preorder, 1, preIdx + 1);
        int[] subInPreHalf = Arrays.copyOfRange(inorder, 0, preIdx);
        res.left = buildTree(subPrePreHalf, subInPreHalf);

        int[] subPrePosHalf = Arrays.copyOfRange(preorder, preIdx + 1, len);
        int[] subInPosHalf = Arrays.copyOfRange(inorder, preIdx + 1, len);
        res.right = buildTree(subPrePosHalf, subInPosHalf);

        return res;
    }

    // 构建中序遍历结果的map <val，index> ，方便后续确定根节点位置
    Map<Integer, Integer> inorderMap = new HashMap<>();

    // 前序：根节点,[左子节点前序],[右子节点前序]
    // 中序：[左子节点中序],根节点,[右子节点中序]
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;
        for (int i = 0; i < len; i++) {
            inorderMap.put(inorder[i], i);
        }
        return helper(preorder, 0, len - 1);
    }

    /**
     * @param preorder:    先序数组
     * @param preStartIdx: 先序数组开始的位置
     * @param preEndIdx:   先序数组结束的位置
     * @return
     */
    public TreeNode helper(int[] preorder, int preStartIdx, int preEndIdx) {
        if (preStartIdx > preorder.length - 1) return null;
        TreeNode res = new TreeNode(preorder[preStartIdx]);
        if (preStartIdx == preEndIdx) return res;
        // 根据前序确定的根节点，在中序遍历结果里找到根节点位置
        int inRootIdx = inorderMap.get(preorder[preStartIdx]);

        // 前序：{根节点,[左子节点前序]},[右子节点前序]
        // 中序：{[左子节点中序],根节点},[右子节点中序]

        //todo  preStartIdx + 1 > inRootIdx 时该怎么处理？
        if (preStartIdx >= inRootIdx && preStartIdx + 1 == preorder.length - 1)
            inRootIdx = preorder.length - 1;
        // 取花括号第一部分
        res.left = helper(preorder, preStartIdx + 1, inRootIdx);
        // 取花括号剩下部分
        res.right = helper(preorder, inRootIdx + 1, preEndIdx);


        return res;
    }


    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal c =
                new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        // int[] preorder = {3, 9, 20, 15, 7};
        // int[] inorder = {9, 3, 15, 20, 7};

        // 这个数据和下面一组数据有冲突地方
        int[] preorder = {1, 2, 3};
        int[] inorder = {3, 2, 1};

        // int[] preorder = {1, 2};
        // int[] inorder = {1, 2};

        // TreeNode res = c.buildTree(preorder, inorder);
        // TreeNode res = c.buildTree2(preorder, inorder);
        // TreeNode res = c.buildTreeOfficial1(preorder, inorder);
        TreeNode res = c.buildTreeOfficial2(preorder, inorder);

    }


    // 方法一：递归
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int preorder_left, int preorder_right, int inorder_left, int
            inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTreeOfficial1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0, n - 1);
    }

    // 方法二：迭代
    // 思路
    // 迭代法是一种非常巧妙的实现方法。
    // 对于前序遍历中的任意两个连续节点 u 和 v，根据前序遍历的流程，我们可以知道 u 和 v 只有两种可能的关系：
    //
    // v 是 u 的左儿子。这是因为在遍历到 u 之后，下一个遍历的节点就是 u 的左儿子，即 v；
    //
    // u 没有左儿子，并且 v 是 u 的某个祖先节点（或者 u 本身）的右儿子。
    // 如果 u 没有左儿子，那么下一个遍历的节点就是 u 的右儿子。
    // 如果 u 没有右儿子，我们就会向上回溯，直到遇到第一个有右儿子（且 u 不在它的右儿子的子树中）的节点 u_a
    // ​，那么 v 就是 u_a 的右儿子。
    // 第二种关系看上去有些复杂。我们举一个例子来说明其正确性，并在例子中给出我们的迭代算法。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public TreeNode buildTreeOfficial2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

}
