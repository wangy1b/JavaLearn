package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeMorrisTraversal {

    /*
    * Morris 中序遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
    * 1.如果 x 无左孩子，则访问 x 的右孩子，即 x=x.right。
    * 2.如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），记为 predecessor。
    *  根据 predecessor 的右孩子是否为空，进行如下操作:
    *   2.1.如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
    *   2.2.如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明已经遍历完 x 的左子树，
    *       将 predecessor 的右孩子置空，然后访问 x 的右孩子，即 x=x.right。
    * 3.重复上述操作，直至访问完整棵树。
    *
    * 复杂度分析：
    * 时间复杂度：O(N)，其中 N 为二叉搜索树的高度。Morris 遍历中每个节点会被访问两次，因此总时间复杂度为 O(2N)=O(N)。
    * 空间复杂度：O(1)。
    *
    * */
    public List morrisInorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode h = root, pred = h; // 仅存放两个临时变量，O(1)空间复杂度
        while (h != null) {
            // h 无左孩子
            if (h.left == null) {
                res.add(h.val);
                h = h.right;
            } else { // h 有左孩子
                //  pred 为 h 左子树上最右的节点
                pred = h.left;
                // 找到左子树上最右的节点，pred.right != h 用来防止越过当前的节点h，去取h的右子节点

                // 这是其中最关键的一步是判断前驱节点是否访问过。 pred.right != h
                // 注意到如果前驱节点访问过，则其右孩子必然为当前节点，否则必然为空。
                // 据此可以判断应当深入左子树还是右子树。
                while (pred.right != null && pred.right != h) {
                    pred = pred.right;
                }
                // 如果 pred 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子
                if (pred.right == null) {
                    pred.right = h;
                    h = h.left;
                } else { // 如果 pred 的右孩子不为空，说明左子树已经访问完了，我们需要断开链接，然后访问 h 的右孩子
                    pred.right = null;
                    res.add(h.val);
                    h = h.right;
                }
            }
        }
        return res;
    }
    /*
    * 根据Morris中序遍历，其先序遍历和后序遍历都是在中序遍历的基础之上加以改动得到的。
    * 例如先序遍历时，需要先访问节点，再决定深入左子树或右子树：
    * */
    public List morrisPreorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode h = root, pred = h;
        while (h != null) {
            if (h.left == null) {
                res.add(h.val); // 先访问当前节点
                h = h.right;
            } else {
                pred = h.left;
                while (pred.right != null && pred.right != h) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    res.add(h.val); // 在确定前驱节点未访问过时，访问当前节点（注意与中序遍历的区别）
                    pred.right = h;
                    h = h.left;
                } else {
                    pred.right = null;
                    h = h.right;
                }
            }
        }
        return res;
    }
    /*
    * 后序遍历的处理有点复杂，因为没有哪个节点是遍历3次的，但是一样可以利用遍历2次的节点来处理，
    * 我们使用的方法时在第二次来到当前节点时，将该节点的左子树的右边界进行逆序输出，
    * 直到左右有左子树的节点的左子树右边界均输出完毕，最后再把根节点的右边边界进行逆序输出即可。
    *
    * ==============================================================================================
    * 后序遍历相比中序遍历稍微复杂一些，但是后序遍历也有其特性：
    * 若一个节点是右孩子，或该节点是左孩子但是没有兄弟节点，则访问完该节点后立刻会访问该节点的父节点。
    *
    * 推广到Morris遍历里，可以得到：
    * 当访问到任何节点C的前驱节点B时，由B到C的路径（不包括节点C）即为之后的访问顺序。
    * 因此所有的访问过程可以化为由B到C的访问。得到的Morris后序遍历程序如下，注意为了保证程序能够顺利访问右子树，为根节点添加了一个哨兵节点：
    *
    * 以此实现后序遍历结果。由于相比较其他两种遍历，后序遍历多了逆序访问的过程，其时间复杂度与链表长度成正比。
    * 因此后序遍历的时间复杂度仍然为O(n)。
    * */
    public List morrisPostorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode h = new TreeNode(Integer.MAX_VALUE, root,null), pred = h;
        while (h != null) {
            if (h.left == null) {
                h = h.right;
            } else {
                pred = h.left;
                while (pred.right != null && pred.right != h) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    pred.right = h;
                    h = h.left;
                } else { // 前驱节点已访问过，恢复树结构
                    visitReverse(h.left, pred, res); // 确定访问过左子树后，逆序访问沿路节点（注意与中序遍历的区别）
                    pred.right = null;
                    h = h.right;
                }
            }
        }
        return res;
    }

    // 对于逆序访问函数visitReverse()，我们可以采用链表翻转的方式实现
    private void visitReverse(TreeNode node1, TreeNode node2, List res) {
        reverse(node1, node2); // 首先进行翻转
        TreeNode node = node2; // 之后进行顺序访问
        while (node != node1) {
            res.add(node.val);
            node = node.right;
        }
        res.add(node1.val);
        reverse(node2, node1); // 恢复结构
    }

    private void reverse(TreeNode node1, TreeNode node2) {
        // 实现链表翻转
        TreeNode prev = node1;
        TreeNode current = prev.right;
        TreeNode next = current.right;
        while (prev != node2) {
            current.right = prev;
            prev = current;
            current = next;
            next = next.right;
        }
    }


    public static void main(String[] args) {
        BinarySearchTreeMorrisTraversal b = new BinarySearchTreeMorrisTraversal();
        String nums = "2,0,4,null,1,3,5,null,null,null,null,null,6";
        TreeNode root = TreeNode.transArrayToTree(nums);
        List<Integer> res = b.morrisInorderTraversal(root); // [0, 1, 2, 3, 4, 5, 6]
        // List<Integer> res = b.morrisPreorderTraversal(root); // [2, 0, 1, 4, 3, 5, 6]
        // List<Integer> res = b.morrisPostorderTraversal(root);  // [1, 0, 3, 6, 5, 4, 2]
        System.out.println(res);
    }
}
