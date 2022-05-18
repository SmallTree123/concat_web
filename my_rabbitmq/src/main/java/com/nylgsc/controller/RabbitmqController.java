package com.nylgsc.controller;


import com.nylgsc.myann.Student;
import com.nylgsc.producer.MyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/rabbit")
public class RabbitmqController {

    @Autowired
    MyProducer producer;


    @GetMapping(value = "/send")
    public void send(){
        producer.sendMessage();
    }

    @GetMapping(value = "/oom")
    public void oom(){
        int i =0;
        ArrayList<Object> arrayList = new ArrayList<>();
        while (true){
            System.out.println("oom--->"+(i++));
            arrayList.add(new Object());
        }
    }

    @PostMapping(value = "/get")
    public void get(@Valid @RequestBody Student student){
        System.out.println(student.toString());
    }

}
