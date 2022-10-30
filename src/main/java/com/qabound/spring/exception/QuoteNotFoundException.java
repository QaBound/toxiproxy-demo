package com.qabound.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Quote Not Found")
public class QuoteNotFoundException extends Exception {

	public QuoteNotFoundException(String message) {
		super(message);
	}
}
