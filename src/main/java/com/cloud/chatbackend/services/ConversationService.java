package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.exceptions.BadRequestException;
import com.cloud.chatbackend.exceptions.UnauthorizedException;
import com.cloud.chatbackend.repositories.ConversationRepository;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.StartConversationRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.responses.ConversationsResponse;
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

    public ConversationsResponse getAllConversations() {
        List<Conversation> conversations = conversationRepository.findAll();
        return ConversationsResponse.builder()
                .success(true)
                .message("Conversations fetched")
                .conversations(conversations)
                .build();
    }

    public BasicResponse startConversation(StartConversationRequest startConversationRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return BasicResponse.basicResponseBuilder()
                    .success(false)
                    .message("The requesting user does not exist")
                    .build();
        }

        Optional<User> targetUser = userRepository.findByUsername(startConversationRequest.getUsername());
        if (targetUser.isEmpty()) {
            throw new UnauthorizedException("The target user does not exist");
        }

        Optional<Conversation> existingConversation = conversationRepository.findByParticipantsContainingAndParticipantsContains(user.get(), targetUser.get());
        if (existingConversation.isPresent()) {
            throw new BadRequestException("The conversation already exists");
        }

        Conversation conversation = new Conversation();

        conversation.getParticipants().add(user.get());
        conversation.getParticipants().add(targetUser.get());

        return BasicResponse.basicResponseBuilder()
                .success(true)
                .message("Conversation started")
                .build();
    }
}
