package com.nylgsc.controller;

import com.nylgsc.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class DemoController {

    @Value("${mySocket.myPwd}")
    public String myPwd;

    @Autowired
    private WebSocketServer webSocketServer;

    /**
      * 手机客户端请求接口
      * @param id 发生异常的设备ID
      * @param pwd 密码（实际开发记得加密）
      * @throws IOException
      */
     @GetMapping(value = "/onReceive/{id}")
     public void onReceive(@PathVariable("id") String id) throws Exception {
         //密码校验一致（这里举例，实际开发还要有个密码加密的校验的），则进行群发
         webSocketServer.broadCastInfo(id);

     }

}
