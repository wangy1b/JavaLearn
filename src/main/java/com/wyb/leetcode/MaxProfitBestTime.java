package com.wyb.leetcode;

/*

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class MaxProfitBestTime {

    public static void main(String[] args) {
        int[] prices = {3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(2, prices));
    }

    private static int maxProfit(int k, int[] prices) {
        if ((prices.length <= 1) || (k <= 1)) return 0;


        // k 超过了上限，也就变成了 无限次交易问题
        if (k > prices.length / 2) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }

        // 状态定义
        int[][] dp = new int[k][2];

        // 初始化
        for (int i = 0; i < k; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        // 遍历每一天，模拟 k 次交易，计算并更新状态


        for (int p : prices) {
            dp[0][0] = Math.max(dp[0][0], 0 - p);                  // 第 1 次买
            dp[0][1] = Math.max(dp[0][1], dp[0][0] + p);           // 第 1 次卖

            for (int i = 1; i < k; i++) {
                // 第 1 次买入的时候，上次的状态： 利润为 0 的时候
                // 第 1 次卖出的时候，上次的状态：第 1 次买入
                // 第 3 次买入的时候，上次的状态：第 2 次卖出
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);   // 第 i 次买
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);       // 第 i 次卖
            }
        }
        return dp[k - 1][1];
    }
}
