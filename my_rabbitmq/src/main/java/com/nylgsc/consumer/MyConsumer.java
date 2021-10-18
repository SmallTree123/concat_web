package com.nylgsc.consumer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.nylgsc.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyConsumer {


    @RabbitListener(queuesToDeclare = @Queue(QueueConstant.FIRST_RABBITMQ_MESSAGE))
    public void handleMessage(String message){
        System.out.println("------------消费者接收到消息---------------");
        List<Integer> list  = (List<Integer>) JSONObject.parse(message);
        System.out.println(list.toString());
    }


}
