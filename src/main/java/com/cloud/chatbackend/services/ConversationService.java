package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.StartConversationRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final UserRepository userRepository;

    public BasicResponse getAllConversations() {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }

    public BasicResponse startConversation(StartConversationRequest startConversationRequest) {

        // TODO: Move and improve this logic somewhere else
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);

        Optional<User> targetUser = userRepository.findByUsername(startConversationRequest.getUsername());
        if (targetUser.isEmpty()) {
            return BasicResponse.builder()
                    .success(false)
                    .message("User not found")
                    .build();
        }

        // TODO: Check if the already conversation exists

        Conversation conversation = new Conversation();

        conversation.getParticipants().add(user.get());
        conversation.getParticipants().add(targetUser.get());

        return BasicResponse.builder()
                .success(true)
                .message("Conversation started")
                .build();
    }
}
