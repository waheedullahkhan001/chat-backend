package com.cloud.chatbackend.responses;

import com.cloud.chatbackend.entities.Message;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ConversationMessagesResponse extends BasicResponse {
    List<Message> messages;

    @Builder
    ConversationMessagesResponse(boolean success, String message, List<Message> messages) {
        super(success, message);
        this.messages = messages;
    }
}
