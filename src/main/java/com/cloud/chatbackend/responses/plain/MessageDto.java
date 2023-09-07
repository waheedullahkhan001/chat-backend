package com.cloud.chatbackend.responses.plain;

import com.cloud.chatbackend.entities.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {
    public String content;
    public Date sentAt;
    public String sender;

    public MessageDto(Message message) {
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
        this.sender = message.getSender().getUsername();
    }
}
