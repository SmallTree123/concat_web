package com.nylgsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    int i = 0;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @RabbitListener(queuesToDeclare = @Queue(QueueConstant.FIRST_RABBITMQ_MESSAGE),containerFactory = "customContainerFactory")
//    public void handleMessage(Channel channel,Message message) throws IOException {
//
//        String messageId = message.getMessageProperties().getMessageId();
//
//        String messageIdRedis = redisTemplate.opsForValue().get(messageId);
//        //如果在redis中存的有的话，那就直接返回，不需要消费
//        if(messageId.equals(messageIdRedis)){
//            return;
//        }
//        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
//        //手动签收
//        try {
////            int a = 1/0;
//            //手动确认接收到消息
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//            System.out.println();
//            System.out.println("当前消费线程是--------->"+Thread.currentThread().getName()+"=======消息id为："+messageId);
//        } catch (Exception e) {
//            /**
//             * 否是重新回到队列的消息
//             * 这里有一个问题就是，代码错误的话回无限的重新接受消息，从而是cup急剧上升
//             */
//            //b：是否允许多条处理  b1：是否重新回到队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            System.out.println("消息即将再次返回队列处理..."+i);
//
//            /**
//             * 将失败的消息拒绝之后，重新发送到队列的尾部，这样可以保证其他的正常的消息是可以被处理的。
//             */
//            //channel对象重新发送消息到队尾
////            channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
////                    message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
////                    JSON.toJSONBytes(msg));
//        }
//
//        redisTemplate.opsForValue().set(messageId,messageId);
//    }

    public static void main(String[] args) throws Exception {

    }


}
