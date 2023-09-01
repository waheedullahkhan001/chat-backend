package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.repositories.ConversationRepository;
import com.cloud.chatbackend.repositories.MessageRepository;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.SendMessageRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    public BasicResponse sendMessage(SendMessageRequest sendMessageRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return BasicResponse.builder()
                    .success(false)
                    .message("The requesting user does not exist")
                    .build();
        }

        Optional<Conversation> conversation = conversationRepository.findById(sendMessageRequest.getConversationId());
        if (conversation.isEmpty()) {
            return BasicResponse.builder()
                    .success(false)
                    .message("Conversation not found")
                    .build();
        }

        if (!conversation.get().getParticipants().contains(user.get())) {
            return BasicResponse.builder()
                    .success(false)
                    .message("The requesting user is not a participant of the conversation")
                    .build();
        }

        // TODO: Implement message sending logic
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }

    public BasicResponse getConversationMessages(String conversationId) {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }
}
