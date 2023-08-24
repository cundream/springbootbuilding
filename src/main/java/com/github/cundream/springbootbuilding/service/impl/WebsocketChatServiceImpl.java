package com.github.cundream.springbootbuilding.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.cundream.springbootbuilding.service.WebsocketChatService;
import com.github.cundream.springbootbuilding.vo.websocket.WebsocketChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * @className: com.github.cundream.springbootbuilding.service.impl-> WebsocketChatServiceImpl
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-17 17:32
 */
@Service
public class WebsocketChatServiceImpl implements WebsocketChatService {
    @Autowired
    //最终把消息推送到websocket的消息代理中去
    private SimpMessageSendingOperations simpMessageSendingOperations;
    @Override
    public boolean sendMsg(String msg) {
        try {
            JSONObject msgJson = JSONObject.parseObject(msg);
            //群发消息，通过消息内容的type区别，分别是聊天消息，上线消息，下线消息。都使用ConverAndSend方法推送到/topic/public
            if (msgJson.getString("to").equals("all") && msgJson.getString("type").equals(WebsocketChatMessage.MessageType.CHAT.toString())){
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            }else if (msgJson.getString("to").equals("all") && msgJson.getString("type").equals(WebsocketChatMessage.MessageType.JOIN.toString())) {
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            }else if(msgJson.getString("to").equals("all") &&  msgJson.getString("type").equals(WebsocketChatMessage.MessageType.LEAVE.toString())) {
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            }else if (!msgJson.getString("to").equals("all") &&  msgJson.getString("type").equals(WebsocketChatMessage.MessageType.CHAT.toString())){
                //都使用ConverAndSend方法推送给单独用户，用户消息识别 来自 "to"，因为使用toUser最后会增加上用户
                simpMessageSendingOperations.convertAndSendToUser(msgJson.getString("to"),"/topic/msg", msgJson);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
