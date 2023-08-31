package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.repositories.ConversationRepository;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.StartConversationRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    public BasicResponse getAllConversations() {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }

    public BasicResponse startConversation(StartConversationRequest startConversationRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return BasicResponse.builder()
                    .success(false)
                    .message("The requesting does not exist")
                    .build();
        }

        Optional<User> targetUser = userRepository.findByUsername(startConversationRequest.getUsername());
        if (targetUser.isEmpty()) {
            return BasicResponse.builder()
                    .success(false)
                    .message("User not found")
                    .build();
        }

        Optional<Conversation> existingConversation = conversationRepository.findByParticipants(List.of(user.get(), targetUser.get()));
        if (existingConversation.isPresent()) {
            return BasicResponse.builder()
                    .success(false)
                    .message("Conversation already exists")
                    .build();
        }

        Conversation conversation = new Conversation();

        conversation.getParticipants().add(user.get());
        conversation.getParticipants().add(targetUser.get());

        return BasicResponse.builder()
                .success(true)
                .message("Conversation started")
                .build();
    }
}
