package com.cg.ima.exception;

import java.util.Date;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
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
	
	@ExceptionHandler({ EmployeeExistsException.class })
	public final ResponseEntity<Object> handleEmployeeExistsException(EmployeeExistsException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Already Exists!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.CONFLICT);
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
	
	@ExceptionHandler({ CategoryExistsException.class })
	public final ResponseEntity<Object> handlecategoryExistsException(CategoryExistsException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Already Exists!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest rq) {
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
        ExceptionResponse expResp = new ExceptionResponse(ex.getMessage(),"Detail Description of the Exception", new Date());
        return new ResponseEntity<Object>(expResp,HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
	
	@ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> handleInvaliInputExceptions(InvalidInputException ex, WebRequest req) {
        ExceptionResponse expResp = new ExceptionResponse("Invalid Input!",ex.getMessage(), new Date());
        return new ResponseEntity<Object>(expResp,HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionResponse expRes = new ExceptionResponse("Invalid Argument!", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expRes, HttpStatus.BAD_REQUEST); // 400
	}
	
}