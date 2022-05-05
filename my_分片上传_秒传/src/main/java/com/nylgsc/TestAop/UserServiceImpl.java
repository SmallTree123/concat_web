package com.nylgsc.TestAop;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    public void update(){
        System.out.println("update");
    }
}
