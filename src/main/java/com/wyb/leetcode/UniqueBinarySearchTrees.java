package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/*

二叉搜索树(左 < 根 < 右)

96. 不同的二叉搜索树
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

https://leetcode-cn.com/problems/unique-binary-search-trees/

 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        System.out.println(numTrees(3));

    }

    /*

    方法一：动态规划
    思路

    给定一个有序序列 1⋯n，为了构建出一棵二叉搜索树，我们可以遍历每个数字 i，将该数字作为树根，
    将 1⋯(i−1) 序列作为左子树，将 n(i+1)⋯n 序列作为右子树。接着我们可以按照同样的方式递归构建左子树和右子树。

    在上述构建的过程中，由于根的值不同，因此我们能保证每棵二叉搜索树是唯一的。

    由此可见，原问题可以分解成规模较小的两个子问题，且子问题的解可以复用。因此，我们可以想到使用动态规划来求解本题。

    算法

    题目要求是计算不同二叉搜索树的个数。为此，我们可以定义两个函数：

    G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数。
    对于边界情况，当序列长度为 1（只有根）或为 0（空树）时，只有一种情况，即：

    G(0)=1,G(1)=1
    给定序列 1⋯n，我们选择数字 i 作为根，则根为 i 的所有二叉搜索树的集合是左子树集合和右子树集合的笛卡尔积，
    对于笛卡尔积中的每个元素，加上根节点之后形成完整的二叉搜索树

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
