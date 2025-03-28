package com.teapot.emarenda.exception;

public class IPExpiredJwtException extends RuntimeException {

    public IPExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
