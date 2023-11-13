package com.github.cundream.springbootbuilding.vo.websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: com.github.cundream.springbootbuilding.vo.websocket-> ChatMessage
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-08-17 17:30
 */
@Data
public class WebsocketChatMessage implements Serializable {
    private MessageType type;
    private String content;
    private String sender;
    private String to;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}
