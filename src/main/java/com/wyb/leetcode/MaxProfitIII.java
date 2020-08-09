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
        // int[] prices = {1,2,3,4,5};
        int[] prices = {6,1,3,2,4,7};
        System.out.println(maxProfitIII(prices));
        // maxProfitIII(prices);

    }


    private static int maxProfitIII(int[] prices) {

        int i = 0;
        int valley = 0;
        int peak = 0;
        int temp = 0;
        int high = 0;
        int senior = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            System.out.println("valley : " + i);
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            System.out.println("peak : " + i);

            temp = peak - valley;
            high = Math.max(temp, high);
        }

        System.out.println("senior = "+ senior);
        System.out.println("high = "+ high);


        return senior + high;
    }


}
