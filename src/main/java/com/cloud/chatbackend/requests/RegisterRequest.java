package com.cloud.chatbackend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String displayName;
    private String username;
    private String password;
}
