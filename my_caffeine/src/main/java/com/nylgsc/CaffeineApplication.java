package com.nylgsc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching   //启用缓存
public class CaffeineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaffeineApplication.class,args);
    }
}
