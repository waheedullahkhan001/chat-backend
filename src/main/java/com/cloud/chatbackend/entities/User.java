package com.cloud.chatbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "`users`")
@Getter
@Setter
public class User extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String displayName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    private Date lastLogin;
    @Column(nullable = false)
    private Date memberSince;

    @Column(nullable = false)
    private String role;

    @ManyToMany
    private List<Conversation> conversations = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();
}
