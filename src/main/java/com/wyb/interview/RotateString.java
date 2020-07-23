package com.wyb.interview;

// 左右字符串交换
public class RotateString {
    public  static String rotate1(String s,int leftSize) {
        if (leftSize <=0 || leftSize >= s.length()) {
            return s;
        }
        return process1(s.toCharArray(), 0, leftSize - 1, s.length() - 1);
    }
    //如果认为str[L..M]是做左部分，str[M+1..R]是右部分
    //请原地调整，只能用几个有限变量，左->右，右->左
    public static String process1(char[] str, int L, int M, int R){
        reverse(str, L, M);
        reverse(str, M + 1, R);
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

    public static String rotate2(String s, int leftSize) {
        if (leftSize <=0 || leftSize >= s.length()) {
            return s;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        int lpart = leftSize;
        int rpart = str.length - leftSize;
        int same = Math.min(lpart, rpart);
        int diff = lpart - rpart;
        exchange(str, L, R, same);
        while (diff != 0 ){
            if(diff > 0) { //左边大
                L += same;
                lpart = diff;
            } else { //右边大
                R -= same;
                rpart = -diff;
            }
            same = Math.min(lpart,rpart);
            diff = lpart - rpart;
            exchange(str, L, R, same);
        }
        return String.valueOf(str);
    }
    // str[l..]数出size大小，和str[..r]数出size大小，来交换
    public static void exchange(char[] chas, int start, int end, int size){
        int i = end - size + 1;
        char tmp = 0;
        while (size-- !=0) {
            tmp = chas[start];
            chas[start] = chas[i];
            chas[i] = tmp;
            start++;
            i++;
        }

    }

    public static void main(String[] args) {
        String test_str = "abcdefgh";
        int cut_size = 2;
        String test_result1 = rotate1(test_str, cut_size);
        System.out.println(test_result1);
        String test_result2 = rotate2(test_str, cut_size);
        System.out.println(test_result2);
    }
}
