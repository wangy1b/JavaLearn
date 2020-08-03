package com.wyb.leetcode;

public class PlusOne {
    public static void main(String[] args) {

        int[] digits = {9,9,9};
        // printArr(digits);
        printArr(plusOne(digits));
        // printArr(digits);
    }

    private static void printArr(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print( " "+ ints[i]);
        }
        System.out.println();
    }

    private static int[] plusOne(int[] digits) {
        int[] res = new int[digits.length+1];
        int last = digits[digits.length - 1] ;
        int more = last + 1 >= 10 ? 1 : 0;
        for (int i = digits.length -2 ; i >= 0; i--) {
            if (digits[i] + more>= 10 ) {
                res[i+1] = digits[i] + more - 10;
                more = 1;
            } else {
                res[i+1] = digits[i] + more ;
                more = 0;
            }
        }
        res[digits.length] = last + 1 >= 10 ? last - 9 :last + 1;
        res[0] = more == 1 ? 1 : 0;
        System.arraycopy(res, 1, digits,0, digits.length );
        return more == 1 ? res : digits;
        // return res;
    }
}
