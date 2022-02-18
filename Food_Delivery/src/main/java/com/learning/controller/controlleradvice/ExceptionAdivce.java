package com.learning.controller.controlleradvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.exception.apierror.ApiError;

@ControllerAdvice
public class ExceptionAdivce extends ResponseEntityExceptionHandler {
	//this class should be used when any userdefined exception is caused th
	//throughout all the controller
	
	@ExceptionHandler(RecordAlreadyExistsException.class)
	public ResponseEntity<?> recordalreadyRecordExistsException(){
		Map<String, String> map = new HashMap<>();
		map.put("Message" , "This record already exists");
		return ResponseEntity.status(404).body(map);
	}
	
	//Used for unknown exceptions
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> exceptionHandler(Exception e){
//		Map<String, String> map = new HashMap<>();
//		map.put("Message" , "Unknown Exception" + e.getMessage());
//		return ResponseEntity.status(404).body(map);
//	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundException(Exception e){
		Map<String, String> map = new HashMap<>();
		map.put("Message" , "Sorry user with "+e.getMessage()+" not found.");
		return ResponseEntity.status(404).body(map);
	}
	
	//Following function are for writing the customization exception using API errors
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
	
	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
	apiError.setMessage("Validation Error");
	apiError.addValidationErrors(ex.getBindingResult().getFieldErrors()); // fieldwise errors
	apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
	return buildResponseEntity(apiError);
		
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError	apiError){
		// to get which RE object === > if I wnat to make any changes into our existing object then in every return we have to do the change 
		// instead of that if we will use buildRE method ===> EOM.
		return new ResponseEntity<>(apiError,apiError.getHttpStatus());
	}
}
