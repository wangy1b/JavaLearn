/**
 * 跟01一个意思
 */

package com.wyb.DesignPatterns.singleton;

public class Mgr02 {

    private static final Mgr02 INSTANCE ;
    static {
        INSTANCE = new Mgr02();
    }

    //构造方法为private
    private Mgr02(){};

    public static  Mgr02 getInstance() {return INSTANCE;};

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr02 m1 = Mgr02.getInstance();
        Mgr02 m2 = Mgr02.getInstance();
        System.out.println(m1 == m2 );
    }
}
