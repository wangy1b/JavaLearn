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
    // -1
    int ne = Integer.MAX_VALUE + Integer.MAX_VALUE + 1;
    // 1的n次方，1,2,4,8...
    long[] poCache = new long[32];
    // -1的n次方，-1,-2,-4,-8...
    long[] neCache = new long[32];
    long[] cache = new long[32]; // 存放乘数或除数的倍数，1*a,2*a,4*a,8*a...主要用于快速计算，不然容易超时
    long[] cache1 = new long[32]; // 存放乘数或除数的倍数 负数-1*a,-2*a,-4*a,-8*a


    public Operations() {
        poCache[0] = 1;
        neCache[0] = ne;
        for (int i = 1; i < 32; i++) {
            poCache[i] = poCache[i + ne] + poCache[i + ne];
            neCache[i] = neCache[i + ne] + neCache[i + ne];
        }
    }

    // minus(a, b) 减法，返回a - b
    public int minus(int a, int b) {
        if (a == b) return 0;
        int index = 31;
        // a - b = res =>  (a - b) + (b - b) = res => 让ab同幅度递减，只要让b减到0，那res = a 剩下部分
        while ( b != 0) {
            // b 为正数
            if (b > 0) {
                if (b >= poCache[index]) { // 如果b大于2的index次方，
                    b += neCache[index];
                    a += neCache[index];
                } else {
                    index += ne;
                }
            } else { // b 为负数
                if (b <= neCache[index]) {
                    b += poCache[index];
                    a += poCache[index];
                } else {
                    index += ne;
                }
            }
        }
        return a;
    }

    // multiply(a, b) 乘法，返回a * b
    // 转化为a个b相加(当a<b时)，注意符号就行
    public int multiply(int a, int b) {
        if (a == 0 || b == 0) return 0;
        if (a == 1) return b;
        if (b == 1) return a;
        if (a == ne) return minus(0, b);
        if (b == ne) return minus(0, a);
        int sign = (a > 0 && b > 0) || (a < 0 && b < 0) ? 1 : ne;
        // 把b变成正数
        if (b < 0) {
            b = minus(0, b);
        }

        cache[0] = a;
        for (int i = 1; i < 32; i++) {
            cache[i] = cache[i + ne] + cache[i + ne];
        }
        int index = 30; // 从31开始应该也是可以的
        int ret = 0;
        int retSign = a > 0 ? 1 : ne; // 记录返回值的符号
        while (b > 0) {
            if (b >= poCache[index]) {
                b += neCache[index];
                ret += cache[index];
                retSign = ret > 0 ? 1 : ne;// 记录返回值的符号
            } else {
                index += ne;
            }
        }
        // 根据初始值改变返回值的符号
        if ((sign < 0 && ret > 0) || (sign > 0 && ret < 0)) {
            ret = minus(0, ret);
        }
        // 结果溢出，返回值的符号会变成相反的
        if (retSign != (a > 0 ? 1 : ne)) {
            ret = minus(0, ret);
        }
        return ret;
    }

    // divide(a, b) 除法，返回a / b
    // 假设结果为res，转化为res * b < a，注意符号
    public int divide(int a, int b) {
        // 被除数不能为0
        assert b != 0;
        if (a == 0) return 0;
        if (b == 1) return a;
        if (b == ne) return minus(0, a);
        int ret = 0;
        int sign = (a > 0 && b > 0) || (a < 0 && b < 0) ? 1 : ne;
        long nb = b;
        long pb = b;
        if (b < 0) {
            b = minus(0, b);
        } else {
            nb = minus(0, b);
        }
        if (a < 0) {
            a = minus(0, a);
        }
        cache[0] = b;
        cache1[0] = nb;
        int index = 1;
        for (; index < 32; ++index) {
            cache[index] = cache[index + ne] + cache[index + ne];
            cache1[index] = cache1[index + ne] + cache1[index + ne];
            if (cache1[index] >= a) {
                break; // 找到最大值就可以返回了，不用计算完
            }
        }
        if (index >= 32) index = 31;
        while (a >= b) {
            if (a >= cache[index]) {
                ret += poCache[index];// 注意这里是2的index次方的值
                a += cache1[index];
            } else {
                index += ne;
            }
        }
        if (sign < 0) {
            ret = minus(0, ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        Operations operations = new Operations();
        System.out.println(operations.minus(1, 2)); //返回-1
        // System.out.println(operations.minus(10,2147483647)); //返回-1
        // System.out.println(operations.multiply(3, 4)); //返回12
        // long t1 = System.nanoTime();
        // System.out.println(operations.divide(5, -3)); //返回-2
        // long t2 = System.nanoTime();
        // System.out.println("divide用时："+(t2 - t1)/1000000000 + "s");
    }
}