package com.wyb.leetcode;

public class MaxProfitII {
    public static void main(String[] args) {
        // int[] prices = {7, 1, 5, 3, 6, 4};
        // int[] prices = {7,6,4,3,1};
        // int[] prices = {2,4,1};
        // int[] prices = {1,2,3,4,5};
        // int[] prices = {7, 6, 4, 3, 1};
        // int[] prices = {6, 1, 3, 2, 4, 7};
        // int[] prices = {3, 2, 6, 5, 0, 3};
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfitII(prices));
    }


    /**
     * todo 剩下的部分step需要灵活变动
     */
    private static int maxProfitII(int[] prices) {
        int maxprofit = 0;
        // for (int idx = 0; idx < prices.length; idx++) {
        int idx = 1;
        for (int step = 1; step <= prices.length - idx - 1; step++) {
            maxprofit = Math.max(maxprofit, findCurrentMaxProfit(prices, idx, step));
            // System.out.println("idx=" + idx + " step=" + step + " maxprofit=" + findCurrentMaxProfit(prices, idx, step));
        }
        // System.out.println("idx = " + idx +" maxprofit = " + findCurrentMaxProfit(prices,1, 3));
        // }
        // System.out.println("step = 3 :" + findCurrentMaxProfit(prices,1, 3));
        // System.out.println("maxprofit :" + maxprofit);
        return maxprofit;
    }


    private static int findCurrentMaxProfit(int[] prices, int idx, int step) {
        if (idx + step > prices.length) {
            return 0;
        }
        System.out.println(String.format("start at %s step %s", idx, step));
        for (int end = idx + step; end < prices.length; end++) {
            if (prices[end] > prices[idx]) {
                int temp_max = 0;
                for (int j = 1; j < prices.length - idx + step; j++) {
                    // temp_max = Math.max(temp_max, findCurrentMaxProfit(prices, end + j + 1, j));
                    temp_max = Math.max(temp_max, findCurrentMaxProfit(prices, end + j, j));
                }
                return prices[end] - prices[idx] + temp_max;
            }
        }

        return 0;
    }
}
