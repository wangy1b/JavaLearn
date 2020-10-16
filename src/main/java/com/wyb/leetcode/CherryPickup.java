package com.wyb.leetcode;

import java.util.Arrays;

/*

741. 摘樱桃
一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：

0 表示这个格子是空的，所以你可以穿过它。
1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
-1 表示这个格子里有荆棘，挡着你的路。
你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：

从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
示例 1:

输入: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
输出: 5
解释：
玩家从（0,0）点出发，经过了向下走，向下走，向右走，向右走，到达了点(2, 2)。
在这趟单程中，总共摘到了4颗樱桃，矩阵变成了[[0,1,-1],[0,0,-1],[0,0,0]]。
接着，这名玩家向左走，向上走，向上走，向左走，返回了起始点，又摘到了1颗樱桃。
在旅程中，总共摘到了5颗樱桃，这是可以摘到的最大值了。
说明:

grid 是一个 N * N 的二维数组，N的取值范围是1 <= N <= 50。
每一个 grid[i][j] 都是集合 {-1, 0, 1}其中的一个数。
可以保证起点 grid[0][0] 和终点 grid[N-1][N-1] 的值都不会是 -1。

https://leetcode-cn.com/problems/cherry-pickup/

 */
public class CherryPickup {
    public static void main(String[] args) {

        // int[][] grid = {{0, 1, -1},
        //                 {1, 0, -1},
        //                 {1, 1, 1}};

        // int[][] grid = {{1, 1, -1},
        //                 {1, -1, 1},
        //                 {-1, 1, 1}};

        int[][] grid = {{1,1,1,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,1},
                        {1,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,1,1,1}};

        int res = cherryPickupOfficial1(grid);
        // int res = cherryPickupOfficial2(grid);
        System.out.println("res : " + res);

    }


    /*

    方法二：动态规划（自顶向下）[Accepted]

    与其从左上角走到右下角，再从右下角走到左上角；不如直接考虑从左上角选两条路走到右下角。
    在走了 t 步之后，我们可能处于的位置 (r, c) 满足 r+c=t，
    所以如果我们在位置 (r1, c1) 和 (r2, c2) 有两个人，那么 r2=r1+c1-c2。
    这意味着 r1，c1，c2 唯一地决定了两个走了 t 步数的人。这个条件让我们能够很好地进行动态规划。

    算法：

    定义 dp[r1][c1][c2] 是两个人从 (r1, c1) 和 (r2, c2) 开始，
    朝着 (N-1, N-1) 所能摘到最多的樱桃数量，其中 r2=r1+c1-c2。

    如果 grid[r1][c1] 和 grid[r2][c2] 不是荆棘，那么 dp[r1][c1][c2] 的值是 (grid[r1][c1] + grid[r2][c2])
    ，加上 dp[r1+1][c1][c2]，dp[r1][c1+1][c2]，dp[r1+1][c1][c2+1]，dp[r1][c1+1][c2+1] 的最大值。
    在 (r1, c1) == (r2, c2) 的情况下，我们要避免重复计数。
    为什么要加上 dp[r+1][c1][c2]，dp[r1][c1+1][c2]，dp[r1+1][c1][c2+1]，dp[r1][c1+1][c2+1]的最大值？
    它对应 1 号和人 2 号向下或向右移动的 4 种可能性：
    1 号向下和 2 号向下：dp[r1+1][c1][c2]
    1 号向右和 2 号向下：dp[r1][c1+1][c2]
    1 号向下和 2 号向右：dp[r1+1][c1][c2+1]
    1 号向右和 2 号向右：dp[r1][c1+1][c2+1]

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/cherry-pickup/solution/zhai-ying-tao-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    static int[][][] memo;
    static int[][] grid;
    static int N;
    private static int cherryPickupOfficial1(int[][] grid1) {
        grid = grid1;
        N = grid.length;
        memo = new int[N][N][N];
        for (int[][] layer: memo)
            for (int[] row: layer)
                Arrays.fill(row, Integer.MIN_VALUE);
        return Math.max(0, dp(0, 0, 0));
    }

    private static int dp(int r1, int c1, int c2) {
        int r2 = r1 + c1 - c2;
        if (N == r1 || N == r2 || N == c1 || N == c2 ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return -999999;
        } else if (r1 == N-1 && c1 == N-1) {
            return grid[r1][c1];
        } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
            return memo[r1][c1][c2];
        } else {
            int ans = grid[r1][c1];
            if (c1 != c2) ans += grid[r2][c2];
            ans += Math.max(Math.max(dp(r1, c1+1, c2+1), dp(r1+1, c1, c2+1)),
                    Math.max(dp(r1, c1+1, c2), dp(r1+1, c1, c2)));
            memo[r1][c1][c2] = ans;
            return ans;
        }
    }


    /*
    方法三：动态规划（自底向上）[Accepted]

    假设 r1+c1=t 是第 t 层。因为递归只能引用下一层，所以我们一次需要在内存中保留两层。

    算法：
    在第 t 步，dp[c1][c2] 为两个人从 (0, 0) 到 (r1, c1) 和 (0, 0) 到 (r2, c2) 能摘到最多樱桃的数量，
    其中 r1 = t-c1, r2 = t-c2。我们的动态规划类似于方法 2。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/cherry-pickup/solution/zhai-ying-tao-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public int cherryPickupOfficial2(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];

        for (int t = 1; t <= 2*N - 2; ++t) {
            int[][] dp2 = new int[N][N];
            for (int[] row: dp2) Arrays.fill(row, Integer.MIN_VALUE);

            for (int i = Math.max(0, t-(N-1)); i <= Math.min(N-1, t); ++i) {
                for (int j = Math.max(0, t-(N-1)); j <= Math.min(N-1, t); ++j) {
                    if (grid[i][t-i] == -1 || grid[j][t-j] == -1) continue;
                    int val = grid[i][t-i];
                    if (i != j) val += grid[j][t-j];
                    for (int pi = i-1; pi <= i; ++pi)
                        for (int pj = j-1; pj <= j; ++pj)
                            if (pi >= 0 && pj >= 0)
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[N-1][N-1]);
    }



    private static int maxPathSumSingleWay(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                // 第一格肯定为1
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    // grid[i][j] = 0;
                }
                // 第一列 都是从上方推过来的
                else if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                // 第一行 都是从左边推过来的
                else if (j == 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
                // 中间的都是 if (i > 0 && j > 0 ) 从左边或者上边一起推过来的,要从中选择最小的
                else {
                    // dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];

                    int tmp =  Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if ( tmp == 0) {
                        dp[i][j] =0;
                    }
                    else dp[i][j] = tmp + grid[i][j];
                }
            }
        }

        int lastVal = dp[m - 1][n - 1];
        if (lastVal == 0) return lastVal;
        int lastX = m - 1;
        int lastY = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m -1 && j == n -1){
                    grid[i][j] = 0;
                    continue;
                }
                int currVal = dp[i][j];
                if ((lastVal - currVal == 1) && (lastX == i || lastY == j)) {
                    lastVal = currVal;
                    lastX = i;
                    lastY = j;
                    grid[i][j] = 0;
                }

            }

        }
        return dp[m - 1][n - 1];
    }
}
