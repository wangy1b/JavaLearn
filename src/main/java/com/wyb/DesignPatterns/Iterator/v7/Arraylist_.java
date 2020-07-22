package DesignPatterns.Iterator.v7;

public class Arraylist_<E> implements Collection_<E> {

    E[] objects = (E[]) new Object[10];
    private int index = 0;
    @Override
    public void add(E o) {
        if(index == objects.length) {
            E[] newObjects = (E[]) new Object[objects.length*2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index++;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator_<E> iterator() {
        return new ArryListIterator();
    }
    private class ArryListIterator implements Iterator_ {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if(currentIndex >= index){ return false;}
            return true;
        }

        @Override
        public E next() {
            E o =(E) objects[currentIndex];
            currentIndex++;
            return o;
        }
    }



}
