package com.spu.TourismApp.ExceptionHandling.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public ResourceNotFoundException(String exceptionMessage, Throwable exceptionCause) {
        super(exceptionMessage, exceptionCause);
    }
}
