package com.nylgsc.创建型.建造者;

public class Worker extends Builder {

    private Product product;

     public Worker(){
         product = new Product();
     }


    @Override
    void buildA() {
        product.setA("建造地基");
    }

    @Override
    void buildB() {
        product.setB("铺设钢筋");
    }

    @Override
    void buildC() {
        product.setC("浇灌水泥");
    }

    @Override
    void buildD() {
         product.setD("交房");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
