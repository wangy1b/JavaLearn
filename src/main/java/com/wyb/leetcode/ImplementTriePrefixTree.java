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

import java.util.HashMap;

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
        String[] words = {"oath","pea","eat","rain"};
        Trie root = new Trie();

        // build Trie
        buildTrie(words,root);

        System.out.println("aa");
    }

    private static void buildTrie(String[] words,Trie root){
        for(int i = 0;i<words.length;i++) {
            Trie node = root;
            String wd = words[i];
            for(int j = 0;j<wd.length();j++){
                // not exist,then add
                char alph = wd.charAt(j);
                if(!node.children.containsKey(alph)) {
                    Trie t = new Trie();
                    node.children.put(alph,t);
                }
                node = node.children.get(alph);
            }
            node.word = wd;
        }

    }

}
