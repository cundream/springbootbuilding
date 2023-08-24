package com.github.cundream.springbootbuilding.rabbitmq.consts;

import org.springframework.data.redis.listener.Topic;

/**
 * @className: com.github.cundream.springbootbuilding.common.rabbitmq-> RabbitConst
 * @description:
 * @author: 李村
 * @createDate:
 */
public class RabbitConst {
    /**
     * 交换机
     */
    public static final String DELAY_EXCHANGE = "delay_exchange";

    /**
     * 队列
     */
    public static final String DELAY_QUEUE = "delay_queue";

    /**
     * 路由
     */
    public static final String DELAY_KEY = "delay_key";


    //绑定键
    public final static String MSG_TOPIC_KEY = "topic.public";
    //队列
    public final static String MSG_TOPIC_QUEUE = "websocket.msg.topic.queue";

    public final static String TOPIC_WEB_SOCKET_EXCHANGE = "topicWebSocketExchange";


}
