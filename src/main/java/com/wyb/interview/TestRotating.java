package com.wyb.interview;

public class TestRotating {
    public static void main(String[] args) {
        String test_str = "12345678";
        int cut_size = 5;
        String test_result1 = rotate(test_str, cut_size);
        System.out.println(test_result1);
    }


    public  static String rotate(String s,int leftSize) {
        if (leftSize <=0 || leftSize >= s.length()) {
            return s;
        }
        return process(s.toCharArray(), 0, leftSize - 1, s.length() - 1);
    }


    public static String process(char[] str, int L, int M, int R){
        reverse(str, L, M);
        reverse(str, L, R);
        return String.valueOf(str);
    }

    public static void reverse(char[] str, int L, int R) {
        while (L < R) {
            char tmp = str[L];
            str[L++] = str[R];
            str[R--] = tmp;
        }
    }

}
