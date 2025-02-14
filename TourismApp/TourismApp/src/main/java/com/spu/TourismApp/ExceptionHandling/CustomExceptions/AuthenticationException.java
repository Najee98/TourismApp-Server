package com.spu.TourismApp.ExceptionHandling.CustomExceptions;

// Custom exception class to handle authentication-related errors.
public class AuthenticationException extends RuntimeException {

    // Constructor that takes an exception message.
    // This constructor is used when you only need to provide a message describing the error.
    public AuthenticationException(String exceptionMessage) {
        super(exceptionMessage); // Passes the message to the parent RuntimeException class.
    }

    // Constructor that takes both an exception message and a cause (another throwable).
    // This constructor is useful when you want to provide more context, such as the original exception
    // that caused the authentication failure (e.g., an invalid token).
    public AuthenticationException(String exceptionMessage, Throwable exceptionCause) {
        super(exceptionMessage, exceptionCause); // Passes the message and cause to the parent class.
    }
}

