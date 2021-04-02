package com.wyb.leetcode;

// 二进制中1的个数
// 输入一个整数，输出该数二进制表示中1的个数(其中负数用补码表示)
public class NumberOf1 {
    private static int countNumberOfOneBinary(int n){
        int cnt = 0;
        while (n != 0) {
            n = n & (n-1);
            cnt++;
        }
        return cnt;
    }

    public int countNumberOfOneBinary1(int n) {
        int va=0;
        for(int i=0;i<32;i++)
        {
            if((n&(1<<i))!=0)
            {
                va++;
            }
        }
        return va;
    }

    public static void main(String[] args) {
        NumberOf1 numberOf1 = new NumberOf1();
        System.out.println(numberOf1.countNumberOfOneBinary1(5));
        System.out.println(numberOf1.countNumberOfOneBinary(5));
    }
}
