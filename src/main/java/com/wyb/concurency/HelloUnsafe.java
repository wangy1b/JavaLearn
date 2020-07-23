package com.wyb.concurency;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class HelloUnsafe {

    static class M {
        private M() {}
        int i = 0 ;
    }

    AtomicInteger cnt = new AtomicInteger(0);
    void n () {
        for (int i = 0; i < 10000; i++) {
            cnt.incrementAndGet();
        }
        System.out.println(cnt);
    }


//    public static void main(String[] args) throws InstantiationException {
//        Unsafe unsafe = Unsafe.getUnsafe();
//        M m = (M)unsafe.allocateInstance(M.class);
//        m.i = 9;
//        System.out.println(m.i);
//    }

}
