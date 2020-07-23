package com.wyb.DesignPatterns.Iterator.v4;

/**
 *  v1:构建一个容器，可以添加对象
 *  v2:用链表来实现一个容器
 *  v3:添加容器的共同接口，实现容器的替换
 *  v4:如何对容器遍历？
 */
public class Main {
    public static void main(String[] args) {
        Arraylist_ list = new Arraylist_();
//        LinkedList_ list = new LinkedList_();
        for (int i = 0; i < 15; i++) {
            list.add("s" + i);
        }
        System.out.println(list.size());

        Iterator_ it = list.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            System.out.println(o);
        }
    }
}
