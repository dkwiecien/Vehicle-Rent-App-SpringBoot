package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionPayload(
        HttpStatus status,
        String message,
        LocalDateTime timeStamp
) {
}
