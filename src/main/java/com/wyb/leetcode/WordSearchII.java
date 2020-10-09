package com.wyb.leetcode;

import com.sun.org.apache.xml.internal.utils.Trie;

import java.util.ArrayList;
import java.util.List;

/*

212. 单词搜索 II
给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

示例:

输入:
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

输出: ["eat","oath"]
说明:
你可以假设所有输入都由小写字母 a-z 组成。

提示:

你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
什么样的数据结构可以有效地执行这样的操作？
散列表是否可行？为什么？
前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。

 */
public class WordSearchII {
    public static void main(String[] args) {
        String[] words = {"oath","pea","eat","rain"};
        char[][] board = {
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'},
                };

        List<String> list =  findWords(board,words);
        System.out.println(list);

    }
    private static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            boolean tmp = exist(board,word);
            if (tmp) res.add(word);
        }

        return res;
    }


    private static boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for(int i = 0;i < h;i++) {
            for(int j = 0;j < w; j++){
                boolean tmp = helper(board,visited,i,j,word,0);
                if (tmp) return true;
            }
        }
        return false;
    }

    private static boolean helper(char[][] board, boolean[][] visited, int i, int j, String word, int idx){
        if(board[i][j] != word.charAt(idx)){
            return false;
        } else if ( idx == word.length() - 1){
            return true;
        }
        boolean res = false;
        visited[i][j] = true;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] direc: directions) {
            int newi = i + direc[0], newj = j + direc[1];
            if(newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if(!visited[newi][newj]) {
                    boolean tmp = helper(board,visited,newi,newj,word,idx+1);
                    if(tmp){
                        res = true;
                        break;
                    }
                }
            }
        }


        visited[i][j] = false;
        return res;

    }
}
