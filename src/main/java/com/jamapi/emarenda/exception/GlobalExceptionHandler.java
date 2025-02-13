package com.jamapi.emarenda.exception;

import com.jamapi.emarenda.domain.response_message.ResponseMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.error("IllegalArgumentException: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        LOGGER.error("Validation Error: {}", errorMessages, ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessages);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGenericException(Exception ex) {
        LOGGER.error("Unexpected Error: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage> handleAccessDeniedException(AccessDeniedException ex) {
        LOGGER.warn("Access Denied: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.FORBIDDEN, "You do not have permission to perform this action.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BlacklistedTokenException.class)
    public ResponseEntity<ResponseMessage> handleBlacklistedTokenException(BlacklistedTokenException ex) {
        LOGGER.warn("Blacklisted Token: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(CsvProcessingException.class)
    public ResponseEntity<ResponseMessage> handleCsvProcessingException(CsvProcessingException ex) {
        LOGGER.error("CSV Processing Error: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(
                HttpStatus.BAD_REQUEST,
                "CSV Processing Error: " + ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleUserNotFoundException(UserNotFoundException ex) {
        LOGGER.warn("User Not Found: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(
                HttpStatus.NOT_FOUND,
                "User not found: " + ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseMessage> handleExpiredJwtException(ExpiredJwtException ex) {
        LOGGER.warn("Expired JWT Token: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.UNAUTHORIZED, "Token has expired. Please log in again.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseMessage> handleNoSuchElementException(NoSuchElementException ex) {
        LOGGER.warn("No Such Element: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.NOT_FOUND, "The requested resource was not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseMessage> handleIllegalStateException(IllegalStateException ex) {
        LOGGER.error("Illegal State: {}", ex.getMessage(), ex);
        ResponseMessage response = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
