package com.spu.TourismApp.ExceptionHandling.CustomExceptions;

public class DuplicatedResourceException extends RuntimeException{

    public DuplicatedResourceException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public DuplicatedResourceException(String exceptionMessage, Throwable exceptionCause) {
        super(exceptionMessage, exceptionCause);
    }

}