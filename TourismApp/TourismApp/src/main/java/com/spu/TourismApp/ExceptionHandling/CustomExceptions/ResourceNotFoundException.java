package com.spu.TourismApp.ExceptionHandling.CustomExceptions;

// Custom exception class to handle scenarios where a requested resource is not found.
public class ResourceNotFoundException extends RuntimeException {

    // Constructor that accepts only the exception message.
    // This constructor is used when an error occurs, and you want to throw a specific message about a missing resource.
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage); // Passes the message to the parent RuntimeException constructor.
    }

    // Constructor that accepts both the exception message and a cause (another throwable).
    // This constructor allows you to throw the exception along with the original exception that led to the resource not being found.
    public ResourceNotFoundException(String exceptionMessage, Throwable exceptionCause) {
        super(exceptionMessage, exceptionCause); // Passes both message and cause to the parent class.
    }
}

