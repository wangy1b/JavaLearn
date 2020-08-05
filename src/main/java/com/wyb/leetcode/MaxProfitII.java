package com.wyb.leetcode;

public class MaxProfitII {
    public static void main(String[] args) {
        // int[] prices = {7, 1, 5, 3, 6, 4};
        // int[] prices = {7,6,4,3,1};
        // int[] prices = {2,4,1};
        // int[] prices = {1,2,3,4,5};
        // int[] prices = {7, 6, 4, 3, 1};
        int[] prices = {6, 1, 3, 2, 4, 7};
        System.out.println(maxProfitII(prices));
    }


    /**
     *  todo 剩下的部分step需要灵活变动
     *
     */
    private static int maxProfitII(int[] prices) {
        int maxprofit = 0;
        // for (int idx = 0; idx < prices.length; idx++) {
            int idx = 1;
            for (int step = 1; step <= prices.length - idx - 1; step++) {
                maxprofit = Math.max(maxprofit, findCurrentMaxProfit(prices, idx, step));
                System.out.println("idx=" + idx + " step=" + step + " maxprofit=" + findCurrentMaxProfit(prices, idx, step));
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
        for (int i = idx + step; i < prices.length; i++) {
            if (prices[i] > prices[idx]) {
                return prices[i] - prices[idx] + findCurrentMaxProfit(prices, i + step, step);
            }
        }

        return 0;
    }
}
