package com.wyb.leetcode;

/**
 * 面试题 16.09. 运算
 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，
 程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，
 不允许使用位运算。

 你的实现应该支持如下操作：

 Operations() 构造函数
 minus(a, b) 减法，返回a - b
 multiply(a, b) 乘法，返回a * b
 divide(a, b) 除法，返回a / b
 示例：

 Operations operations = new Operations();
 operations.minus(1, 2); //返回-1
 operations.multiply(3, 4); //返回12
 operations.divide(5, -2); //返回-2
 提示：

 你可以假设函数输入一定是有效的，例如不会出现除法分母为0的情况
 单个用例的函数调用次数不会超过1000次

 https://leetcode-cn.com/problems/operations-lcci/

 */
class Operations {

    public Operations() {

    }

    // 超出时间限制
    // minus(a, b) 减法，返回a - b
    public int minus(int a, int b) {
        return a + (-b);
    }

    // multiply(a, b) 乘法，返回a * b
    public int multiply(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int flag = 1;
        if ( a >= 0 & b >= 0) {
            flag = 1;
        }
        else {
            flag = -1;
            a = Math.abs(a);
            b = Math.abs(b);
        }

        int maxOne = a > b ? a : b;
        int minOne = a > b ? b : a;
        int res = 0;
        for (int i = 0; i < minOne; i++) {
            res += maxOne;
        }
        return flag > 0 ? res : -res;
    }

    // divide(a, b) 除法，返回a / b
    public int divide(int a, int b) {
        int res = a;

        int flag = 1;
        if ( a >= 0 & b >= 0) {
            flag = 1;
        }
        else {
            flag = -1;
            a = Math.abs(a);
            b = Math.abs(b);
        }



        while (multiply(res , b) > a) {
                res =  minus(res , 1);
            }

        return multiply(res,flag);
    }

    public static void main(String[] args) {
        Operations operations = new Operations();
        System.out.println(operations.minus(1, 2)); //返回-1
        System.out.println(operations.multiply(3, 4)); //返回12
        System.out.println(operations.divide(5, -3)); //返回-2
    }
}
