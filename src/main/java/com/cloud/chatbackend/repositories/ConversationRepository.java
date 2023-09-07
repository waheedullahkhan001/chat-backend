package com.cloud.chatbackend.repositories;

import com.cloud.chatbackend.entities.Conversation;
import com.cloud.chatbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    // TODO: What is the difference between Containing and Contains?
    Optional<Conversation> findByParticipantsContainingAndParticipantsContains(User first, User second);
}
