package com.nylgsc.创建型.建造者;

public class Bieshu extends Builder {

    private Product product;

     public Bieshu(){
         product = new Product();
     }


    @Override
    void buildA() {
        product.setA("建高楼");
    }

    @Override
    void buildB() {
        product.setB("建高楼");
    }

    @Override
    void buildC() {
        product.setC("建高楼");
    }

    @Override
    void buildD() {
         product.setD("建高楼");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
