package com.wyb.leetcode;

public class ValidPalindrome {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }


    private static boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;
        s.replaceAll("(,|:)","");
        System.out.println(s);


        return false;
    }



}
