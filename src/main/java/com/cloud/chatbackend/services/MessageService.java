package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.Message;
import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.exceptions.UnauthorizedException;
import com.cloud.chatbackend.repositories.ConversationRepository;
import com.cloud.chatbackend.repositories.MessageRepository;
import com.cloud.chatbackend.requests.SendMessageRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.responses.ConversationMessagesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserService userService;
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    public BasicResponse sendMessage(SendMessageRequest sendMessageRequest) {
        User user = userService.getUserFromContext();

        Optional<Conversation> conversation = conversationRepository.findById(sendMessageRequest.getConversationId());
        if (conversation.isEmpty() || !conversation.get().getParticipants().contains(user)) {
            throw new UnauthorizedException("The requesting user is not a participant of the conversation");
        }

        Message message = new Message();
        message.setContent(sendMessageRequest.getContent());
        message.setSentAt(new Date());
        message.setSender(user);
        message.setConversation(conversation.get());

        messageRepository.save(message);

        return BasicResponse.basicResponseBuilder()
                .success(true)
                .message("Message sent successfully")
                .build();
    }

    public ConversationMessagesResponse getConversationMessages(String conversationId) {
        User user = userService.getUserFromContext();

        Optional<Conversation> conversation = conversationRepository.findById(Long.parseLong(conversationId));
        if (conversation.isEmpty() || !conversation.get().getParticipants().contains(user)) {
            throw new UnauthorizedException("The requesting user is not a participant of the conversation");
        }

        return ConversationMessagesResponse.builder()
                .success(true)
                .message("Messages fetched")
                .messages(conversation.get().getMessages())
                .build();
    }
}
