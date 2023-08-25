package com.cloud.chatbackend.responses;

import lombok.Builder;

@Builder
public class BasicResponse {
    public Boolean success;
    public String message;
}
