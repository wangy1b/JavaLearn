package com.wyb.leetcode;

public class CountAndSay {
    public static void main(String[] args) {
    /**
     1.     1
     2.     11
     3.     21
     4.     1211
     5.     111221
    **/

        // System.out.println(findCount("1"));
        System.out.println(countAndSay(7));

    }

    private static String findCount(String s) {
        StringBuffer newStr =  new StringBuffer() ;
        int lastNum = Integer.valueOf(String.valueOf(s.charAt(0)));
        int i = 1;
        int count = 1;
        while ( i < s.length() ) {
            int currentNum = Integer.valueOf(String.valueOf(s.charAt(i)));
            if (lastNum == currentNum) {
                count ++ ;
            } else {
                newStr.append(count).append(String.valueOf(lastNum));
                count = 1;
                lastNum = currentNum;
            }
            i++;
        }
        newStr.append(count).append(String.valueOf(lastNum));
        return newStr.toString();
    }


    private static String countAndSay(int n) {
        if ( n == 1 ) return "1";
        String last_str = countAndSay(n - 1);
        String current_str = findCount(last_str);
        return current_str;
    }


    public String countAndSayOther(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuffer res = new StringBuffer();
        String str = countAndSayOther(n - 1);
        int length = str.length();
        int a = 0;
        for (int i = 1; i < length + 1; i++) {
            if (i == length) {
                return res.append(i - a).append(str.charAt(a)).toString();
            } else if (str.charAt(i) != str.charAt(a) ) {
                res.append(i - a).append(str.charAt(a));
                a = i;
            }
        }
        return res.toString();
    }


}
