package com.nylgsc.controller;

import com.nylgsc.entity.User;
import com.nylgsc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MyCatContoller {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/save")
    public String save(User user){
        userMapper.insert(user);
        return "保存成功";
    }

    @RequestMapping("/get")
    public String get(User user){
        return userMapper.selectById(user.getId()).toString();
    }

    @RequestMapping("/list")
    public List<User> list(){
        return userMapper.selectAll();
    }
}
