package com.wyb.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*

973. 最接近原点的 K 个点
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。



示例 1：

输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释：
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
示例 2：

输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）


提示：

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

https://leetcode-cn.com/problems/k-closest-points-to-origin/

 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        // int[][] points = {{3,3},{5,-1},{-2,4}};
        // int[][] points = {{-5, 4}, {-6, -5}, {4, 6}};
        // int[][] points = {{1, 0}, {0, 1}};
        // int k = 2;
        int[][] points = {{1,3},{2,-2}};
        int k = 1;
        int[][] res = kClosestOfficial(points, k);
        for (int i = 0; i < res.length; i++) {
            System.out.print("(");
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + ",");
            }
            System.out.println(")");
        }
    }


    private static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Double> heap = new PriorityQueue<Double>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        HashMap<int[],Double> map = new HashMap<>();
        for (int[] arr : points) {
            Double distance = (Math.pow((double) arr[0], 2) + Math.pow((double) arr[1], 2));

            map.put(arr,distance);
            heap.add(distance);
            if (heap.size() > K) {
                heap.poll();
            }
        }

        int[][] newtmp = new int[K][2];
        for (int i = 0; i < K; i++) {
            for (int[] ints : map.keySet()) {
                if (heap.poll() == map.get(ints)) {
                    newtmp[i] = ints;
                    map.remove(ints);
                    break;
                }
            }
        }


        return newtmp;
    }


    private static int[][] kClosestOfficial(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }

    private static int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }


}

