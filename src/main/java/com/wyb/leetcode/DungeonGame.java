package com.wyb.leetcode;

import java.util.Arrays;

/*

174. 地下城游戏
一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。

骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。

为了尽快到达公主，骑士决定每次只向右或向下移动一步。



编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。

例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)


说明:

骑士的健康点数没有上限。

任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。

https://leetcode-cn.com/problems/dungeon-game/
 */
public class DungeonGame {
    public static void main(String[] args) {
        int[][] test = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},
        };
        // int res = calculateMinimumHP(test);
        int res = calculateMinimumHPOfficial(test);
        System.out.println("res : " + res);

    }


    // TODO: 2020/10/16 not finished
    private static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        int[][] dp1 = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 第一格肯定为1
                if (i == 0 && j == 0) {
                    dp[i][j] = dungeon[i][j];
                    dp1[i][j] = dungeon[i][j];
                }
                // 第一列 都是从上方推过来的
                else if (i == 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i][j - 1],dp[i][j - 1] + dungeon[i][j]);
                    dp1[i][j] =dp[i][j - 1] + dungeon[i][j];
                }
                // 第一行 都是从左边推过来的
                else if (j == 0 && i > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j],dp[i - 1][j] + dungeon[i][j]);
                    dp1[i][j] = dp[i - 1][j] + dungeon[i][j];
                }
                // 中间的都是 if (i > 0 && j > 0 ) 从左边或者上边一起推过来的,要从中选择最小的
                else {
                    // 从出发点到当前点所需的最小初始值
                    dp[i][j] = Math.max(Math.min(dp[i - 1][j],dp[i - 1][j]+ dungeon[i][j])
                            ,Math.min(dp[i][j - 1], dp[i][j - 1] + dungeon[i][j]));
                    // 从出发点到当前点的路径和
                    dp1[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]) + dungeon[i][j];
                }
            }
        }
        return dp[m - 1][n - 1] + dp1[m - 1][n - 1] ;
    }

    /*

    因此，如果按照从左上往右下的顺序进行动态规划，我们无法直接确定到达 (1,2)的方案，
    因为有两个重要程度相同的参数同时影响后续的决策。也就是说，这样的动态规划是不满足「无后效性」的。

    于是我们考虑从右下往左上进行动态规划。令dp[i][j] 表示从坐标(i,j) 到终点所需的最小初始值。
    换句话说，当我们到达坐标(i,j) 时，如果此时我们的路径和不小于dp[i][j]，我们就能到达终点。

    这样一来，我们就无需担心路径和的问题，只需要关注最小初始值。
    对于dp[i][j]，我们只要关心dp[i][j+1] 和dp[i+1][j] 的最小值minn。
    记当前格子的值为dungeon(i,j)，那么在坐标(i,j) 的初始值只要达到minn−dungeon(i,j) 即可。
    同时，初始值还必须大于等于 11。
    这样我们就可以得到状态转移方程：
        dp[i][j] = max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
    最终答案即为dp[0][0]。

    边界条件为，当i=n−1 或者j=m−1 时，dp[i][j] 转移需要用到的dp[i][j+1] 和dp[i+1][j] 中有无效值
    ，因此代码实现中给无效值赋值为极大值。
    特别地，dp[n−1][m−1] 转移需要用到的 dp[n−1][m] 和 dp[n][m−1] 均为无效值，因此我们给这两个值赋值为1。

    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/dungeon-game/solution/di-xia-cheng-you-xi-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    private static int calculateMinimumHPOfficial(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                // 左/上取小的那个
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }


}
