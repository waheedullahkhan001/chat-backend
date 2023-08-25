package com.cloud.chatbackend.services;

import com.cloud.chatbackend.requests.StartConversationRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    public BasicResponse getAllConversations() {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }

    public BasicResponse startConversation(StartConversationRequest startConversationRequest) {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }
}
