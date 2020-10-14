package com.wyb.leetcode;

/*

62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？



例如，上图是一个7 x 3 的网格。有多少可能的路径？



示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28


提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 10 ^ 9

https://leetcode-cn.com/problems/unique-paths/

*/

import java.util.LinkedList;

public class UniquePaths {
    public static void main(String[] args) {

        int m = 23, n = 12;
        int res = unquePahts1(m, n);
        System.out.println("res = " + res);
    }

    static int cnt = 0;

    private static int uniquePaths(int m, int n) {

        // LinkedList paths = new LinkedList();
        // LinkedList path = new LinkedList();
        // helper(m, n, 0, 0, path, paths);
        //
        // // 4. return paths
        // // for (Object s : paths) {
        // //     System.out.println(s);
        // // }
        // return paths.size();
        helper(m, n, 0, 0);
        return cnt;
    }

    private static int unquePahts1(int m, int n){
        // 我们令 dp[i][j] 是到达 i, j 最多路径
        // 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1


        // dp思路:到达右下角的路径数 = 到达右下角上面一格的路径数 + 到达右下角左边一格的路径数

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对于边界都只有一条路
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private static void helper(int m, int n, int x, int y, LinkedList path, LinkedList paths) {
        String tmp = String.format("(%s,%s)", x, y);
        // out of the range
        if (x >= n || x < 0 || y >= m || y < 0) {
            // path_string.delete(0,path_string.length());
            return;
        }
        // final position
        else if (x == n - 1 && y == m - 1) {
            path.add(tmp);
            paths.add(path.toString());
            // path_string.delete(0,path_string.length());
            return;
        } else {
            path.add(tmp);
        }

        // move to right or down
        int[][] direcs = {{0, 1}, {1, 0}};
        for (int[] direc : direcs) {
            int newx = x + direc[0];
            int newy = y + direc[1];

            if (newx >= n || newx < 0 || newy >= m || newy < 0) {
                continue;
            }
            helper(m, n, newx, newy, path, paths);
            // remove the last add string from the path ,for next direc to go
            path.removeLast();
        }

    }


    // recursive out of time
    private static void helper(int m, int n, int x, int y) {
        // out of the range
        if (x >= n || x < 0 || y >= m || y < 0) {
            return;
        }
        // final position
        else if (x == n - 1 && y == m - 1) {
            cnt++;
            return;
        }
        // move to right or down
        int[][] direcs = {{0, 1}, {1, 0}};
        for (int[] direc : direcs) {
            int newx = x + direc[0];
            int newy = y + direc[1];
            if (newx >= n || newx < 0 || newy >= m || newy < 0) {
                continue;
            }
            helper(m, n, newx, newy);
        }

    }




}
