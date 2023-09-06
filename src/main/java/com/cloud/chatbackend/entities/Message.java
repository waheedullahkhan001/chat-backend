package com.cloud.chatbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Entity
@Getter
@Setter
public class Message extends AbstractPersistable<Long> {
    @Column(nullable = false, length = 1024)
    private String content;
    @Column(nullable = false)
    private Date sentAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User sender;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Conversation conversation;
}
