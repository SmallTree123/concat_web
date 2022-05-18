package com.nylgsc.结构型.适配器;

public class Adapter implements NetToUSB {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void handRequest() {
        adaptee.request();
    }

    @Override
    public void video4K() {
        adaptee.video4k();
    }
}
