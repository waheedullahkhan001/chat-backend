package com.cloud.chatbackend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be at least 3 characters long and at most 20 characters long")
    private String username;

    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be at least 8 characters long and at most 20 characters long")
    private String password;
}
