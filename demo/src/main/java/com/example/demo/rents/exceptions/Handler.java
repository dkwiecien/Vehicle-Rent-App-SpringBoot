package com.example.demo.rents.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionPayload> handleBadRequestException(final BadRequestException e) {
        return new ResponseEntity<>(new ExceptionPayload(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}
