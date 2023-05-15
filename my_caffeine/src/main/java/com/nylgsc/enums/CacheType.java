package com.nylgsc.enums;

public enum CacheType {

    IZUUL(10),
    MUMU(2);
    private int expires;

    CacheType(int expires){
        this.expires = expires;
    }

    public int getExpires(){
        return expires;
    }
}
