package com.github.cundream.springbootbuilding.service.impl;

import com.github.cundream.springbootbuilding.common.rabbitmq.RabbitConst;
import com.github.cundream.springbootbuilding.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: com.github.cundream.springbootbuilding.service.impl-> RabbitMqServiceImpl
 * @description:
 * @author:
 * @createDate:
 */
@Service
@Slf4j
public class RabbitMqServiceImpl implements RabbitMqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendDelayMessage(Object object, long millisecond) {
        this.rabbitTemplate.convertAndSend(
                RabbitConst.DELAY_EXCHANGE,
                RabbitConst.DELAY_KEY,
                object.toString(),
                message -> {
                    message.getMessageProperties().setHeader("x-delay", millisecond);
                    return message;
                }
        );

    }
}
