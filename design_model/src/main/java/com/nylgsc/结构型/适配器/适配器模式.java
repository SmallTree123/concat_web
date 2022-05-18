package com.nylgsc.结构型.适配器;

public class 适配器模式 {
    public static void main(String[] args) {
        Computer computer= new Computer(); //电脑
        Adaptee adaptee = new Adaptee();  //网线，这里也可以是HDMI
        Adapter adapter = new Adapter(adaptee);//转接器
//        computer.net(adapter);
        computer.video4K(adapter);
    }
}
