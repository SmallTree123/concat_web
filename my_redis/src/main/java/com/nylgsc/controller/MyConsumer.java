package com.nylgsc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class MyConsumer {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue(QueueConstant.FIRST_RABBITMQ_MESSAGE))
    public void handleMessage(Channel channel,Message message) throws IOException {

        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        //手动签收
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("接收到的消息为："+msg+"==消息id为："+messageId);
        } catch (IOException e) {
            //是否是重新回到队列的消息
            //这里有一个问题就是，代码错误的话回无限的重新接受消息，从而是cup急剧上升
            System.out.println("消息即将再次返回队列处理...");
            //b：是否允许多条处理  b1：是否重新回到队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            //channel对象重新发送消息到队尾
            channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                    message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
                    JSON.toJSONBytes(msg));
        }

        String messageIdRedis = redisTemplate.opsForValue().get("messageId");
        if(messageId.equals(messageIdRedis)){
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String email = jsonObject.getString("message");
        redisTemplate.opsForValue().set("messageId",messageId);
    }


}
