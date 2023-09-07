package com.cloud.chatbackend.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {
    @NotBlank
    @Min(value = 1, message = "Conversation ID must be at least 1")
    @Max(value = Long.MAX_VALUE, message = "Conversation ID must be at most " + Long.MAX_VALUE)
    private Long conversationId;

    @NotBlank
    @Size(min = 1, max = 500, message = "Message content must be at least 1 characters long and at most 500 characters long")
    private String content;
}
