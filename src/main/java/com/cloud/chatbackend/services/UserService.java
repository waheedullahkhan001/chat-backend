package com.cloud.chatbackend.services;

import com.cloud.chatbackend.entities.User;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public User getUserFromSecurityContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElse(null); // TODO: Throw exception if not found
    }
    public BasicResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isEmpty()) {
            // TODO: Use custom exception handling
            return BasicResponse.builder()
                    .success(false)
                    .message("User not found")
                    .build();
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            // TODO: Use custom exception handling
            return BasicResponse.builder()
                    .success(false)
                    .message("Incorrect password")
                    .build();
        }

        String token = jwtService.generateToken(user.get().getUsername(), user.get().getRole());

        return LoginResponse.builder()
                .success(true)
                .message("User logged in successfully")
                .token(token)
                .build();
    }

    public BasicResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            // TODO: Use custom exception handling
            return BasicResponse.builder()
                    .success(false)
                    .message("User already exists")
                    .build();
        }

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
