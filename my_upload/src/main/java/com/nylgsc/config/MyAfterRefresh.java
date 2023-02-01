package com.nylgsc.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyAfterRefresh extends SpringApplication {

    @Override
    protected void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args) {
        System.out.println("MyAfterRefresh------自定义刷新上下文后置处理");
        super.afterRefresh(context, args);
    }
}
