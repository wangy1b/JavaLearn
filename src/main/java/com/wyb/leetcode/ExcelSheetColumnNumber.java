package com.wyb.leetcode;

public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        // String s = "AB";
        String s = "ZZ";
        System.out.println(titleToNumber(s));
    }

    private static int titleToNumber(String s) {
        if (s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int castAscii = (int) ch;
            int val = castAscii - 64;
            res += val * Math.pow(26, s.length() - 1 - i);
        }
        return res;
    }
}
