package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/*

653. 两数之和 IV - 输入 BST
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:

输入:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True


案例 2:

输入:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False

https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 */
public class TwoSumIvInputIsABst {

    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(
                5
                , new TreeNode(3
                    , new TreeNode(2)
                    , new TreeNode(4)
                )
                , new TreeNode(6, null, new TreeNode(7))
        );
        System.out.println(findTarget(treeNode, 29));
    }

    /*

    方法三：使用 BST【通过】
    算法

    在本方法中利用 BST 的性质，BST 的中序遍历结果是按升序排列的。因此，中序遍历给定的 BST，并将遍历结果存储到 list 中。

    遍历完成后，使用两个指针 l 和 r 作为 list 的头部索引和尾部索引。然后执行以下操作：

    检查 l 和 r 索引处两元素之和是否等于 k。如果是，立即返回 True。

    如果当前两元素之和小于 k，则更新 l 指向下一个元素。这是因为当我们需要增大两数之和时，应该增大较小数。

    如果当前两元素之和大于 k，则更新 r 指向上一个元素。这是因为当我们需要减小两数之和时，应该减小较大数。

    重复步骤一至三，直到左指针 ll 大于右指针 rr。

    如果左指针 l 到右指针 r 的右边，则返回 False。

    注意，在任何情况下，都不应该增大较大的数，也不应该减小较小的数。这是因为如果当前两数之和大于 kk，不应该首先增大 list[r] 的值。类似的，也不应该首先减小 list[l] 的值。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/solution/liang-shu-zhi-he-iv-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    private static boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList();
        inorder(root,list);
        System.out.println("list : "+list);
        int i = 0,j = list.size()-1;
        while ( i < j) {
            int sum = list.get(i) + list.get(j);
            if ( sum > k) {
                j--;
            } else if (list.get(i) + list.get(j) < k) {
                i++;
            }
            else if (sum == k) {
                System.out.println("first : " + list.get(i));
                System.out.println("second : " + list.get(j));
                return true;
            }
        }


        return false;
    }


    private static void inorder(TreeNode node, ArrayList list){
        if (node != null) {
            if (node.left != null) {
                inorder(node.left,list);
            }
            list.add(node.val);
            if (node.right != null) {
                inorder(node.right,list);
            }
        }
    }

}
