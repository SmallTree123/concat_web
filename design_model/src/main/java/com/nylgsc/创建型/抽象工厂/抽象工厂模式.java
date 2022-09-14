package com.nylgsc.创建型.抽象工厂;

/**
 *   抽象工厂模式关注于产品族
 *
 *
 * 产品等级
 * |
 * |   小米手机      小米路由器
 * |
 * |   华为手机     华为路由器
 * |
 * |——————————————————————>产品族
 */
public class 抽象工厂模式 {
    public static void main(String[] args) {
        System.out.println("===============小米产品==============");
        AFactory factory = new XiaomiFactory();
        Iphone iphone = factory.createIphone();
        iphone.start();
        iphone.sendSMS();
        iphone.shutdown();
        System.out.println("===============华为产品==============");
        AFactory afactory = new HuaweiFactory();
        Router router = afactory.createRoute();
        router.start();
        router.openWIFI();
        router.shutdown();

    }
}
interface Iphone{
    void start();
    void shutdown();
    void sendSMS();
}
interface Router{
    void start();
    void shutdown();
    void openWIFI();
}
class XiaomiIphone implements Iphone{
    @Override
    public void start() {
        System.out.println("开启小米手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米手机");
    }

    @Override
    public void sendSMS() {
        System.out.println("用小米发短信");
    }
}

class HuaWeiIphone implements Iphone{
    @Override
    public void start() {
        System.out.println("开启华为手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为手机");
    }

    @Override
    public void sendSMS() {
        System.out.println("用华为发短信");
    }
}
class XiaomiRouter implements Router{
    @Override
    public void start() {
        System.out.println("开启小米路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米路由器");
    }

    @Override
    public void openWIFI() {
        System.out.println("开启小米路由器wifi");
    }
}

class HuaweiRouter implements Router{
    @Override
    public void start() {
        System.out.println("开启华为路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为路由器");
    }

    @Override
    public void openWIFI() {
        System.out.println("开启华为路由器wifi");
    }
}

interface AFactory{
    Iphone createIphone();
    Router createRoute();
}
class XiaomiFactory implements AFactory{
    @Override
    public Iphone createIphone() {
        return new XiaomiIphone();
    }

    @Override
    public Router createRoute() {
        return new XiaomiRouter();
    }
}

class HuaweiFactory implements AFactory{
    @Override
    public Iphone createIphone() {
        return new HuaWeiIphone();
    }

    @Override
    public Router createRoute() {
        return new HuaweiRouter();
    }
}
