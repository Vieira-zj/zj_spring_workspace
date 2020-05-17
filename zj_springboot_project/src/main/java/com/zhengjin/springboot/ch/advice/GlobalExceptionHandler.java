package com.zhengjin.springboot.ch.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = { ExceptionController.class })
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<?> handleAppException(BaseException e, HttpServletRequest request) {
		ErrorReponse representation = new ErrorReponse(e, request.getRequestURI());
		return new ResponseEntity<>(representation, new HttpHeaders(), e.getError().getStatus());
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorReponse> handleResourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		ErrorReponse errorReponse = new ErrorReponse(e, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReponse);
	}

}
