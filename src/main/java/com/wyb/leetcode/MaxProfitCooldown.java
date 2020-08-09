package com.wyb.leetcode;

/*

给定一个整数数组，其中第i个元素代表了第i天的股票价格 。​
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

public class MaxProfitCooldown {

    public static void main(String[] args) {
        // int[] prices = {1,2,3,0,2};
        // int[] prices = {6,1,3,2,4,7};
        // System.out.println(maxProfitCooldown(prices));
        // int[] prices = {4,2,1};
        int[] prices = {6,1,6,4,3,0,2};
        System.out.println(myMaxProfit(prices));
        System.out.println(myMaxProfitMoreeffection(prices));
    }



    // f[i][0]: 手上持有股票的最大收益

    // 我们目前持有的这一支股票可以是在第i-1天就已经持有的，对应的状态为 f[i-1][0]；
    // 或者是第i天买入的，那么第i-1天就不能持有股票并且不处于冷冻期中，对应的状态为 f[i-1][2]加上买入股票的负收益 prices[i]
    // f[i][0]=max(f[i−1][0],f[i−1][2]−prices[i])

    // ==================================================================================================================


    // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益

    // 对于 f[i][1]，我们在第 i 天结束之后处于冷冻期的原因是在当天卖出了股票，那么说明在第 i-1天时我们必须持有一支股票，
    // 对应的状态为f[i−1][0] 加上卖出股票的正收益 prices[i]。因此状态转移方程为：
    // f[i][1]=f[i−1][0]+prices[i]

    // ==================================================================================================================


    // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益

    // 对于 f[i][2]，我们在第 i 天结束之后不持有任何股票并且不处于冷冻期，说明当天没有进行任何操作，即第 i-1天时不持有任何股票：
    // 如果处于冷冻期，对应的状态为f[i−1][1]；如果不处于冷冻期，对应的状态为 f[i−1][2]。因此状态转移方程为：
    // f[i][2]=max(f[i−1][1],f[i−1][2])

    // ==================================================================================================================

    // 如果一共有 nn 天，那么最终的答案即为：
    // max(f[n−1][0],f[n−1][1],f[n−1][2])

    // 注意到如果在最后一天（第 n-1天）结束之后，手上仍然持有股票，那么显然是没有任何意义的。
    // 因此更加精确地，最终的答案实际上是 f[n−1][1] 和 f[n−1][2] 中的较大值，即：
    // max(f[n−1][1],f[n−1][2])

    // 细节：
    //
    // 我们可以将第 00 天的情况作为动态规划中的边界条件：
    // f[0][0] = −prices[0]
    // f[0][1] = 0
    // f[0][2] = 0
    //
    // 在第0天时，如果持有股票，那么只能是在第0天买入的，对应负收益−prices[0]；
    // 如果不持有股票，那么收益为零。注意到第0天实际上是不存在处于冷冻期的情况的，
    // 但我们仍然可以将对应的状态f[0][1] 置为零，这其中的原因留给读者进行思考。
    // 这样我们就可以从第 1 天开始，根据上面的状态转移方程进行进行动态规划，直到计算出第 n-1天的结果。

    private static int maxProfitCooldown(int[] prices){
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
    private static int maxProfitCooldownOfficial(int[] prices){
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }

        return Math.max(f1, f2);
    }


    private static int myMaxProfit(int[] prices) {
        if (prices.length <=1 ) return 0;
        int[][] f = new int[prices.length][3];
        //持股
        f[0][0] = -prices[0];
        //不持股
        f[0][1] = 0;
        //冷冻期
        f[0][2] = 0;
        for(int i = 1; i<prices.length; i++) {
            // 持股{昨天持股，今天还持股；昨天冷冻期，今天买了}
            f[i][0] = Math.max(f[i-1][0],f[i-1][2] - prices[i]);
            // 不持股{昨天不持股，今天还不持股；昨天持股，今天卖了}
            f[i][1] = Math.max(f[i-1][1], f[i-1][0] + prices[i]);
            // 冷冻期{昨天不持股，今天进入冷冻期}
            f[i][2] = f[i-1][1];
        }
        return Math.max(f[prices.length-1][1], f[prices.length-1][2]);
    }

    private static int myMaxProfitMoreeffection(int[] prices) {
        if (prices.length <=1 ) return 0;
        int[][] f = new int[prices.length][3];
        //持股
        int f0 = -prices[0];
        //不持股
        int f1 = 0;
        //冷冻期
        int f2 = 0;
        for(int i = 1; i<prices.length; i++) {
            // 持股{昨天持股，今天还持股；昨天冷冻期，今天买了}
            int nf0 = Math.max(f0, f2 - prices[i]);
            // 不持股{昨天不持股，今天还不持股；昨天持股，今天卖了}
            int nf1 = Math.max(f1, f0 + prices[i]);
            // 冷冻期{昨天不持股，今天进入冷冻期}
            int nf2 = f1;
            f0 = nf0;
            f1 = nf1;
            f2 = nf2;
        }
        return Math.max(f1, f2);
    }
}
