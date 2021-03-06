package com.wyb.DesignPatterns.Iterator.v3;

public class Arraylist_ implements Collection_ {

    Object[] objects = new Object[10];
    private int index = 0;
    @Override
    public void add(Object o) {
        if(index == objects.length) {
            Object[] newObjects = new Object[objects.length*2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }
        index++;
    }

    @Override
    public int size() {
        return index;
    }
}
