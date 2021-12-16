package com.nylgsc.controller;

import api.UserService;
import bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerUserController {

    @DubboReference(protocol = "dubbo", loadbalance = "random")
    private UserService userService;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        User user = userService.getUserInfo(id);
        System.out.println("从服务提供方获取到的数据是..."+user);
        return user;
    }
}

