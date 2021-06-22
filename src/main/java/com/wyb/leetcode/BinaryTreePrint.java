package com.wyb.leetcode;

import java.util.*;

/**
 * 655. 输出二叉树
 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：

 行数 m 应当等于给定二叉树的高度。
 列数 n 应当总是奇数。

 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。
 根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。
 你应该将左子树输出在左下部分，右子树输出在右下部分。
 左下和右下部分应当有相同的大小。
 即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。
 然而，如果两个子树都为空则不需要为它们留出任何空间。

 每个未使用的空间应包含一个空的字符串""。
 使用相同的规则输出子树。
 示例 1:

 输入:
     1
   /
 2
 输出:
 [["", "1", ""],
 ["2", "", ""]]
 示例 2:

 输入:
       1
     / \
    2   3
    \
    4
 输出:
 [["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 示例 3:

 输入:
          1
         / \
        2   5
      /
    3
  /
 4
 输出:
 [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 注意: 二叉树的高度在范围 [1, 10] 中。

 https://leetcode-cn.com/problems/print-binary-tree/
 */
public class BinaryTreePrint {
    /*** 方法一：递归
     * 创建一个长度为 height*(2^{height}-1) 的数组 res，其中 height 是树的高度。
     * 先使用空字符串 "" 填充数组 res。然后递归调用 fill(res, root, i, l, r) 将节点的值输出到数组 resres 中，
     * 其中 i 表示当前节点所在层数，l 和 r 表示当前子树在数组 res 中的左右边界，
     * 当前节点被输出到数组 res 第 i 行的第 l 列和第 r 列中间位置上。

     在递归方法中，执行以下步骤：

     如果到达树的末尾，即 root = null，直接返回。

     确定当前节点所在的列 j=(l+r)/2。将当前节点输出到数组 res 的第 i 行第 j 列，即 res[i][j]。

     递归调用 fill(res, root.left, i + 1, l, (l + r) / 2)，输出 root 的左子树。

     递归调用 fill(res, root.right, i + 1, (l + r + 1) / 2, r)，输出 root 的右子树。

     注意：在第三步和第四步的递归调用中需要更新行号，确保子节点可以输出的正确的位置。另外，也要根据 l 和 r 更新子树的左右边界。

     另外，创建方法 getHeight(root)， 用于计算 root 为根节点的树高度 height。递归遍历树的所有分支，从中找出最深的一个分支作为树的高度。

     最后，将数组 res 转换成题目要求格式。

     作者：LeetCode
     链接：https://leetcode-cn.com/problems/print-binary-tree/solution/shu-chu-er-cha-shu-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for(String[] arr:res)
            Arrays.fill(arr,"");
        List<List<String>> ans = new ArrayList<>();
        fill(res, root, 0, 0, res[0].length);
        for(String[] arr:res)
            ans.add(Arrays.asList(arr));
        return ans;
    }

    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null)
            return;
        res[i][(l + r) / 2] = "" + root.val;
        fill(res, root.left, i + 1, l, (l + r) / 2);
        fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static void main(String[] args) {
        BinaryTreePrint b = new BinaryTreePrint();
        // String nums = "1,2,5,3,null,null,null,4";
        String nums = "1,2";
        TreeNode root = TreeNode.transArrayToTree(nums);
        List res = b.printTree(root);
        System.out.println(res);
    }
}
