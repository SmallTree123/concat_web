package com.nylgsc.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.dao.UserMapper;
import com.nylgsc.domain.User;
import com.nylgsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @DS("master")
    public void save(String name) {
        User user = new User();
        user.setName(name);
        userMapper.insert(user);
    }

    @Override
    @DS("slave")
    public List<User> find() {
        return userMapper.selectList(new QueryWrapper<>());
    }

}
