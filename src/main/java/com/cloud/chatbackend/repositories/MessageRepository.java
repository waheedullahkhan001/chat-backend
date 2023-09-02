package com.cloud.chatbackend.repositories;

import com.cloud.chatbackend.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
