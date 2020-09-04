package com.wyb.leetcode;
/*

543. 二叉树的直径
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。



示例 :
给定二叉树

          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。



注意：两结点之间的路径长度是以它们之间边的数目表示。

https://leetcode-cn.com/problems/diameter-of-binary-tree/

 */
public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                1
                ,new TreeNode(2
                    ,new TreeNode(4)
                    ,new TreeNode(5)
        )
                ,new TreeNode(3)
        );

        System.out.println(diameterOfBinaryTree(treeNode));
    }

    /*

    方法：深度优先搜索
    首先我们知道一条路径的长度为该路径经过的节点数减一，所以求直径（即求路径长度的最大值）等效于求路径经过节点数的最大值减一。

    而任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到。

    如图我们可以知道路径 [9, 4, 2, 5, 7, 8] 可以被看作以 22 为起点，从其左儿子向下遍历的路径 [2, 4, 9] 和从其右儿子向下遍历的路径 [2, 5, 7, 8] 拼接得到。

    假设我们知道对于该节点的左儿子向下遍历经过最多的节点数 L （即以左儿子为根的子树的深度）
    和其右儿子向下遍历经过最多的节点数 R （即以右儿子为根的子树的深度），那么以该节点为起点的路径经过节点数的最大值即为 L+R+1 。

    我们记节点 node 为起点的路径经过节点数的最大值为 d node ，那么二叉树的直径就是所有节点d node 的最大值减一。


    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/er-cha-shu-de-zhi-jing-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    static int ans;
    private static int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    private static int depth(TreeNode node) {
        if (node == null) return 0; // 访问到空节点了，返回0
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }


    private static int helper(TreeNode node){
        // 访问到空节点了，返回0
        if (node == null) return 0;
        // 左儿子为根的子树的深度
        int l = helper(node.left);
        // 右儿子为根的子树的深度
        int r = helper(node.right);
        // 返回该节点为根的子树的深度
        return Math.max(l ,r) + 1;
    }
}
