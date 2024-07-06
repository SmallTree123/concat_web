package com.nylgsc.service;


import com.nylgsc.domain.User;

import java.util.List;

public interface UserService {

    void save(String name);

    List<User> find();

}
