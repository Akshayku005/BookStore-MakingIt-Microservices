package com.bridgelabz.orderservice.exception;


import org.springframework.http.HttpStatus;

public class BookException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public BookException( HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public BookException(String message) {
        super(message);
    }

}