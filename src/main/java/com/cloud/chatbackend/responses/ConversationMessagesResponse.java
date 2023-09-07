package com.cloud.chatbackend.responses;

import com.cloud.chatbackend.entities.Message;
import com.cloud.chatbackend.responses.plain.MessageDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ConversationMessagesResponse extends BasicResponse {
    List<MessageDto> messages;

    @Builder
    ConversationMessagesResponse(boolean success, String message, List<Message> messages) {
        super(success, message);
        this.messages = messages.stream().map(MessageDto::new).toList();
    }
}
