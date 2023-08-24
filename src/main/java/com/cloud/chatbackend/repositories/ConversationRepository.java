package com.cloud.chatbackend.repositories;

import com.cloud.chatbackend.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
