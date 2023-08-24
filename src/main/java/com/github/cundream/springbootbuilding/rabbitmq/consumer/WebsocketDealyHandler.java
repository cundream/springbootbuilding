package com.github.cundream.springbootbuilding.rabbitmq.consumer;

import com.github.cundream.springbootbuilding.rabbitmq.consts.RabbitConst;
import com.github.cundream.springbootbuilding.service.WebsocketChatService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className: com.github.cundream.springbootbuilding.rabbitmq.consumer-> WebsocketDealyHandler
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-09 18:07
 */

@Slf4j
@RabbitListener(queuesToDeclare = @Queue(RabbitConst.MSG_TOPIC_QUEUE))
@Component
public class WebsocketDealyHandler {

    @Autowired
    private WebsocketChatService chatService;
    @RabbitHandler
    public void directHandlerManualAck(Object object, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body = message.getBody();
            String msg = new String(body);
            System.out.println("rabbitmq收到消息 : " +msg);
            Boolean sendToWebsocket = chatService.sendMsg(msg);

            if (sendToWebsocket){
                System.out.println("消息处理成功！ 已经推送到websocket！");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费

            }

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
