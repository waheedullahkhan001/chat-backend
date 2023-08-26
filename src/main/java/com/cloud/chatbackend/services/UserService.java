package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.LoginRequest;
import com.cloud.chatbackend.requests.RegisterRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public BasicResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        String token = jwtService.generateToken(user.get().getUsername(), user.get().getRole());

        return LoginResponse.builder()
                .success(true)
                .message("User logged in successfully")
                .token(token)
                .build();
    }

    public BasicResponse register(RegisterRequest registerRequest) {
        userRepository.findByUsername(registerRequest.getUsername()).ifPresent(user -> {
            throw new RuntimeException("User already exists");
        });

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User user = modelMapper.map(registerRequest, User.class);

        user.setMemberSince(new Date());
        user.setRole("USER");

        userRepository.save(user);

        return BasicResponse.builder()
                .success(true)
                .message("User registered successfully")
                .build();
    }
}
