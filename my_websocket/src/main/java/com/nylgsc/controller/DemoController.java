package com.nylgsc.controller;

import com.nylgsc.server.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class DemoController {

    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public String page(){
        return "websocket.html";
    }

    /**
     * 给指定用户发送消息
     * @param message
     * @param toUserId
     * @return
     * @throws IOException
     */
    @RequestMapping("/push/{toUserId}/{message}")
    public ResponseEntity<String> pushToWeb(@PathVariable String message, @PathVariable String toUserId) throws IOException {
        int count = 10;
        while (count>0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebSocketServer.sendInfo(message+"-->"+count,toUserId);
            count--;
        }
        return ResponseEntity.ok("消息发送成功...");
    }
}
