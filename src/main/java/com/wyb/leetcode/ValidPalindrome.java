package com.wyb.leetcode;

public class ValidPalindrome {

    public static void main(String[] args) {
        // String s = "A man, a plan, a canal: Panama";
        String s = "a12, b   B , 21 a + - / | ";
        System.out.println(s.replaceAll("\\P{LD}+", "").toLowerCase());
        System.out.println(isPalindrome(s));
    }


    private static boolean isPalindrome(String s) {
        boolean res = true;
        if (s.isEmpty()) return res;
        StringBuffer ns = new StringBuffer();
        ns.append(s.replaceAll("\\P{LD}+", "").toLowerCase());
        int start = 0, end = ns.length() -1;
        while (start < end){
            if (ns.charAt(start++) != ns.charAt(end--)) res = false;
        }
        return res;
    }



}
