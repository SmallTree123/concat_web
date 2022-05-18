package com.nylgsc.创建型.建造者;

public class 建造者模式 {
    public static void main(String[] args) {
        Director director = new Director();
        Product product = director.bulildHouse(new Worker());
        Product product2 = director.bulildBieshu(new Bieshu());
        System.out.println(product.toString());
        System.out.println(product2.toString());
    }
}

