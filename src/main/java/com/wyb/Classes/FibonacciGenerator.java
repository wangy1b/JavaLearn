package com.wyb.Classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FibonacciGenerator {

    public static void main(String[] args) {
//        FibonacciGenerator f = new FibonacciGenerator();
//        int result = f.generate(4);
        int result = generate(4);
        System.out.println(result);


        //test Collections.shuffle
        Integer[] o = {1,2,3,4,5,6};
        List<Integer> a = Arrays.asList(o) ;
        System.out.println(a);
        Collections.shuffle(a);
        System.out.println(a);

    }

    private static int generate(int n) {
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement frame : frames) {
            System.out.println("calling: " + frame.getMethodName());
        }
        if (n <= 2) {
            return 1;
        } else {
            return generate(n-1) + generate(n-2);
        }

    }
}
