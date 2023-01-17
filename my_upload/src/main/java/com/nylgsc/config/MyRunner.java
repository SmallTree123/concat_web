package com.nylgsc.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner, CommandLineRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("我是实现了ApplicationRunner接口的自定义run方法");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("我是实现了CommandLineRunner接口的自定义run方法");
    }
}
