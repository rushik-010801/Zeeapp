package com.learning.exception.apierror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
	
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ApiValidationError(String object, String message) {
		// TODO Auto-generated constructor stub
	}
}
