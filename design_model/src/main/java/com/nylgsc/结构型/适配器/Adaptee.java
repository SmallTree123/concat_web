package com.nylgsc.结构型.适配器;

//要适配的类 ，网线
public class Adaptee {

    public void request(){
        System.out.println("插上网线，可以上网了...");
    }

    public void video4k(){
        System.out.println("开启4k video视频");
    }
}
