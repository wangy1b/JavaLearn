package com.wyb.leetcode;

/*

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmlwi1/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */
public class SearchMatrix {
    public static void main(String[] args) {


        int[][] nums = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                // {10, 13, 14, 17, 24},
                // {18, 21, 23, 26, 30}
        };

        SearchMatrix s = new SearchMatrix();
        boolean res = s.searchMatrix(nums,19);
        System.out.println("res : " + res);
    }
    private boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = Math.max(m,n);
        int s = Math.min(m,n);
         for (int i = 1; i < l; i++) {
                // 正方形内
                if (i < s) {
                    // 斜线值，左下比它小，右上比它大
                    int center = matrix[i][i];
                    if (target <= center) {
                        // center test
                        if (target == center) return true;
                        // row test ,move right
                        if (target == matrix[i - 1][i]) return true;
                        // column test,move up
                        if (target == matrix[i][i - 1]) return true;
                    }

                } else {
                    // 多出来的长方形
                    if (i >= n ) {
                        // column test
                        for (int k = 0; k < l; k++) {
                            if (target == matrix[k][i]) return true;
                        }
                    }
                    // m > n
                    else if ( i >= m ) {
                        for (int j = 0; j < 0; j++) {
                            if (target == matrix[m-1][j]) return true;
                        }

                    }
                }
        }

        return  false;

    }
}
