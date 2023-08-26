package com.cloud.chatbackend.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse extends BasicResponse{
    private final String token;

    @Builder
    LoginResponse(boolean success, String message, String token) {
        super(success, message);
        this.token = token;
    }
}
