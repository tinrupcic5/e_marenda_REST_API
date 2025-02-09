package com.jamapi.emarenda.exception;

import com.jamapi.emarenda.domain.response_message.ResponseMessage;
import io.jsonwebtoken.ExpiredJwtException;
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

    // Handle IllegalArgumentException (e.g., when username already exists)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseMessage response = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Handle MethodArgumentNotValidException (e.g., validation errors)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ResponseMessage response = new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessages);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Handle Generic Exception (catch-all for unexpected errors)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGenericException(Exception ex) {
        ResponseMessage response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Handle Access Denied (Unauthorized role)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage> handleAccessDeniedException(AccessDeniedException ex) {
        ResponseMessage response = new ResponseMessage(HttpStatus.FORBIDDEN, "You do not have permission to perform this action.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Handle Blacklisted Token
    @ExceptionHandler(BlacklistedTokenException.class)
    public ResponseEntity<ResponseMessage> handleBlacklistedTokenException(BlacklistedTokenException ex) {
        ResponseMessage response = new ResponseMessage(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Handle CSV Processing Errors
    @ExceptionHandler(CsvProcessingException.class)
    public ResponseEntity<ResponseMessage> handleCsvProcessingException(CsvProcessingException ex) {
        ResponseMessage response = new ResponseMessage(
                HttpStatus.BAD_REQUEST,
                "CSV Processing Error: " + ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Handle User not found Errors
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleUserNotFoundException(UserNotFoundException ex) {
        ResponseMessage response = new ResponseMessage(
                HttpStatus.NOT_FOUND,
                "User not found: " + ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Handle Expired JWT Token Exception
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseMessage> handleExpiredJwtException(ExpiredJwtException ex) {
        ResponseMessage response = new ResponseMessage(HttpStatus.UNAUTHORIZED, "Token has expired. Please log in again.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Handle NoSuchElement Exception
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseMessage> handleNoSuchElementException(NoSuchElementException ex) {
        ResponseMessage response = new ResponseMessage(HttpStatus.NOT_FOUND, "The requested resource was not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
