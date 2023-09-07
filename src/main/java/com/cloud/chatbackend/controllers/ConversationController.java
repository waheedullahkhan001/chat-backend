package com.cloud.chatbackend.controllers;

import com.cloud.chatbackend.requests.StartConversationRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.services.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping("/all")
    public BasicResponse getAllConversations() {
        return conversationService.getAllConversations();
    }

    @PostMapping("/start")
    public BasicResponse startConversation(@Valid @RequestBody StartConversationRequest startConversationRequest) {
        return conversationService.startConversation(startConversationRequest);
    }
}
