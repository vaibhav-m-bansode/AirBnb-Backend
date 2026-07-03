package com.example.airbnb.Advice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
public class ApiError {
    private String message;
    private HttpStatus status;

    public ApiError(String localizedMessage, HttpStatus httpStatus) {
    }
}
