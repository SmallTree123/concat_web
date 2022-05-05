package com.nylgsc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nylgsc.mapper")
public class MyCatApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyCatApplication.class,args);
    }
}
