package com.nylgsc.service;

import api.UserService;
import bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

@DubboService
@Component
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public User getUserInfo(long userId) {
        System.out.println("消费端远程调用成功。。。"+userId);
        return new User(userId, "userName" + userId , " --->>>>response from remote RPC provider:" + RpcContext.getContext().getLocalAddress());
    }
}
