package com.nylgsc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRedisController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/setValue")
    public void setRedisValue(){
        redisTemplate.opsForValue().set("such","你好啊");
    }

    @RequestMapping("/getValue")
    public void getRedisValue(){
        System.out.println(redisTemplate.opsForValue().get("such").toString());
    }
}
