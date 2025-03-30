package com.example.vinemas_server.adapter.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> buildErrorResponse(HttpStatus status, String message, Object details, String path) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        errorResponse.put("details", details);
        errorResponse.put("path", path);
        return errorResponse;
    }

    /**
     * Xử lý lỗi validation khi request chứa dữ liệu không hợp lệ.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<Map<String, String>> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("field", fieldError.getField());
                    error.put("message", fieldError.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation error", validationErrors, request.getRequestURI()),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Xử lý lỗi TransactionSystemException (lỗi giao dịch JPA)
     */
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, Object>> handleTransactionException(TransactionSystemException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Transaction error", ex.getMostSpecificCause().getMessage(), request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Xử lý lỗi NotFoundException (trả về 404 nếu dữ liệu không tồn tại).
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null, request.getRequestURI()),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Xử lý lỗi chung (lỗi hệ thống không xác định).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
