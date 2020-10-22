package com.wyb.leetcode;

import java.util.*;

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
        // int[][] nums = {{1,1},{2,2},{3,3}};
        int[][] nums = {{1,1},{2,3},{1,1}};
        // int[][] nums = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int res = maxPoints(nums);
        System.out.println("res : " + res);
    }

    private static int maxPoints(int[][] points) {
        int res = 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] == o2[0] && o1[1] == o2[1])?0:-1;
            }
        });
        for (int i = 0; i < points.length; i++) {
                res = Math.max(res,help(points,i));
        }
        return res;
    }

    private static int help(int[][] points,int c){
        int res = 0;
        HashMap<Double,Integer> rateCnt = new HashMap<Double,Integer>();
        for (int i = c+1; i < points.length; i++) {
            double newrate = slope(points[i],points[c]);
            rateCnt.put(newrate,rateCnt.getOrDefault(newrate,0)+1);

        }
        for (Integer integer : rateCnt.values()) {
            res = Math.max(res,integer);
        }
        return res+1;
    }

    private static double slope(int[] a,int[] b){
        return (b[0]-a[0]) == 0 ? 1:(b[1]-a[1])/(b[0]-a[0]);
    }
}
