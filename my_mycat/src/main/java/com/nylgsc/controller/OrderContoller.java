package com.nylgsc.controller;

import com.nylgsc.entity.Address;
import com.nylgsc.entity.Order;
import com.nylgsc.entity.User;
import com.nylgsc.mapper.AddressMapper;
import com.nylgsc.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderContoller {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/save")
    public String save(Order order){
        orderMapper.insert(order);
        return "保存成功";
    }

    @RequestMapping("/get")
    public String get(Order order){
        return orderMapper.selectById(order.getId()).toString();
    }

    @RequestMapping("/list")
    public List<User> list(){
        return orderMapper.selectAll();
    }
}
