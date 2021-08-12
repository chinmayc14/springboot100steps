package com.example.exeptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getLocalizedMessage(), "From HMANV",
				new Date());
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getLocalizedMessage(),
				"From HMANV not allowed", new Date());
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getMessage(), "From HMANV not allowed",
				new Date());
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getLocalizedMessage(),
				"From HMANV not allowed", new Date());
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

}
