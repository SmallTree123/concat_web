package com.nylgsc.producer;

import com.alibaba.fastjson.JSONObject;
import com.nylgsc.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i+1);
        }
        System.out.println("-------------生产者发送消息-------------"+list.toString());
        rabbitTemplate.convertAndSend(QueueConstant.FIRST_RABBITMQ_MESSAGE, JSONObject.toJSONString(list));
    }

}
