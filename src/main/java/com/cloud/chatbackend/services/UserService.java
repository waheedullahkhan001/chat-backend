package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.User;
import com.cloud.chatbackend.exceptions.BadRequestException;
import com.cloud.chatbackend.exceptions.NotFoundException;
import com.cloud.chatbackend.exceptions.UnauthorizedException;
import com.cloud.chatbackend.repositories.UserRepository;
import com.cloud.chatbackend.requests.LoginRequest;
import com.cloud.chatbackend.requests.RegisterRequest;
import com.cloud.chatbackend.responses.BasicResponse;
import com.cloud.chatbackend.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public User getUserFromContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UnauthorizedException("The requesting user does not exist"));
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(
                () -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        user.setLastLogin(new Date());
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return LoginResponse.builder()
                .success(true)
                .message("User logged in successfully")
                .token(token)
                .build();
    }

    public BasicResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User user = modelMapper.map(registerRequest, User.class);

        user.setMemberSince(new Date());
        user.setRole("USER");

        userRepository.save(user);

        return BasicResponse.basicResponseBuilder()
                .success(true)
                .message("User registered successfully")
                .build();
    }
}
