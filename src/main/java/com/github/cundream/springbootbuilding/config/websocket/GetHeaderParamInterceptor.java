package com.github.cundream.springbootbuilding.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

/**
 * @className: com.github.cundream.springbootbuilding.config.websocket-> GetHeaderParamInterceptor
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-09 16:50
 */
@Component
@Slf4j
public class GetHeaderParamInterceptor extends ChannelInterceptorAdapter {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("----"+message.toString());
        log.info("GetHeaderParamInterceptor--接收消息--preSend");
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        log.info("GetHeaderParamInterceptor--接收消息--accessor" +accessor.toString());
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                Object name = ((Map) raw).get("username"); //取出客户端携带的参数
                System.out.println(name);
                if (name instanceof LinkedList) {
                    // 设置当前访问的认证用户
                    accessor.setUser(new UserPrincipal(((LinkedList) name).get(0).toString()));
                }
            }
        }
        return message;
    }
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("GetHeaderParamInterceptor--接收消息POst--postSend");
    }
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
        log.info("GetHeaderParamInterceptor--在消息发送后--afterSendCompletion");
    }
    @Override
    public boolean preReceive(MessageChannel channel) {

        log.info("GetHeaderParamInterceptor----preReceive");
        return true;
    }
    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        log.info("GetHeaderParamInterceptor----postReceive");
        return message;
    }
    @Override
    public void afterReceiveCompletion(@Nullable Message<?> message, MessageChannel channel, @Nullable Exception ex) {
        log.info("GetHeaderParamInterceptor----afterReceiveCompletion");
    }
}
