package com.wyb.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/*

208. 实现 Trie (前缀树)
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。


示例：

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True

https://leetcode-cn.com/problems/implement-trie-prefix-tree/

 */
public class MyImplementTriePrefixTree {
    TreeNode[] elements;
    int default_size = 10;
    private int size = 0;
    int index = 0;
    char splitAlpha = 'o';
    int treeHeight = 0;

    private void grow() {
        int old_len = size;
        size = size << 1;
        TreeNode[] new_elements = new TreeNode[size];
        System.arraycopy(elements, 0, new_elements, 0, old_len);
        elements = new_elements;
    }

    private static class TreeNode {
        // <char,isLeaf>
        HashMap<Character, Boolean> val = new HashMap<>();
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(char val, TreeNode left, TreeNode right) {
            this.val.put(val, false);
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyImplementTriePrefixTree() {
        elements = new TreeNode[default_size];
        size = default_size;
    }

    /**
     * Inserts a word into the trie.
     */
    // 向前缀树中插入字符串 word
    public void insert(String word) {
        boolean new_ele = false;
        // 首字母
        char firstChar = word.charAt(0);
        TreeNode eleNode = null;
        // 根据首字母判断是否已经存在tree
        for (int i = 0; i <= index; i++) {
            TreeNode treeNodes = elements[i];
            if (treeNodes != null && treeNodes.val.containsKey(firstChar)) {
                eleNode = treeNodes;
                break;
            } else {
                new_ele = true;
                eleNode = new TreeNode(firstChar, null, null);
                break;
            }
        }

        TreeNode prtNode = eleNode;
        char letter = firstChar;
        // 给对应的treeNode增加节点
        for (int j = 1; j < word.length(); j++) {
            letter = word.charAt(j);
            // a-n 放左侧
            // o-z 放右侧
            if (letter < splitAlpha) {
                // 如果节点val为空就增加字母
                if (prtNode.left == null)
                    prtNode.left = new TreeNode(letter, null, null);
                prtNode = prtNode.left;
            } else {
                // 如果节点val为空就增加字母
                if (prtNode.right == null)
                    prtNode.right = new TreeNode(letter, null, null);
                prtNode = prtNode.right;
            }
            // val如果不存在就加入
            prtNode.val.putIfAbsent(letter, false);
        }
        // 最后一个值的<val,isLeaf>对应的isLeaf改为true
        prtNode.val.put(letter, true);
        if (new_ele) {
            // 判断是否需要扩容
            if (index == size) grow();
            elements[index++] = eleNode;
        }

    }

    /** Returns if the word is in the trie. */
    // 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
    public boolean search(String word) {
        if (word == null || index == 0) return false;
        char lastLetter = word.charAt(word.length()-1);
        TreeNode treeNode = getNodeStartWith(word);
        if (treeNode!= null && treeNode.val.getOrDefault(lastLetter,false)
                && treeHeight == word.length())
            return true;
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    // 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
    public boolean startsWith(String prefix) {
        TreeNode treeNode = getNodeStartWith(prefix);
        if (treeNode == null)
            return false;
        return true;
    }

    private TreeNode getNodeStartWith(String prefix) {
        // 首字母
        char firstChar = prefix.charAt(0);
        // treeNode
        TreeNode treeNode = null;
        for (int i = 0; i < index; i++) {
            TreeNode treeNodes = elements[i];
            if (treeNodes.val.containsKey(firstChar)) {
                treeNode = treeNodes;
                break;
            }
        }
        if (treeNode == null) {
            return null;
        }


        // lastLetter
        char lastLetter = firstChar;
        treeHeight = 1;
        for (int j = 1; j < prefix.length(); j++) {
            treeHeight++;
            lastLetter = prefix.charAt(j);
            if (lastLetter < splitAlpha && treeNode.left != null) {
                treeNode = treeNode.left;
            } else if (lastLetter >= splitAlpha && treeNode.right != null) {
                treeNode = treeNode.right;
            }

            // treeNode没了，但是prefix还未结束,返回最后一个有效treeNode
            // 返回null
            if ((treeNode.left == null && treeNode.right == null &&j != prefix.length() -1)){
                return null;
            }
            // 不包含val
            if(!treeNode.val.containsKey(lastLetter))
                return null;
        }
        return treeNode;
    }

    public static void main(String[] args) {
        MyImplementTriePrefixTree trie = new MyImplementTriePrefixTree();
        // trie.insert("apple");
        // trie.search("apple");   // 返回 True
        // System.out.println(trie.startsWith("apple"));   // 返回 True
        // System.out.println(trie.search("applea"));   // 返回 True
        // System.out.println(trie.startsWith("appled"));   // 返回 True
        // trie.search("app");     // 返回 False
        // trie.startsWith("app"); // 返回 True
        // trie.insert("app");
        // trie.search("app");     // 返回 True
        // =====

        // trie.insert("hello");
        // trie.search("hell");
        // trie.search("helloa");
        // trie.search("hello");
        // trie.startsWith("hell");
        // trie.startsWith("helloa");
        // trie.startsWith("hello");

        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        trie.search("apps");
        trie.search("app");
        trie.search("ad");
        trie.search("applepie");
        trie.search("rest");
        trie.search("jan");
        trie.search("rent");
        trie.search("beer");
        trie.search("jam");
        trie.search("apple");
        trie.startsWith("apple");
        trie.startsWith("apps");
        trie.startsWith("app");
        trie.startsWith("ad");
        trie.startsWith("applepie");
        trie.startsWith("rest");
        trie.startsWith("jan");
        trie.startsWith("rent");
        trie.startsWith("beer");
        trie.startsWith("jam");

    }
}
