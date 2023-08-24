package com.cloud.chatbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Entity
@Getter
@Setter
public class Message extends AbstractPersistable<Long> {
    private String content;
    private Date sentAt;

    @ManyToOne
    private User sender;
    @ManyToOne
    private Conversation conversation;
}
