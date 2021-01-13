package com.egas.dealer.exception;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class ExceptionHandlerAPI
{
	 
	@ExceptionHandler(value = {NotFoundException.class })
	public ResponseEntity<Object> handleAnyRequest(NotFoundException ex, WebRequest request)
	{
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), "Error", ex.getLocalizedMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = {InputException.class })
	public ResponseEntity<Object> handleAnyRequest(InputException ex, WebRequest request)
	{
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), "Validation Error", ex.getLocalizedMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationExceptionHandling(MethodArgumentNotValidException exception)
	{
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), "Validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> bodyNotFoundExceptionHandling(HttpMessageNotReadableException exception)
	{
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), "Validation Error", "Please Provide data in Valid json Format for Registration.  ");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	
}


