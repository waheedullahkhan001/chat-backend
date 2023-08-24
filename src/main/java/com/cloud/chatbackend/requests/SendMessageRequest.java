package com.cloud.chatbackend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {
    private Long conversationId;
    private String message;
}
