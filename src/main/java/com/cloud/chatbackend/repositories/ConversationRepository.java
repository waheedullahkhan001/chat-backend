package com.cloud.chatbackend.repositories;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByParticipantsContainingAndParticipantsContains(User first, User second);
}
