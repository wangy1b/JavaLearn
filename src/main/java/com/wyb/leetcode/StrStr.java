package com.wyb.leetcode;

public class StrStr {
    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        // String haystack = "aaaaa", needle = "bba";
        // String haystack = "aa", needle = "aa";
        // String haystack = "", needle = "";
        System.out.println(strStr(haystack,needle));
    }


    private static int strStr(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= h - n; i++) {
                String cut = haystack.substring(i,i + n);
                if ( cut.equals(needle)) {
                    return i;
                }
        }
        return -1;
    }
}
