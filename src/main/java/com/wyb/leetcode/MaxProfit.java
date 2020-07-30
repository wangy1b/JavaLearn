package com.wyb.leetcode;

public class MaxProfit {
    public static void main(String[] args) {
        // int[] prices = {7,1,5,3,6,4};
        // int[] prices = {7,6,4,3,1};
        int[] prices = {2,4,1};
        System.out.println(maxProfit(prices));
    }


    /** 不会
     * @param prices
     * @return
     */
    private static int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
