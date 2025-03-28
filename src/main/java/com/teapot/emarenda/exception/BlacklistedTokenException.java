package com.teapot.emarenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BlacklistedTokenException extends RuntimeException {
    public BlacklistedTokenException(String message) {
        super(message);
    }
}
