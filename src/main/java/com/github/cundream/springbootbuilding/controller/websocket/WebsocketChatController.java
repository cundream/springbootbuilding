package com.github.cundream.springbootbuilding.controller.websocket;

import cn.hutool.json.JSONUtil;
import com.github.cundream.springbootbuilding.vo.websocket.WebsocketChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @className: com.github.cundream.springbootbuilding.controller.websocket-> WebsocketChatController
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-22 11:56
 */

@Controller
@Slf4j
public class WebsocketChatController {

        @Autowired
        private RabbitTemplate rabbitTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;

        /**
         * 服务端推送给单人的接口
         * @param uid
         * @param content
         */
        @ResponseBody
        @GetMapping("/sendToOne")
        public void sendToOne(@RequestParam("uid") String uid, @RequestParam("content") String content ){

            WebsocketChatMessage chatMessage=new WebsocketChatMessage();
            chatMessage.setType(WebsocketChatMessage.MessageType.CHAT);
            chatMessage.setContent(content);
            chatMessage.setTo(uid);
            chatMessage.setSender("系统消息");
            rabbitTemplate.convertAndSend("topicWebSocketExchange","topic.public", JSONUtil.toJsonStr(chatMessage));

        }


    @ResponseBody
    @GetMapping("/getUserLison")
    public void getUserLison(){

        System.out.println("用户列表"+userRegistry.getUserCount());
        System.out.println("---"+userRegistry.getUsers().toString());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            log.info("用户" + i++ + "---" + user);
        }

    }


        /**
         * 接收 客户端传过来的消息 通过setSender和type 来判别时单发还是群发
         * @param chatMessage
         * @param principal
         */
        @MessageMapping("/chat.sendMessageTest")
        public void sendMessageTest(@Payload WebsocketChatMessage chatMessage, Principal principal) {
            try {

                String name = principal.getName();
                chatMessage.setSender(name);
                rabbitTemplate.convertAndSend("topicWebSocketExchange","topic.public", JSONUtil.toJsonStr(chatMessage));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        /**
         * 接收 客户端传过来的消息 上线消息
         * @param chatMessage
         */
        @MessageMapping("/chat.addUser")
        public void addUser(@Payload WebsocketChatMessage chatMessage) {

            System.out.println("有用户加入到了websocket 消息室" + chatMessage.getSender());
            try {

                System.out.println(chatMessage.toString());
                rabbitTemplate.convertAndSend("topicWebSocketExchange","topic.public",JSONUtil.toJsonStr(chatMessage));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }




    /**
     * 接收 客户端传过来的消息 上线消息
     * @param gggg
     */
    @MessageMapping("/greeting")
    public void greeting(String gggg) {
        System.out.println(gggg+"----------");
    }

    }
