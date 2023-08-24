package com.cloud.chatbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@Entity
@Getter
@Setter
public class Conversation extends AbstractPersistable<Long> {
    @ManyToMany
    private List<User> participants;
    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;
}
