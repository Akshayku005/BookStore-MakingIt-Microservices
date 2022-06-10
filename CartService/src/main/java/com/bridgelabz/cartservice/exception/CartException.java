package com.bridgelabz.cartservice.exception;

import lombok.Data;

public class CartException extends RuntimeException {
    public CartException(String message) {
        super(message);
    }
}
