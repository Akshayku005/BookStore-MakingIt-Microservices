package com.bridgelabz.orderservice.exception;

import org.springframework.http.HttpStatus;

public class OrderException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public OrderException( HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public OrderException(String message) {
        super(message);
    }
}
