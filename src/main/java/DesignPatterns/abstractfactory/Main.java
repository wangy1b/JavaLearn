package DesignPatterns.abstractfactory;


public class Main {
    public static void main(String[] args) {
//        Car c = new Car();
//        c.go();
//        AK47 w = new AK47();
//        w.shoot();
//        Bread b = new Bread();
//        b.printName();


//        AbstracFactory f = new ModernFactory();
        AbstracFactory f = new MagicFactory();
        Vehicle c = f.createVehicle();
        c.go();
        Weapon w = f.createWeapon();
        w.shoot();
        Food h = f.createFood();
        h.printName();





    }
}
