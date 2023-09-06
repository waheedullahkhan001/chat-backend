package com.cloud.chatbackend.exceptions;

import com.cloud.chatbackend.responses.BasicResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<BasicResponse> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(400).body(
                BasicResponse.basicResponseBuilder()
                        .success(false)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    protected ResponseEntity<BasicResponse> handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(401).body(
                BasicResponse.basicResponseBuilder()
                        .success(false)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<BasicResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(
                BasicResponse.basicResponseBuilder()
                        .success(false)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<BasicResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(400).body(
                BasicResponse.basicResponseBuilder()
                        .success(false)
                        .message(objectMapper.writeValueAsString(errors))
                        .build()
        );
    }

}
