package com.wyb.leetcode;

import java.util.HashMap;

public class RomanToInt {
    public static void main(String[] args) {
        int a = romanToInt("MCMXCIV");
        System.out.println(a);
    }

    private static int romanToInt(String s) {
        HashMap<String, Integer> hashMap = new HashMap();
        hashMap.put("I",1);
        hashMap.put("V",5);
        hashMap.put("X",10);
        hashMap.put("L",50);
        hashMap.put("C",100);
        hashMap.put("D",500);
        hashMap.put("M",1000);

        int result = 0;
        int lastNum = 0;
        for (int i = 0; i < s.length(); i++) {
            String aphaNum = String.valueOf(s.charAt(i));
            int intNum = hashMap.get(aphaNum);
            if (intNum > lastNum & lastNum != 0) {
                result -= lastNum;
                result += (intNum -lastNum );
            } else result += intNum;
            lastNum = intNum;
        }

        return result;

    }
}
