package com.cloud.chatbackend.controllers;

import com.cloud.chatbackend.requests.SendMessageRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public BasicResponse sendMessage(@RequestBody SendMessageRequest sendMessageRequest) {
        return messageService.sendMessage(sendMessageRequest);
    }

    @GetMapping("/{conversationId}")
    public BasicResponse getConversationMessages(@PathVariable("conversationId") String conversationId) {
        return messageService.getConversationMessages(conversationId);
    }
}
