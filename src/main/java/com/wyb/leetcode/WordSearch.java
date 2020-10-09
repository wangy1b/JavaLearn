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
        // System.out.println(exist(board, word));
        System.out.println(existOfficial(board, word));
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
        // TODO: 2020/10/8 如果上下移动一步的单词是一样的，该怎么处理？
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if ((newi >= 0 && newi < board.length && newj >= 0 && newj < board[i].length)
                    && (board[newi][j] == wd)) {
                res = true;
                pos[0] = newi;
                pos[1] = newj;
                return res;
            }
        }
        // 啰嗦
        // //上下横移一步
        // for (int k = 1; k <= 2; k++) {
        //     int newi = i + (int) Math.pow(-1, k);
        //     if ((newi >= 0 && newi < board.length)
        //             && (board[newi][j] == wd)) {
        //         res = true;
        //         pos[0] = newi;
        //         pos[1] = j;
        //         if (res) return res;
        //     }
        // }
        // //左右横移一步
        // for (int l = 1; l <= 2; l++) {
        //     int newj = j + (int) Math.pow(-1, l);
        //     if ((newj >= 0 && newj < board[i].length)
        //             && (board[i][newj] == wd)) {
        //         res = true;
        //         pos[0] = i;
        //         pos[1] = newj;
        //         if (res) return res;
        //     }
        // }
        return res;
    }

/*

方法一：深度优先搜索
思路与算法

设函数 check(i,j,k) 判断以网格的 (i,j) 位置出发，能否搜索到单词 word[k..]，其中 word[k..] 表示字符串 word 从第 k 个字符开始的后缀子串。
如果能搜索到，则返回 true，反之返回 false。函数 check(i,j,k) 的执行步骤如下：

如果 oard[i][j] !=s[k]，当前字符不匹配，直接返回 false。
如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回true。
否则，遍历当前位置的所有相邻位置。如果从某个相邻位置出发，能够搜索到子串word[k+1..]，则返回true，否则返回false。
这样，我们对每一个位置 (i,j) 都调用函数 check(i,j,0) 进行检查：只要有一处返回 true，就说明网格中能够找到相应的单词，否则说明不能找到。

为了防止重复遍历相同的位置，需要额外维护一个与board 等大的visited 数组，用于标识每个位置是否被访问过。每次遍历相邻位置时，需要跳过已经被访问的位置。

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/word-search/solution/dan-ci-sou-suo-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */


    private static boolean existOfficial(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        // 为了防止重复遍历相同的位置，需要额外维护一个与board 等大的visited 数组，用于标识每个位置是否被访问过。
        // 每次遍历相邻位置时，需要跳过已经被访问的位置
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        // 递归函数界点
        // 当board中位置的字母和字符串中选取的不同时，返回false
        if (board[i][j] != s.charAt(k)) {
            return false;
        // 当访问到字符串最后的时候，返回true
        } else if (k == s.length() - 1) {
            return true;
        }
        // 访问的时候将visited对应位置设置为true，表示已访问
        visited[i][j] = true;
        // 四个方向用数组增加值表示
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            // 移动后的位置在board范围内
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                // 如果该位置没有访问才继续
                if (!visited[newi][newj]) {
                    // 递归，将newi,newj继续传入check函数。字符串的选取位置+1
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        // ?什么情况需要将已经访问重置?
        // 当前visited[i][j]及其周围的字母和字符串不匹配的时候，改位置要重置为false（未访问）
        visited[i][j] = false;
        return result;
    }



}
