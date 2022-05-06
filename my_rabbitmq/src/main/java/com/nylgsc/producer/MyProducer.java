package com.nylgsc.producer;

import com.alibaba.fastjson.JSONObject;
import com.nylgsc.constant.QueueConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(){
        for (int i = 0; i < 20; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message","Java旅途"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String json = jsonObject.toJSONString();
            Message message = MessageBuilder.withBody(json.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("UTF-8").setMessageId(UUID.randomUUID()+"").build();
            System.out.println("生产者发送的内容--->"+message.toString());
            rabbitTemplate.convertAndSend(QueueConstant.FIRST_RABBITMQ_MESSAGE,message);
        }
    }

}
