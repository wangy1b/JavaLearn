package com.wyb.leetcode;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        // System.out.println(longestCommonPrefix(new String[]{"flower","flow","flow"}));
        System.out.println(longestCommonPrefix(new String[]{"a","a"}));
        // System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

    private static  String longestCommonPrefix(String[] strs){
        if (strs.length == 0 || strs == null) return "";
        if (strs.length == 1) return strs[0];
        for (int i = 0; i <= strs[0].length()-1; i++) {
            String c = String.valueOf(strs[0].substring(0,i+1));
            for (int j = 1; j < strs.length ; j++) {
                if (i<= strs[j].length()-1) {
                    String d = String.valueOf(strs[j].substring(0,i+1));
                    if (c.hashCode() != d.hashCode() ) {
                        return String.valueOf(strs[0].substring(0,i));
                    }
                } else return String.valueOf(strs[0].substring(0,i));
            }
        }
        return strs[0];

    }
}
