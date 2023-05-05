package com.yash.blog.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yash.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobleEceptionHendler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse> (apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(field,message);
		});
		
		return new ResponseEntity<Map<String,String>> (errors,HttpStatus.BAD_REQUEST) ;
		
	}
	

}
