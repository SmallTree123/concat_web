package com.nylgsc.创建型.工厂方法;

/**
 * 工厂方法只关注于产品等级
 *
 * 小米手机和华为手机就是一个产品等级的
 *
 * 产品等级
 * |
 * |   小米手机      小米路由器
 * |
 * |   华为手机     华为路由器
 * |
 * |——————————————————————>产品族
 */
public class 工厂方法 {
    public static void main(String[] args) {
        Factory factory = new DazhongFactory();
        factory.getCar().carName();
    }
}

interface Car{
    void carName();
}

class Wuling implements Car{
    @Override
    public void carName() {
        System.out.println("生产出五菱宏光...");
    }
}
class DaZhong implements Car{
    @Override
    public void carName() {
        System.out.println("生产出大众...");
    }
}
interface Factory{
    Car getCar();
}
class WulingFactory implements Factory{
    @Override
    public Car getCar() {
        return new Wuling();
    }
}

class DazhongFactory implements Factory{
    @Override
    public Car getCar() {
        return new DaZhong();
    }
}



