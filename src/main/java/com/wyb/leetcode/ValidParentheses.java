package com.wyb.leetcode;

import java.util.HashMap;
import java.util.LinkedList;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "){";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        if (s.length() <= 1 || s.length() % 2 == 1 ) return false;
        HashMap<Character,Character> hashMap = new HashMap();
        hashMap.put(')','(');
        hashMap.put(']','[');
        hashMap.put('}','{');

        LinkedList<Character> stack = new LinkedList();

        for(Character c: s.toCharArray())
            if(!hashMap.containsKey(c)) {
                stack.add(c);
            } else {
                if (stack.pollLast() != hashMap.get(c)) {
                    return false;
                }
            }
        return stack.isEmpty();

    }
}
