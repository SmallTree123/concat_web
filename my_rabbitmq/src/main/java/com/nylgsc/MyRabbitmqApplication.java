package com.nylgsc;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyRabbitmqApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MyRabbitmqApplication.class,args);
        String a = "页面变量";
        System.out.println(a.contains("页面"));
    }
}
