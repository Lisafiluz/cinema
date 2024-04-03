package com.cinema.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ServiceResponseCode {
	OK(1, "OK"),
	ERROR_INTERNAL_SERVER_ERROR(2, "Internal Server Error"),
	ERROR_NOT_FOUND(3, "Not Found"),
	ERROR_BAD_REQUEST(4, "Bad Request"),
	ERROR_DUPLICATE_ENTITY(5, "Duplicate entity"),
	ERROR_APPLICATIVE(6, "Failure"),
	ERROR_INVALID_INPUT(7, "Invalid Input");
	
	private final int code;
	private final String message;
	
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
