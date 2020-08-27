package com.wyb.leetcode;

/*

42. 接雨水
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6

 */
public class TrappingRainWater {
    public static void main(String[] args) {
        // int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] nums = {4, 2, 3};
        System.out.println(trap(nums));
    }



    /*

    找到数组中从下标 i 到最左端最高的条形块高度 left_max。
    找到数组中从下标 i 到最右端最高的条形块高度 right_max。
    扫描数组 height 并更新答案：
    累加 min(max_left[i],max_right[i])−height[i] 到 ans 上

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    private static int trap0(int[] height) {
        if(height == null) return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size], right_max = new int[size];
        // 从左到右依次遍历找到当前的最大值
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        // 从右到左依次遍历找到当前的最大值
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        //同时遍历，找到当前的两边的最大值
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }


    /*

    从动态编程方法的示意图中我们注意到，只要 right_max[i]>left_max[i] （元素 0 到元素 6），
    积水高度将由 left_max 决定，类似地 left_max[i]>right_max[i]（元素 8 到元素 11）。
    所以我们可以认为如果一端有更高的条形块（例如右端），积水的高度依赖于当前方向的高度（从左到右）。
    当我们发现另一侧（右侧）的条形块高度不是最高的，我们则开始从相反的方向遍历（从右到左）。
    我们必须在遍历时维护 left_max 和 right_max ，但是我们现在可以使用两个指针交替进行，实现 1 次遍历即可完成。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    */
    private static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if ( height[left] >= left_max ) {
                    left_max = height[left];
                } else{
                    ans += (left_max - height[left]);
                }
                ++left;
            }
            else {
                if (height[right] >= right_max ) {
                    right_max = height[right];
                }
                else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;

    }


}
