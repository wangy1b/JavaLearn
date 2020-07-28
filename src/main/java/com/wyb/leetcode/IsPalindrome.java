package com.wyb.leetcode;

public class IsPalindrome {

    public static void main(String[] args) {
        // System.out.println(isPalindrome(10));
        System.out.println(isPalindromeNew(121));
    }


    private static boolean isPalindrome(int x) {
        if(x<0) return false;
        String s = String.valueOf(x);
        String t = "";
        for (int i = s.length() - 1; i >= 0 ; i--) {
            t += String.valueOf(s.charAt(i));
        }
        System.out.println(t);
        return (s.hashCode()==t.hashCode())?  true : false;
    }


    private static boolean isPalindromeNew(int x) {
        int input = x;
        if(x<0) return false;
        int y = 0;
        while (x > 0){
            y = x % 10 + y*10;
            x = x / 10;
        }
        return input == y ? true : false;
    }
}
