package com.nylgsc;


import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableNacosDiscovery
public class MyRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyRedisApplication.class,args);
    }
}
