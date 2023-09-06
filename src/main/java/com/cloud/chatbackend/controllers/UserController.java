package com.cloud.chatbackend.controllers;

import com.cloud.chatbackend.requests.LoginRequest;
import com.cloud.chatbackend.requests.RegisterRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public BasicResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }


    @PostMapping("/register")
    public BasicResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}
