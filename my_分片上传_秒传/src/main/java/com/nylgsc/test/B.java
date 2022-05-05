package com.nylgsc.test;

public interface B {
    public void say();

    public abstract void test();

    default void def(){
        System.out.println("默认接口实现");
    }
    static void sta(){
        System.out.println("静态方法");
    }
}
