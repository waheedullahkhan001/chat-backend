package com.cloud.chatbackend.services;

import com.cloud.chatbackend.requests.SendMessageRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public BasicResponse sendMessage(SendMessageRequest sendMessageRequest) {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }

    public BasicResponse getConversationMessages(String conversationId) {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }
}
