package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*

501. 二叉搜索树中的众数
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

   1
    \
     2
    /
   2
返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/

 */
public class FindModeInBinarySearchTree {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                5
                ,new TreeNode(3
                    ,new TreeNode(3)
                    ,new TreeNode(3)
                )
                ,new TreeNode(6,null, new TreeNode(7))
        );

        int[] res = findMode(treeNode);
        for (int i : res) {
            System.out.println(i);
        }

    }
    static int len = 0;
    private static void inorder(TreeNode node, HashMap<Integer,Integer> map){
        if (node != null) {
            len ++;
            if (node.left != null) {
                inorder(node.left,map);
            }
            map.put(node.val,map.getOrDefault(node.val,0)+1);

            if (node.right != null) {
                inorder(node.right,map);
            }
        }
    }

    private static int[] findMode(TreeNode root) {
        HashMap<Integer,Integer> map = new HashMap();
        inorder(root,map);
        int[] tmp = new int[len];
        int max_times = 0;
        for (Integer key : map.keySet()) {
            max_times = Math.max(max_times,map.get(key));
        }
        // System.out.println("max_times : " + max_times);
        int idx = 0;
        for (Integer key : map.keySet()) {
            if (max_times == map.get(key)){
                tmp[idx++] = key;
            }
        }
        int[] res = new int[idx];
        System.arraycopy(tmp,0,res,0,idx);
        return res;
    }

}
