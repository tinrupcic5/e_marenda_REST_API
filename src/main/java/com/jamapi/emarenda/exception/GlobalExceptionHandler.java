package com.jamapi.emarenda.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IPErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IPErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessages, ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<IPErrorResponse> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.", ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<IPErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, "You do not have permission to perform this action.", ex);
    }

    @ExceptionHandler(BlacklistedTokenException.class)
    public ResponseEntity<IPErrorResponse> handleBlacklistedTokenException(BlacklistedTokenException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
    }

    @ExceptionHandler(CsvProcessingException.class)
    public ResponseEntity<IPErrorResponse> handleCsvProcessingException(CsvProcessingException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "CSV Processing Error: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<IPErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "User not found: " + ex.getMessage(), ex);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<IPErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Token has expired. Please log in again.", ex);
    }

    @ExceptionHandler(IPExpiredJwtException.class)
    public ResponseEntity<IPErrorResponse> handleIPExpiredJwtException(IPExpiredJwtException ex) {
        LOGGER.error("IPExpiredJwtException caught: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<IPErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "The requested resource was not found.", ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<IPErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }


    private String formatStackTraceElement(StackTraceElement element) {
        return String.format(
                "{className: \"%s\", methodName: \"%s\", fileName: \"%s\", lineNumber: %d}",
                element.getClassName(), element.getMethodName(), element.getFileName(), element.getLineNumber()
        );
    }

    private ResponseEntity<IPErrorResponse> buildErrorResponse(HttpStatus status, String message, Throwable ex) {
        List<String> stackTrace = Arrays.stream(ex.getStackTrace())
                .limit(6)
                .map(this::formatStackTraceElement)
                .collect(Collectors.toList());

        IPErrorResponse response = new IPErrorResponse(message, Instant.now().toString(), status, stackTrace);
        return ResponseEntity.status(status).body(response);
    }
}
