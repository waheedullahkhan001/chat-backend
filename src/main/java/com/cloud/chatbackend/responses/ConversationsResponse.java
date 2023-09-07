package com.cloud.chatbackend.responses;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.responses.plain.ConversationDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ConversationsResponse extends BasicResponse {
    private final List<ConversationDto> conversations;

    @Builder
    public ConversationsResponse(boolean success, String message, List<Conversation> conversations) {
        super(success, message);
        this.conversations = conversations.stream().map(ConversationDto::new).toList();
    }
}
