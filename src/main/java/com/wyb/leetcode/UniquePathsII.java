package com.wyb.leetcode;

/*

63. 不同路径 II
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:

输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

https://leetcode-cn.com/problems/unique-paths-ii/

 */
public class UniquePathsII {
    public static void main(String[] args) {

        // int[][] test = {
        //         {0, 0, 0},
        //         {0, 1, 0},
        //         {0, 0, 0}
        // };

        // int[][] test = {{0, 0},{1, 1},{0, 0}};
        int[][] test = {{0, 0}};
        int res = uniquePathsWithObstacles(test);
        System.out.println("res:" + res);
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // 第一格就是障碍直接退出
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // when catch obstacle
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                // 最上一行和最左一列都应该为1，因为只有1条路

                // 第一格肯定为1
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }
                // 第一列 都是从上方推过来的
                else if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j-1];
                }
                // 第一行 都是从左边推过来的
                else if (j == 0 && i > 0) {
                    dp[i][j] = dp[i-1][j];
                }
                // 中间的都是 if (i > 0 && j > 0 ) 从左边或者上边一起推过来的
                else  {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
