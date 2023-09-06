package com.cloud.chatbackend.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {
    @Min(value = 1, message = "Conversation ID must be at least 1")
    @Max(value = Long.MAX_VALUE, message = "Conversation ID must be at most " + Long.MAX_VALUE)
    private Long conversationId;
    @NotBlank
    @Max(value = 1024, message = "Message content must be at most 1024 characters long")
    private String content;
}
