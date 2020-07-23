package com.wyb.DesignPatterns.abstractfactory;

public class ModernFactory  extends AbstracFactory{

    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }
}

