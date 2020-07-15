package com.zhengjin.springboot.ch.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = { ExceptionController.class })
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<?> handleAppException(BaseException e, HttpServletRequest request) {
		ErrorResponse representation = new ErrorResponse(e, request.getRequestURI());
		return new ResponseEntity<>(representation, new HttpHeaders(), e.getError().getStatus());
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(e, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}
