package com.cloud.chatbackend.responses;

import com.cloud.chatbackend.entities.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageResponse {
    public String content;
    public Date sentAt;
    public String sender;

    MessageResponse(Message message) {
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
        this.sender = message.getSender().getUsername();
    }
}
