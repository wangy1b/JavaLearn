package com.wyb.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

99. 恢复二叉搜索树
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2

示例 2:

输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
进阶:

使用 O(n) 空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？

https://leetcode-cn.com/problems/recover-binary-search-tree/

 */
public class BinarySearchTreeRecover {

    public void recoverTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int x = -1, y = -1;
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i)  > list.get(i + 1) ) {
                y = list.get(i + 1);
                if (x == -1) {
                    x = list.get(i);
                } else {
                    break;
                }
            }
        }

        recover(root,2, x, y);

    }

    private void inorder(TreeNode root,List<Integer> res){
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    private void recover(TreeNode root, int cnt, int x, int y){
        if (cnt == 0 || root == null) return;
        if (root.val == x || root.val == y) {
            root.val = root.val == x ?  y : x;
            cnt --;
        }
        recover(root.left, cnt, x, y);
        recover(root.right, cnt, x, y);
    }


    public static void main(String[] args) {
        BinarySearchTreeRecover r = new BinarySearchTreeRecover();
        String nums = "1,3,null,null,2";
        // String nums = "3,1,4,null,null,2";
        TreeNode root = TreeNode.transArrayToTree(nums);
        // r.recoverTree(root);
        r.recoverTreeOfficial2(root);
    }


    /**
     * 方法二：隐式中序遍历
     思路与算法

     方法一是显式地将中序遍历的值序列保存在一个 nums 数组中，然后再去寻找被错误交换的节点，
     但我们也可以隐式地在中序遍历的过程就找到被错误交换的节点 x 和 y。

     具体来说，由于我们只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，
     且错误交换后最多两个位置不满足条件，因此在中序遍历的过程我们只需要维护当前中序遍历到的最后一个节点 pred，
     然后在遍历到下一个节点的时候，看两个节点的值是否满足前者小于后者即可，
     如果不满足说明找到了一个交换的节点，且在找到两次以后就可以终止遍历。

     这样我们就可以在中序遍历中直接找到被错误交换的两个节点 x 和 y，不用显式建立 nums 数组。

     中序遍历的实现有迭代和递归两种等价的写法，在本方法中提供迭代实现的写法。使用迭代实现中序遍历需要手动维护栈。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/hui-fu-er-cha-sou-suo-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     */
    public void recoverTreeOfficial2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }

        // 交换值
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    /**
     * 方法三：Morris 中序遍历
     思路与算法

     方法二中我们不再显示的用数组存储中序遍历的值序列，但是我们会发现我们仍需要 O(H) 的栈空间，
     无法满足题目的进阶要求，那么该怎么办呢？
     这里向大家介绍一种不同于平常递归或迭代的遍历二叉树的方法：
     Morris 遍历算法，该算法能将非递归的中序遍历空间复杂度降为 O(1)。

     Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 x）：

     1.如果 x 无左孩子，则访问 x 的右孩子，即 x=x.right。
     2.如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），
       我们记为 predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
         2.1.如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即x=x.left。
         2.2.如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，
         我们将 predecessor 的右孩子置空，然后访问 x 的右孩子，即 x=x.right。
     3.重复上述操作，直至访问完整棵树。

     其实整个过程我们就多做一步：将当前节点左子树中最右边的节点指向它，这样在左子树遍历完成后我们通过这个指向走回了 x，
     且能再通过这个知晓我们已经遍历完成了左子树，而不用再通过栈来维护，省去了栈的空间复杂度。

     了解完这个算法以后，其他地方与方法二并无不同，我们同样也是维护一个 pred 变量去比较即可，具体实现可以看下面的代码，这里不再赘述。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/hui-fu-er-cha-sou-suo-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public void recoverTreeOfficial3(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }

        // 交换值
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

}