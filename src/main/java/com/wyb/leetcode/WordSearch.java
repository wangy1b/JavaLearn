package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.List;

/*

79. 单词搜索
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false


提示：

board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3

https://leetcode-cn.com/problems/word-search/

 */
public class WordSearch {
    static int[] pos = {0, 0};
    static List posArr = new ArrayList<int[]>();

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));

    }

    // TODO: 2020/9/29 not finished
    private static boolean exist(char[][] board, String word) {
        int len = word.length();
        if (len == 0) {
            return false;
        }

        for (int j = 0; j < board.length; j++) {
            for (int k = 0; k < board[j].length; k++) {

                // 遍历字符串
                for (int i = 0; i < len - 1; i++) {
                    char cw = word.charAt(i);
                    char nw = word.charAt(i + 1);
                    //
                    pos[0] = j;
                    pos[1] = k;
                    char str = board[j][k];
                    if (str == cw) {
                        // 当前二维网格的位置周围是否存在单个单词nw
                        boolean tmp = helper(board, nw, pos);
                        if (!tmp) break;
                    }
                }
            }


        }
        return posArr.size() == word.length();

    }

    // 当前二维网格board的位置idx周围是否存在cw单个单词
    private static boolean helper(char[][] board, char wd, int[] idx) {
        boolean res = false;
        int i = idx[0];
        int j = idx[1];
        //上下横移一步
        for (int k = 1; k <= 2; k++) {
            int newi = i + (int) Math.pow(-1, k);
            if ((newi >= 0 && newi < board.length)
                    && (board[newi][j] == wd)) {
                res = true;
                pos[0] = newi;
                pos[1] = j;
                posArr.add(res);
            }
        }
        //左右横移一步
        for (int l = 1; l <= 2; l++) {
            int newj = j + (int) Math.pow(-1, l);
            if ((newj >= 0 && newj < board[i].length)
                    && (board[i][newj] == wd)) {
                res = true;
                pos[0] = i;
                pos[1] = newj;
                posArr.add(res);
            }
        }
        return res;
    }
}
