package com.wyb.leetcode;

import java.util.*;

/*

429. N 叉树的层序遍历
给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。

树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。



示例 1：



输入：root = [1,null,3,2,4,null,5,6]
输出：[[1],[3,2,4],[5,6]]
示例 2：



输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]


提示：

树的高度不会超过 1000
树的节点总数在 [0, 10^4] 之间

https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/

 */
public class NTreeLevelOrderTraversal {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        // 两种数据结构： Queue + ArrayList
        Queue<Node> queue = new LinkedList<>();
        // 核心方案：Queue中取出一个元素，再把他的所有孩子放入Queue
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每层临时结果
            ArrayList<Integer> temp = new ArrayList<>();
            // 先获取当前这层的size,防止fast fail
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // Queue中取出一个节点
                Node node = queue.remove();
                // 把值加入list
                temp.add(node.val);
                // 该节点的所有子节点都加入queue
                queue.addAll(node.children);
            }
            ans.add(temp);
        }
        return ans;
    }

    /*
    方法二：简化的广度优先搜索
     */
    public List<List<Integer>> levelOrderOfficial2(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Node> previousLayer = Arrays.asList(root);

        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }

        return result;
    }

    /**
     * 方法三：递归
     算法：
     我们可以使用递归来解决这个问题，通常我们不能使用递归进行广度优先搜索。
     这是因为广度优先搜索基于队列，而递归运行时使用堆栈，适合深度优先搜索。
     但是在本题中，我们可以以不同的顺序添加到最终列表中，只要我们知道节点在哪一层并确保在那一层的列表顺序正确就可以了

     作者：LeetCode
     链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/solution/ncha-shu-de-ceng-xu-bian-li-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrderOfficial3(Node root) {
        if (root != null) traverseNode(root, 0);
        return result;
    }

    private void traverseNode(Node node, int level) {
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        for (Node child : node.children) {
            traverseNode(child, level + 1);
        }
    }


}
