package com.testframwork.jdk8.generalTypeTest;

public class App {

    public static void main(String[] args) {
        Plate<? extends Friut> applePlate = new Plate<>();
//    Plate<? extends Friut> plate   =   applePlate;
        Plate<Object> apple = new Plate<>();
        /*由于apple中的参数类型必须继承Friut，但是apple中的参数类型是Object，并不继承Friut，所以在编译阶段会报错。*/
//    Plate<? extends Friut> objectPlate = apple;

    }
}
