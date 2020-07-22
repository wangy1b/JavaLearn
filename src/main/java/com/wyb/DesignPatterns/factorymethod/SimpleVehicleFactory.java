package DesignPatterns.factorymethod;

public class SimpleVehicleFactory {
    public Car createCar(){
        return new Car();
    }

    public Broom createBroom(){
        return new Broom();
    }

    public Plane createPlane(){
        return new Plane();
    }
}
