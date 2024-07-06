/**
 * Copyright © 2020 国网信通产业集团. All rights reserved.
 *
 * @Title: RabbitmqConfig.java
 * @Prject: pms-engineering-management
 * @Package: com.sgcc.sgga.engineering.basedata.config
 * @author: chenhui
 * @date: 2020年1月6日 上午10:56:33
 * @version: V1.0
 */
package com.nylgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: RabbitmqConfig
 * @Description: RabbitmqConfig
 * @author: chenhui
 * @date: 2020年1月6日 上午10:56:33
 */
//@Configuration
public class RabbitmqConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    /**
     * 单一消费者
     * 声明交换机
     * 声明队列
     * 队列绑定交换机,指定routingKey
     * 并发数量
     *
     * @return
     */
//    public static final int DEFAULT_CONCURRENT = 10;
//
//    @Bean("customContainerFactory")
//    public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
//                                                                 ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
//        factory.setMaxConcurrentConsumers(DEFAULT_CONCURRENT);
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }

}
