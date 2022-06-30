package com.nylgsc.test;

public interface B extends D2{
    public void say();
    int name = 0;

    public abstract void test();

    default void def(){
        System.out.println("默认接口实现");
    }
    static void sta(){
        System.out.println("静态方法");
    }
}
