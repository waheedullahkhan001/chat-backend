package com.cloud.chatbackend.controllers;

import com.cloud.chatbackend.requests.SendMessageRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.services.MessageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public BasicResponse sendMessage(@Valid @RequestBody SendMessageRequest sendMessageRequest) {
        return messageService.sendMessage(sendMessageRequest);
    }

    @GetMapping("/{conversationId}")
    public BasicResponse getConversationMessages(
            @PathVariable("conversationId") @Min(1) @Max(Long.MAX_VALUE) Long conversationId
    ) {
        return messageService.getConversationMessages(conversationId);
    }
}
