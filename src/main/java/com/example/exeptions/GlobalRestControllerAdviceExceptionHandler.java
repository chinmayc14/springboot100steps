package com.example.exeptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public CustomErrorDetails handleUserNameNotFoundException(UserNameNotFoundException ex) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getMessage(), "From REST", new Date());
		return customErrorDetails;
	}

}
