package com.stacksimplify.restservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// Commented to disable this and enable controlleradvice
//@RestControllerAdvice
public class GlobalRestControllerAdviceException {
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails userNameNotFound (UserNameNotFoundException ex) {
		return new CustomErrorDetails(new Date(), ex.getMessage(), "From RestController Advice"); 
	}

}
