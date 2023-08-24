package com.cloud.chatbackend.repositories;

import com.cloud.chatbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
