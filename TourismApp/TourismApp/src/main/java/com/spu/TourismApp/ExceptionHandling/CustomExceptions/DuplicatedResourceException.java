package com.spu.TourismApp.ExceptionHandling.CustomExceptions;

// Custom exception class for handling duplicated resource errors.
public class DuplicatedResourceException extends RuntimeException {

    // Constructor that takes only the exception message.
    // This constructor is used when you want to throw an exception with a specific error message.
    public DuplicatedResourceException(String exceptionMessage) {
        super(exceptionMessage); // Calls the parent RuntimeException constructor with the error message.
    }

    // Constructor that takes both an exception message and the cause (another throwable).
    // This constructor is useful when you want to provide more context about the error,
    // such as the original exception that caused the duplication error.
    public DuplicatedResourceException(String exceptionMessage, Throwable exceptionCause) {
        super(exceptionMessage, exceptionCause); // Passes both message and cause to the parent class.
    }
}
