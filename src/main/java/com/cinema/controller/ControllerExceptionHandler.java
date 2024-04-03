package com.cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cinema.ResponseWrapper;
import com.cinema.exception.ServiceException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice("com.cinema.controller")
@RequestMapping(produces = "application/json")
public class ControllerExceptionHandler {
	
	@ExceptionHandler({ ServiceException.class })
	@ResponseBody
	public ResponseWrapper<?> handleServiceException(ServiceException exception, HttpServletResponse response) {
		HttpStatus httpStatus = mapServiceResponseCodeToHttpStatus(exception);
		response.setStatus(httpStatus.value());
		return ResponseWrapper.failed(exception.getErrorMessage());
	}
	
	private static HttpStatus mapServiceResponseCodeToHttpStatus(ServiceException e) {
		return switch (e.getServiceResponseCode()) {
			case ERROR_NOT_FOUND -> HttpStatus.NOT_FOUND;
			case ERROR_DUPLICATE_ENTITY, ERROR_BAD_REQUEST, ERROR_APPLICATIVE, ERROR_INVALID_INPUT -> HttpStatus.BAD_REQUEST;
			default -> HttpStatus.INTERNAL_SERVER_ERROR;
		};
	}
}


