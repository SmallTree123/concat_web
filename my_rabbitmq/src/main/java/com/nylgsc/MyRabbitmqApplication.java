package com.nylgsc;

import com.nylgsc.producer.MyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRabbitmqApplication.class,args);
    }
}
