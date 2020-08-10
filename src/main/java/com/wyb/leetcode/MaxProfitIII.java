package com.wyb.leetcode;

import java.util.*;

/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

*/

public class MaxProfitIII {
    public static void main(String[] args) {

        // int[] prices = {7, 1, 5, 3, 4, 6};
        // int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = {1,2,3,4,5};
        // int[] prices = {6,1,3,2,4,7};
        System.out.println(maxProfitIII(prices));
        // maxProfitIII(prices);

    }


    private static int maxProfitIII(int[] prices) {
        if (prices.length <=1 ) return 0;
        // 持有（昨天持有，今天也持有；昨天不持有，今天买入）
        int fstBuy = Integer.MIN_VALUE ;// 第一次持有不需要带入前面结果
        int secBuy = Integer.MIN_VALUE;
        // 不持有（昨天不持有，今天也不持有；昨天持有，今天卖出）
        int fstSell = 0 ;
        int secSell = 0;
        for(int i = 0; i < prices.length; i++) {
            fstBuy = Math.max(fstBuy, - prices[i]);
            fstSell = Math.max(fstSell, fstBuy + prices[i]);
            secBuy = Math.max(secBuy, fstSell - prices[i]);
            secSell = Math.max(secSell, secBuy + prices[i]);
        }
        return secSell;
    }


}
