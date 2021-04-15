package com.cg.ima.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ EmployeeNotFoundException.class })
	public final ResponseEntity<Object> handleUserNotFoundException(EmployeeNotFoundException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ OrderNotFoundException.class })
	public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ CategoryNotFoundException.class })
	public final ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND);
	}
	
}
