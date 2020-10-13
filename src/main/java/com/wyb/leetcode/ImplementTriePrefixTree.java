package com.wyb.leetcode;

/*

208. 实现 Trie (前缀树)
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Trie{
    public HashMap<Character,Trie> children = new HashMap<Character,Trie>();
    public String word;
    public Trie(){}
}

public class ImplementTriePrefixTree {



    // /** Initialize your data structure here. */
    // public ImplementTriePrefixTree() {
    //
    // }
    //
    // /** Inserts a word into the trie. */
    // public void insert(String word) {
    //
    // }
    //
    // /** Returns if the word is in the trie. */
    // public boolean search(String word) {
    //     return false;
    // }
    //
    // /** Returns if there is any word in the trie that starts with the given prefix. */
    // public boolean startsWith(String prefix) {
    //     return false;
    // }
    //
    // /**
    //  * Your Trie object will be instantiated and called as such:
    //  * Trie obj = new Trie();
    //  * obj.insert(word);
    //  * boolean param_2 = obj.search(word);
    //  * boolean param_3 = obj.startsWith(prefix);
    //  */


    public static void main(String[] args) {
        // String[] words = {"oath","pea","eat","rain"};
        String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        Arrays.sort(words);
        Trie root = new Trie();
        // build Trie
        buildTrie(words,root);

        // char[][] board = {
        //         {'o', 'a', 'a', 'n'},
        //         {'e', 't', 'a', 'e'},
        //         {'i', 'h', 'k', 'r'},
        //         {'i', 'f', 'l', 'v'},
        // };

        char[][] board = {
                {'a','b'},
                {'a','a'},
        };

        List<String> res = new ArrayList<String>();

        boolean[][] visited = new boolean[board.length][board[0].length];
        //serch word from Trie
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++) {
                boolean tmp = helper(board, visited, i, j, root,res);
                if (!tmp) continue;
            }
        }
        System.out.println(res);
        // [aba, aaa, baa, aaba, aaab]
        // ["aaa","aaab","aaba","aba","baa"]

    }

    private static void buildTrie(String[] words,Trie root){
        for(int i = 0;i<words.length;i++) {
            Trie node = root;
            // Trie preNode = root;
            String wd = words[i];
            for(int j = 0;j<wd.length();j++){
                // not exist,then add
                char alph = wd.charAt(j);
                if(!node.children.containsKey(alph)) {
                    Trie t = new Trie();
                    node.children.put(alph,t);
                }
                // preNode = node;
                node = node.children.get(alph);
            }
            // preNode.word = wd;
            node.word = wd;
        }

    }

    private static boolean helper(char[][] board, boolean[][] visited, int i, int j, Trie root,List<String> reslist) {
        char alph = board[i][j];
        Trie node = root.children.get(alph);
        if (!root.children.containsKey(alph)) {
            return false;
        }
        else if (node.word != null) {
            reslist.add(node.word);
            // 成功找到后，就把当前节点移除
            if (node.children.isEmpty()) {
                root.children.remove(alph);
            }
            node.word = null;
            return true;
        }
        boolean res = false;
        visited[i][j] = true;
        // up down left right
        int[][] direcs = {{-1,0},{0,1},{1,0},{0,-1}};
        for(int[] direc:direcs) {
            int newi = i + direc[0];
            int newj = j + direc[1];
            if(newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if(!visited[newi][newj]) {
                    boolean tmp = helper(board,visited,newi,newj,node,reslist);
                    if(tmp) {
                        res = true;
                        // break;
                    }
                }
            }
        }

        // if (node.children.isEmpty()) {
        //     root.children.remove(alph);
        // }
        visited[i][j] = false;
        return res;
    }

}
