package com.wyb.leetcode;

public class AddBinary {
    public static void main(String[] args) {
        // String a = "1011";
        // String b = "1010";
        String a = "1";
        // String b = "11";
        String b = "111";
        // System.out.println(addBinary(a, b));

        System.out.println(addBinary(6,2));
        System.out.println(avgBinary(6,2));
    }

    // 二进制求和
    private static Integer addBinary(Integer num1, Integer num2) {
        int a = num1 ^ num2;
        int b = num1 & num2;
        b <<= 1;
        if (b == 0)
            return a;
        else
            return addBinary(a, b);


    }

    // 二进制求平均数
    private static Integer avgBinary(Integer num1, Integer num2) {
        int a = num1 ^ num2;
        int b = num1 & num2;
        a >>= 1;
        return addBinary(a,b);
    }



    private static String addBinary(String a, String b) {
        String minS = a.length() < b.length() ? a : b;
        String maxS = b.length() > a.length() ? b : a;
        int minLen = minS.length();
        int maxLen = maxS.length();
        StringBuffer res = new StringBuffer();
        int current_digit_sum = 0;
        int spill = 0;
        for (int i = maxLen - 1 ; i >= 0; i--) {
            if ( i >= maxLen - minLen ) {
                current_digit_sum = Integer.valueOf(String.valueOf(maxS.charAt(i))) + Integer.valueOf(String.valueOf(minS.charAt(i-(maxLen - minLen)))) + spill ;
            } else {
                current_digit_sum = Integer.valueOf(String.valueOf(maxS.charAt(i))) + spill ;
            }
            System.out.println("current_digit_sum : " + current_digit_sum);
            spill = current_digit_sum > 1 ? 1: 0;
            if (spill == 1) {
                res.insert(maxLen - 1 - i,current_digit_sum % 2);
            } else {
                res.insert(maxLen - 1 - i,current_digit_sum);
            }
        }
        if (spill == 1) res.insert(maxLen,current_digit_sum / 2);
        return res.reverse().toString();
    }
}
