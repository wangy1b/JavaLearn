package com.wyb.DesignPatterns.factorymethod;

public class CarFactory {
    public Moveable createCar(){
        System.out.println("a car createed");
        return new Car();
    }

    public Moveable createPlane(){
        System.out.println("a car createed");
        return new Plane();
    }
}
