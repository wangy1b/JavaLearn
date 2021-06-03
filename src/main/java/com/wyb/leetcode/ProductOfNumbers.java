package com.wyb.leetcode;

public class ProductOfNumbers {

    /* 1352. 最后 K 个数的乘积
    请你实现一个「数字乘积类」ProductOfNumbers，要求支持下述两种方法：

    1. add(int num)
    将数字 num 添加到当前数字列表的最后面。

    2. getProduct(int k)
    返回当前数字列表中，最后 k 个数字的乘积。
    你可以假设当前列表中始终 至少 包含 k 个数字。
    题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。



    示例：

    输入：
    ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
    [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

    输出：
    [null,null,null,null,null,null,20,40,0,null,32]

    解释：
    ProductOfNumbers productOfNumbers = new ProductOfNumbers();
    productOfNumbers.add(3);        // [3]
    productOfNumbers.add(0);        // [3,0]
    productOfNumbers.add(2);        // [3,0,2]
    productOfNumbers.add(5);        // [3,0,2,5]
    productOfNumbers.add(4);        // [3,0,2,5,4]
    productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
    productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
    productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
    productOfNumbers.add(8);        // [3,0,2,5,4,8]
    productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32
     */

    private int[] data;
    public int index = 0;
    private int size = 0;
    private int default_size = 10;
    private int start_index = 0;

    private void grow() {
        int new_size = size + (size >> 1);
        int[] data_new = new int[new_size];
        System.arraycopy(data, 0, data_new, 0, size);
        data = data_new;
        size = new_size;
    }

    public ProductOfNumbers() {
        data = new int[default_size];
        size = default_size;
    }

    public void add(int num) {
        if (index == size)
            grow();
        // 每次增加就把最后一个值跟前面所有的数据都乘一遍
        // 包含0 之前的都已经全是0了，不需要再去遍历
        for (int i = start_index; i < index; i++) {
            data[i] *= num;
        }
        // 赋值当前值
        data[index++] = num;
        // 记录包含0 的下一个位置
        if (num == 0)
            start_index = index;
    }

    public int getProduct(int k) {
        if (k <= 0) return 0;
        // 返回第index - k 个的值
        return data[index - k];
    }


    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32
        // for (int i = 0; i < 32; i++) {
        //     productOfNumbers.add(i);
        // }
    }
}
