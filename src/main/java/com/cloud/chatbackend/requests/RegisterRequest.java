package com.cloud.chatbackend.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    @Min(value = 3, message = "Display name must be at least 3 characters long")
    @Max(value = 40, message = "Display name must be at most 40 characters long")
    private String displayName;
    @Min(value = 3, message = "Username must be at least 3 characters long")
    @Max(value = 20, message = "Username must be at most 20 characters long")
    private String username;
    @Min(value = 6, message = "Password must be at least 6 characters long")
    @Max(value = 20, message = "Password must be at most 20 characters long")
    private String password;
}
