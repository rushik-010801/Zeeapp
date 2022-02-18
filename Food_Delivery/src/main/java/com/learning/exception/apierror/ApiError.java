package com.learning.exception.apierror;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiError {
	//should provide collective info about the errors
	
	private HttpStatus httpStatus;
	
	//Format at which is showed/send to response
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;
	
	private ApiError() {
		timeStamp = LocalDateTime.now(); // TO get the time when method is called
	}
	
	public ApiError(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}
	
	public ApiError(HttpStatus httpStatus, String message, Throwable ex ) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	//Every field validation in suberror
	//we need to create and add into suberror
	
	private void addSubError(ApiSubError apiSubError) {
		if(subErrors == null) {
			subErrors = new ArrayList<>();
		}
		subErrors.add(apiSubError);
	}
	
	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object, field, rejectedValue,  message));
	}
	
	private void addValidationError(String object, String message) {
		addSubError(new ApiValidationError(object, message));
	}
	
	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getObjectName(), fieldError.getField(),fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	}
	
	public void addValidationErrors(List<FieldError> fieldErrors) {
		//fieldErrors.forEach(this::addValidationError);
		fieldErrors.forEach(e->this.addValidationError(e));
	}
	
	private void addValidationError(ObjectError objectError) {
		this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}
	
	public void addValidationError(List<ObjectError> globalErrors) {
		globalErrors.forEach(e->addValidationError(e));
	}
	
	public void addValidationError(ConstraintViolation<?> cv) {
		this.addValidationError(cv.getRootBeanClass().getName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
				cv.getInvalidValue()	
				, cv.getMessage());
	}
	
	public void addValidationsErrors(Set<ConstraintViolation<?>> constrainViolations) {
		constrainViolations.forEach(e->addValidationError(e));
	}
}
