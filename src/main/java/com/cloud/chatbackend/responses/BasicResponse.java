package com.cloud.chatbackend.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BasicResponse {
    private Boolean success;
    private String message;
}
