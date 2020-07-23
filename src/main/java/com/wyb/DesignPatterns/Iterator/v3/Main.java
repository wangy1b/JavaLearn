package com.wyb.DesignPatterns.Iterator.v3;

public class Main {
    public static void main(String[] args) {
//        Arraylist_ list = new Arraylist_();
        LinkedList_ list = new LinkedList_();
        for (int i = 0; i < 15; i++) {
            list.add("s" + i);
        }
        System.out.println(list.size());
    }
}
