package com.wyb.leetcode;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class BracketsIsValid {

    public static void main(String[] args) {
        // System.out.println(isValid("()"));
        // System.out.println(isValid("()[]{}"));
        // System.out.println(isValid("(}"));
        // System.out.println(isValid("({["));
        // System.out.println(isValid(""));
        System.out.println(isValidOffical(")]}"));
    }

    private static boolean isValid(String s) {
        HashMap<Character,Character> hashMap = new HashMap<>();
        hashMap.put(')','(');
        hashMap.put(']','[');
        hashMap.put('}','{');

        if (s.isEmpty()) return false;
        Stack<Character> stack = new Stack();
        for (char c: s.toCharArray()) {
            if ( c == '('|| c  == '{' || c  == '[') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                char pc = stack.pop();
                if (pc != hashMap.get(c)) return false;
            } else return false;
        }
        return stack.isEmpty();
    }


    private static boolean isValidOffical(String s) {

        HashMap<Character,Character> hashMap = new HashMap<>();
        hashMap.put(')','(');
        hashMap.put(']','[');
        hashMap.put('}','{');

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (hashMap.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != hashMap.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}
