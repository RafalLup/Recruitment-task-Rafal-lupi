package com.example.taskrafallup.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status,
                            String message) {

    public static ErrorResponse from(final HttpStatus httpStatus, final String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }
}
