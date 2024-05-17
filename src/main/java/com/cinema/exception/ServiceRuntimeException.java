package com.cinema.exception;

import com.cinema.enums.ServiceResponseCode;

public class ServiceRuntimeException extends RuntimeException {
	
	private final ServiceResponseCode serviceResponseCode;
	
	public ServiceRuntimeException(ServiceResponseCode serviceResponseCode, String errorMessage) {
		super(errorMessage);
		this.serviceResponseCode = serviceResponseCode;
	}
	
	public ServiceRuntimeException(ServiceResponseCode serviceResponseCode, String errorMessage, Throwable ex) {
		super(errorMessage, ex);
		this.serviceResponseCode = serviceResponseCode;
	}
	
	public ServiceResponseCode getServiceResponseCode() {
		return serviceResponseCode;
	}
}
