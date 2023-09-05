package com.cloud.chatbackend.exceptions;

import com.cloud.chatbackend.responses.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
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

//     TODO: Uncomment when validation is done
/*
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<BasicResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body(
                BasicResponse.basicResponseBuilder()
                        .success(false)
                        .message(e.getAllErrors().get(0).getDefaultMessage())
                        .build()
        );
    }
*/

}
