package com.example.taskrafallup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GitHubNameNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptableException() {
        final ErrorResponse from = ErrorResponse.from(HttpStatus.NOT_ACCEPTABLE, StringHolder.NOT_ACCEPTED_RESPONSE);
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(from);
    }

    @ExceptionHandler(GitHubNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponse> gitNameNotFoundHandler(final GitHubNameNotFoundException ex) {
        final ErrorResponse from = ErrorResponse.from(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity
                .status(from.status())
                .body(from);
    }
}

