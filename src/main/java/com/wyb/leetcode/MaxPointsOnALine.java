package com.wyb.leetcode;

import javafx.util.Pair;

import java.lang.reflect.Array;
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
        // int[][] nums = {{1,1},{2,2},{3,3},{1,1},{1,1},{1,1},{1,1},{1,1}};
        // int[][] nums = {{1,1},{2,3},{1,1},{2,3},{1,1}};
        // int[][] nums = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int[][] nums = {{0,0}, {94911150,94911151}, {94911151,94911152}};
        MaxPointsOnALine maxPointsOnALine = new MaxPointsOnALine();
        int res = maxPointsOnALine.maxPointsOfficial(nums);
        System.out.println("res : " + res);
    }

    private static int maxPoints(int[][] points) {
        int res = 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int x = o1[0],x1 = o1[1];
                int y = o2[0],y1 = o2[1];

                // (x < y) ? -1 : ((x == y) ? 0 : 1)

                int res = 0;
                if ( x == y ) {
                    res = (x1 < y1) ? -1 : ((x1 == y1) ? 0 : 1);
                } else if ( x != y ) {
                    res = (x < y) ? -1 : ((x == y) ? 0 : 1);
                }

                return res;
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
        int step = 1;
        for (int i = c+1; i < points.length; i++) {
            double newrate = slope(points[i],points[c]);
            // same point with start
            if (points[i][0]  == points[c][0] && points[i][1]  == points[c][1]) {
                step += 1;
                continue;
            }
            // same point with end
            else if (points[i][0]  == points[i-1][0] && points[i][1]  == points[i-1][1]) {
                rateCnt.put(newrate,rateCnt.get(newrate)+1);
            } else {
                rateCnt.put(newrate, rateCnt.getOrDefault(newrate, 0) + step);
            }
        }
        for (Integer integer : rateCnt.values()) {
            res = Math.max(res,integer);
        }
        return res+1;
    }

    private static double slope(int[] a,int[] b){
        return (b[0]-a[0]) == 0 ? 1:(b[1]-a[1])/(b[0]-a[0]);
    }



    int[][] points;
    int n;
    HashMap<String, Integer> lines = new HashMap<String, Integer>();
    int horisontal_lines;

    private Pair<Integer, Integer> add_line(int i, int j, int count, int duplicates) {
    /*
    Add a line passing through i and j points.
    Update max number of points on a line containing point i.
    Update a number of duplicates of i point.
    */
        // rewrite points as coordinates
        int x1 = points[i][0];
        int y1 = points[i][1];
        int x2 = points[j][0];
        int y2 = points[j][1];
        // add a duplicate point
        if ((x1 == x2) && (y1 == y2))
            duplicates++;
            // add a horisontal line : y = const
        else if (y1 == y2) {
            horisontal_lines += 1;
            count = Math.max(horisontal_lines, count);
        }
        // add a line : x = slope * y + c
        // only slope is needed for a hash-map
        // since we always start from the same point
        else {
            double tslope = 1.0 * (x1 - x2) / (y1 - y2) + 0.0;
            String slope = String.valueOf(tslope);
            lines.put(slope, lines.getOrDefault(slope, 1) + 1);
            count = Math.max(lines.get(slope), count);
        }
        return new Pair(count, duplicates);
    }

    private int max_points_on_a_line_containing_point_i(int i) {
    /*
    Compute the max number of points
    for a line containing point i.
    */
        // init lines passing through point i
        lines.clear();
        horisontal_lines = 1;
        // One starts with just one point on a line : point i.
        int count = 1;
        // There is no duplicates of a point i so far.
        int duplicates = 0;

        // Compute lines passing through point i (fixed)
        // and point j (interation).
        // Update in a loop the number of points on a line
        // and the number of duplicates of point i.
        for (int j = i + 1; j < n; j++) {
            Pair<Integer, Integer> p = add_line(i, j, count, duplicates);
            count = p.getKey();
            duplicates = p.getValue();
        }
        return count + duplicates;
    }


    private  int maxPointsOfficial(int[][] points) {

        this.points = points;
        // If the number of points is less than 3
        // they are all on the same line.
        n = points.length;
        if (n < 3)
            return n;

        int max_count = 1;
        // Compute in a loop a max number of points
        // on a line containing point i.
        for (int i = 0; i < n - 1; i++)
            max_count = Math.max(max_points_on_a_line_containing_point_i(i), max_count);
        return max_count;
    }

}
