package com.cinema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class ResponseWrapper<T> implements Serializable{
	
	private static final boolean SUCCESS = true;
	private static final String OK = "OK";
	private final boolean success;
	private final String  message;
	private final List<T> entities;
	
	public ResponseWrapper(boolean success, String message, List<T> entities) {
		this.success = success;
		this.message = message;
		this.entities = entities;
	}
	
//	private ResponseWrapper(int responseCode, String responseMessage, boolean success, List<T> entities) {
//		this.responseCode = responseCode;
//		this.responseMessage = responseMessage;
//		this.success = success;
//		this.entities = entities;
//		this.warnings = Collections.emptyList();
//	}
	
	public static <T> ResponseWrapper<T> success(List<T> entities) {
		return new ResponseWrapper<>(SUCCESS, OK, entities);
	}
	
	public static <T> ResponseWrapper<T> success(T entity) {
		if (entity == null) {
			return success();
		}
		return success(Collections.singletonList(entity));
	}
	
	public static <E> ResponseWrapper<E> failed(String errorMessage) {
		return new ResponseWrapper<>(false, errorMessage, Collections.emptyList());
	}
	
	public static <T> ResponseWrapper<T> success() {
		return success(Collections.emptyList());
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public List<T> getEntities() {
		return entities;
	}
	
	public String getMessage() {
		return message;
	}
}
