package com.cinema.exception;

import com.cinema.enums.ServiceResponseCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServiceException extends Exception {
	
	private ServiceResponseCode serviceResponseCode;
	private String errorMessage;
	private Throwable throwable;
	
	public ServiceException(ServiceResponseCode serviceResponseCode, String errorMessage) {
		super(errorMessage);
		this.serviceResponseCode = serviceResponseCode;
		this.errorMessage = errorMessage;
	}
	
	public ServiceResponseCode getServiceResponseCode() {
		return serviceResponseCode;
	}
	
	public void setServiceResponseCode(ServiceResponseCode serviceResponseCode) {
		this.serviceResponseCode = serviceResponseCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Throwable getThrowable() {
		return throwable;
	}
	
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}

