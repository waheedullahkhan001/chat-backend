package com.cloud.chatbackend.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Min(value = 3, message = "Username must be at least 3 characters long")
    @Max(value = 20, message = "Username must be at most 20 characters long")
    private String username;
    @Min(value = 6, message = "Password must be at least 6 characters long")
    @Max(value = 20, message = "Password must be at most 20 characters long")
    private String password;
}
