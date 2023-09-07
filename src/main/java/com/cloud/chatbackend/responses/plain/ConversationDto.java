package com.cloud.chatbackend.responses.plain;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {
    public Long id;
    public List<String> participants;

    public ConversationDto(Conversation conversation) {
        this.id = conversation.getId();
        this.participants = conversation.getParticipants().stream().map(User::getUsername).toList();
    }
}
