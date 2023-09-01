package com.cloud.chatbackend.responses;

import com.cloud.chatbackend.entities.Conversation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ConversationsResponse extends BasicResponse{
    private final List<Conversation> conversations;

    @Builder
    public ConversationsResponse(boolean success, String message, List<Conversation> conversations) {
        super(success, message);
        this.conversations = conversations;
    }
}
