package com.wyb.leetcode;

import java.util.ArrayDeque;

/*

449. 序列化和反序列化二叉搜索树
序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

编码的字符串应尽可能紧凑。

注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。

https://leetcode-cn.com/problems/serialize-and-deserialize-bst/

 */
public class SerializeAndDeserializeBst {
    public static void main(String[] args) {

        /*

        二叉搜索树具有下列性质的二叉树：
        若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
        若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值

        left.val < p.val  < right.val

        给定二叉树

          1
         / \
        2   5
       / \   \
      3   4   6

         */
        TreeNode treeNode = new TreeNode(
                1
                ,new TreeNode(2
                            ,new TreeNode(3)
                            ,new TreeNode(4)
                            )
                ,new TreeNode(5,new TreeNode(4),new TreeNode(6))
        );


        System.out.println(serialize(treeNode));
        TreeNode res = deserialize(serialize(treeNode));
    }

    // Encodes a tree to a single string.
    private static String serialize(TreeNode root) {
        StringBuffer res = new StringBuffer();
        helper(root,res);
        return res.deleteCharAt(res.length()-1).toString();
    }
    // 后序遍历
    private static void helper(TreeNode node,StringBuffer s) {
        if (node != null) {
            if (node.left != null) {
                helper(node.left,s);
            }
            if (node.right != null) {
                helper(node.right,s);
            }
            s.append(node.val);
            s.append(" ");
        }
    }

    // Decodes your encoded data to tree.
    private static TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        for (String s : data.split("\\s+"))
            nums.add(Integer.valueOf(s));
        return helper2(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);

    }

    // TODO: 2020/9/7 not finished
    private static TreeNode helper2(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) return null;
        int val = nums.getLast();
        if (val < lower || val > upper) {
            return null;
        }

        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper2(val, upper, nums);
        root.left = helper2(lower, val, nums);
        return root;
    }


}
