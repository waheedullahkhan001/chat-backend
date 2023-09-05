package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.Message;
import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.exceptions.UnauthorizedException;
import com.cloud.chatbackend.repositories.ConversationRepository;
import com.cloud.chatbackend.repositories.MessageRepository;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.SendMessageRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.responses.ConversationMessagesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            throw new UnauthorizedException("The requesting user does not exist");
        }

        Optional<Conversation> conversation = conversationRepository.findById(sendMessageRequest.getConversationId());
        if (conversation.isEmpty() || !conversation.get().getParticipants().contains(user.get())) {
            throw new UnauthorizedException("The requesting user is not a participant of the conversation");
        }

        Message message = new Message();
        message.setContent(sendMessageRequest.getContent());
        message.setSentAt(new Date());
        message.setSender(user.get());
        message.setConversation(conversation.get());

        messageRepository.save(message);

        return BasicResponse.basicResponseBuilder()
                .success(true)
                .message("Message sent successfully")
                .build();
    }

    public ConversationMessagesResponse getConversationMessages(String conversationId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UnauthorizedException("The requesting user does not exist");
        }

        Optional<Conversation> conversation = conversationRepository.findById(Long.parseLong(conversationId));
        if (conversation.isEmpty() || !conversation.get().getParticipants().contains(user.get())) {
            throw new UnauthorizedException("The requesting user is not a participant of the conversation");
        }

        return ConversationMessagesResponse.builder()
                .success(true)
                .message("Messages fetched")
                .messages(conversation.get().getMessages())
                .build();
    }
}
