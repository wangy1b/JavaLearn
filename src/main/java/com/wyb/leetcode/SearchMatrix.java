package com.wyb.leetcode;

/*

240. 搜索二维矩阵 II
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/

 */
public class SearchMatrix {
    public static void main(String[] args) {


        // int[][] nums = {
        //         {1,   4,  7, 11, 15},
        //         {2,   5,  8, 12, 19},
        //         {3,   6,  9, 16, 22},
        //         {10, 13, 14, 17, 24},
        //         {18, 21, 23, 26, 30},
        // };


        // int[][] nums = {{19}};
        int[][] nums = {{1,2,3,4,5},
                        {6,7,8,9,10},
                        {11,12,13,14,15},
                        {16,17,18,19,20},
                        {21,22,23,24,25}};

        SearchMatrix s = new SearchMatrix();
        boolean res = s.searchMatrix(nums,15);
        System.out.println("res : " + res);
    }
    private boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            // 如果当前指向的值大于目标值，则可以 “向上” 移动一行。
            // 否则，如果当前指向的值小于目标值，则可以向右移动一列
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;

    }
}
