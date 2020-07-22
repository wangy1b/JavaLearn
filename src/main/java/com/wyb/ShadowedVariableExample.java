package com.wyb;class Outer1 {
    private int size = 10;
    int getOutSize(){
        return this.size;
    }
    public class Inner {
        private int size=20;
        public void someMethod (int size) {
            System.out.println ("Method parameter (size): " + size);
            System.out.println ("Inner size: " + this.size);
            System.out.println ("Outer size: " + Outer1.this.size);
            System.out.println ("Outer getOutSize: " + Outer1.this.getOutSize());
        }
    }
}
public class ShadowedVariableExample {
    public static void main (String[] args) {
        Outer1 outer = new Outer1();
        System.out.println("test getOutSize:" + outer.getOutSize());
        Outer1.Inner inner = outer.new Inner();
        inner.someMethod(5);
    }
}
