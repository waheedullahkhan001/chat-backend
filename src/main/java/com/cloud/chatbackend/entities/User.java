package com.cloud.chatbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;
import java.util.List;

@Entity(name = "`users`")
@Getter
@Setter
public class User extends AbstractPersistable<Long> {
    private String displayName;
    private String username;
    private String password;

    private Date lastLogin;
    private Date memberSince;

    private String role;

    @ManyToMany
    private List<Conversation> conversations;

    @OneToMany(mappedBy = "sender")
    private List<Message> messages;
}
