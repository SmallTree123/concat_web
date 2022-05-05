package com.nylgsc.controller;

import com.nylgsc.entity.Address;
import com.nylgsc.entity.User;
import com.nylgsc.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressContoller {

    @Autowired
    private AddressMapper addressMapper;

    @RequestMapping("/save")
    public String save(Address address){
        addressMapper.insert(address);
        return "保存成功";
    }

    @RequestMapping("/get")
    public String get(Address address){
        return addressMapper.selectById(address.getId()).toString();
    }

    @RequestMapping("/list")
    public List<User> list(){
        return addressMapper.selectAll();
    }
}
