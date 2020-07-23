package com.wyb.DesignPatterns.Strategy;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Cat[] a = {new Cat(3,3),new Cat(5,5), new Cat(1,1)};
        Dog[] b = {new Dog(3),new Dog(5), new Dog(1)};
        Sorter<Dog> sorter = new Sorter();
        sorter.sort(b,new DogComparator());

//        sorter.sort(b,(o1, o2) -> {
//            if (o1.food < o2.food) {return -1; }
//            else if (o1.food > o2.food) {return 1;}
//            else {return 0;}});
        System.out.println(Arrays.toString(b));
    }
}
