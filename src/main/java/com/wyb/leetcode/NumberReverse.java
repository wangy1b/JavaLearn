package com.wyb.leetcode;

public class NumberReverse {

    public static void main(String[] args) {
        int a = 1230;
        System.out.println(reverse(a));
    }
    private static int reverse(int x) {
        int y = 0;
        while( x !=0 ) {
            // 判断是否溢出
            if ((y * 10) / 10 != y) {
                y = 0;
                break;
            }
             y = y*10 + x % 10 ;
             x = x / 10;
        }
        return  y;
    }
}
