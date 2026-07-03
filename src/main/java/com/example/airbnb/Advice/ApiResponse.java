package com.example.airbnb.Advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime dateTime;
    private T data;
    private ApiError error;

    ApiResponse() {
        this.dateTime = LocalDateTime.now();
    }

    ApiResponse(T data) {
        this.dateTime = LocalDateTime.now();
        this.data = data;
    }

    ApiResponse(ApiError error) {
        this.dateTime = LocalDateTime.now();
        this.error = error;
    }
}
