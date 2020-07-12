package DesignPatterns.factorymethod;

public class Main {
    public static void main(String[] args) {
//        Car c = new Car();
//        c.go();
//        Plane p = new Plane();
//        p.go();

//        Moveable m = new Broom();
//        m.go();


        Moveable m = new CarFactory().createCar();
        m.go();

    }
}
