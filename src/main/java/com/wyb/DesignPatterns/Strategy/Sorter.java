package com.wyb.DesignPatterns.Strategy;

public class Sorter<T> {
//    public void sort(Comparable[] arr){
//        for (int i = 0; i < arr.length -1; i++) {
//            int minPos = i;
//
//            for (int j = 0; j < arr.length; j++) {
//                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j :minPos;
//            }
//            swap(arr,i,minPos);
//        }
//    }

    public void sort(T[] arr,Comparator<T> comparator){
        for (int i = 0; i < arr.length -1; i++) {
            int minPos = i;

            for (int j = 0; j < arr.length; j++) {
                minPos = comparator.compare(arr[j],arr[minPos]) == -1 ? j :minPos;
            }
            swap(arr,i,minPos);
        }
    }


    void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
