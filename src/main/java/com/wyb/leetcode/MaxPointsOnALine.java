package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*

149. 直线上最多的点数
给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

示例 1:

输入: [[1,1],[2,2],[3,3]]
输出: 3
解释:
^
|
|        o
|     o
|  o
+------------->
0  1  2  3  4
示例 2:

输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出: 4
解释:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6

https://leetcode-cn.com/problems/max-points-on-a-line/

 */
public class MaxPointsOnALine {
    public static void main(String[] args) {
        int[][] nums = {{1,1},{2,2},{3,3}};
        // int[][] nums = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int res = maxPoints(nums);
        System.out.println("res : " + res);
    }

    private static int maxPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                res = Math.max(res,help(points,i, j));
            }
        }
        return res;
    }

    private static int help(int[][] points,int x, int y){
        int res = 0;
        HashMap<Double,Integer> rateCnt = new HashMap<Double,Integer>();
        int a = x, b = y;
        for (int i = x; i < points.length; i++) {
            for (int j = y; j < points[i].length; j++) {
                if ( i == x && j == y) continue;
                double newrate = (x-a) == 0 ? 1:(y - b)/(x -  a);
                rateCnt.put(newrate,rateCnt.getOrDefault(newrate,0)+1);
            }
        }

        for (Integer integer : rateCnt.values()) {
            res = Math.max(res,integer);
        }
        return res;
    }
}
