package com.github.cundream.springbootbuilding.service;

/**
 * @className: com.github.cundream.springbootbuilding.service-> RabbitMqService
 * @description:
 * @author: 李村 200900681
 * @createDate:
 */
public interface RabbitMqService {
    void sendDelayMessage(Object object, long millisecond);
}
