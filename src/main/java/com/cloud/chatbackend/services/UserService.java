package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.LoginRequest;
import com.cloud.chatbackend.requests.RegisterRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public BasicResponse login(LoginRequest loginRequest) {
        return BasicResponse.builder()
                .success(false)
                .message("Not implemented yet")
                .build();
    }

    public BasicResponse register(RegisterRequest registerRequest) {
        userRepository.findByUsername(registerRequest.getUsername()).ifPresent(user -> {
            throw new RuntimeException("User already exists");
        });

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User user = modelMapper.map(registerRequest, User.class);

        user.setMemberSince(new Date());

        // TODO: Setup role

        return BasicResponse.builder()
                .success(true)
                .message("User registered successfully")
                .build();
    }
}
