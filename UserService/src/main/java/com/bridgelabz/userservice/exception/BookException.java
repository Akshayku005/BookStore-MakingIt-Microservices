package com.bridgelabz.userservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

public @Data class BookException extends RuntimeException{
 private String message;
 private HttpStatus httpStatus;

	public BookException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}
	public BookException(String message) {
		super(message);
	}
}
