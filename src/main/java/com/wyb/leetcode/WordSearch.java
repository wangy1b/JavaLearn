package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
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
    static LinkedList<int[]> posArr = new LinkedList<int[]>();

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "SEE";
        System.out.println(exist(board, word));
        System.out.println("check result:");
        for (int i = 0; i < posArr.size(); i++) {
            int x = posArr.get(i)[0];
            int y = posArr.get(i)[1];
            System.out.println("index: (" + x +","+ y + ") value: " + board[x][y]);
        }

    }

    private static boolean exist(char[][] board, String word) {
        boolean res = false;
        int len = word.length();
        if (len == 0) {
            return false;
        }

        for (int j = 0; j < board.length; j++) {
            for (int k = 0; k < board[j].length; k++) {
                //
                pos[0] = j;
                pos[1] = k;
                posArr.clear();
                System.out.println("start at ("+ pos[0] + ","+ pos[1]+")");
                // 遍历字符串
                for (int i = 0; i < len - 1; i++) {
                    char cw = word.charAt(i);
                    char nw = word.charAt(i + 1);

                    char str = board[pos[0]][pos[1]];
                    if (str == cw) {
                        // 当前二维网格的位置周围是否存在单个单词nw
                        boolean tmp = helper(board, nw, pos);
                        if (!tmp) break;
                        posArr.add(new int[]{pos[0],pos[1]});
                    }

                    if ( posArr.size() == word.length()-1) {
                        posArr.addFirst(new int[]{j,k});
                        return true;
                    }
                }
            }
        }
        return res;
    }

    // 当前二维网格board的位置idx周围是否存在cw单个单词
    private static boolean helper(char[][] board, char wd, int[] idx) {
        boolean res = false;
        int i = idx[0];
        int j = idx[1];
        //上下横移一步
        // TODO: 2020/10/8 如果上下移动一步的单词是一样的，该怎么处理？
        for (int k = 1; k <= 2; k++) {
            int newi = i + (int) Math.pow(-1, k);
            if ((newi >= 0 && newi < board.length)
                    && (board[newi][j] == wd)) {
                res = true;
                pos[0] = newi;
                pos[1] = j;
                if (res) return res;
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
                if (res) return res;
            }
        }
        return res;
    }
}
