package com.github.cundream.springbootbuilding.rabbitmq.consumer;

import com.github.cundream.springbootbuilding.rabbitmq.consts.RabbitConst;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className: com.github.cundream.springbootbuilding.common.rabbitmq.consumer-> ReceiveDealyConsumer
 * @description:
 * @author: 李村
 * @createDate:
 */
@Slf4j
@RabbitListener(queuesToDeclare = @Queue(RabbitConst.DELAY_QUEUE))
@Component
public class ReceiveDealyHandler {
    @RabbitHandler
    public void directHandlerManualAck(Object object, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("直接队列1，手动ACK，接收消息：{}", object.toString());
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
