package com.wyb.leetcode;

public class LengthOfLastWord {
    public static void main(String[] args) {
        // String s = "Hello World";
        // String s = "";
        String s = " a";
        System.out.println(lengthOfLastWord(s));
    }

    private static int lengthOfLastWord(String s) {
        // if (s.replace(" ", "").length() == 0) return 0;
        // return s.split(" ")[s.split(" ").length-1].length();

        int len = 0;
        boolean flag = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' '  ) {
                if (flag)  {
                    len = 0;
                    // continue;
                } else {
                    return len;
                }
            } else {
                len ++;
                flag = false;
            }

        }
        return len;
    }
}
