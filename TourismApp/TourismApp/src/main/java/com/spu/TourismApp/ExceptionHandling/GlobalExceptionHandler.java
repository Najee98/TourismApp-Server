package com.spu.TourismApp.ExceptionHandling;

import com.spu.TourismApp.ExceptionHandling.CustomExceptions.AuthenticationException;
import com.spu.TourismApp.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

// This class is a centralized exception handler that catches specific exceptions across the whole application.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles ResourceNotFoundException, which is thrown when a requested resource is not found.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        // Creating an ErrorResponse object with message, HTTP status, and description of the request that caused the error.
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), request.getDescription(false));
        // Returning the ErrorResponse wrapped in a ResponseEntity with a NOT_FOUND (404) status.
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handles AuthenticationException, which is thrown when authentication fails.
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e, WebRequest request) {
        // Creating a custom error message for authentication failure, setting the status to FORBIDDEN (403).
        ErrorResponse errorResponse = new ErrorResponse("Could not authenticate successfully.", HttpStatus.FORBIDDEN.value(), request.getDescription(false));
        // Returning the ErrorResponse with a FORBIDDEN status.
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    // Handles any other general exceptions that are not specifically handled above.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e, WebRequest request) {
        // Creating an ErrorResponse object for any unexpected errors, setting the status to INTERNAL_SERVER_ERROR (500).
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));
        // Returning the ErrorResponse wrapped in a ResponseEntity with an INTERNAL_SERVER_ERROR status.
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
