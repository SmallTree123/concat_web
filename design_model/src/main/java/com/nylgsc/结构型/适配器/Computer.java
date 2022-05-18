package com.nylgsc.结构型.适配器;

//客户端类，想上网，插不上网线
public class Computer {

    public void  net(Adapter adapter){
        //上网的具体实现，找一个转接头
        adapter.handRequest();
    }

    public void  video4K(Adapter adapter){
        //上网的具体实现，找一个转接头
        adapter.video4K();
    }
}
