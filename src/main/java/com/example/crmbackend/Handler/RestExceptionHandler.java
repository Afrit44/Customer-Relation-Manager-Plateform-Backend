package com.example.crmbackend.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.crmbackend.Exception.EntityNotFoundException;
import com.example.crmbackend.Exception.InvalidEntityException;
import com.example.crmbackend.Exception.InvalidOperationException;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	  @ExceptionHandler(EntityNotFoundException.class)
	  public ResponseEntity<ErrorDTO> handleException(EntityNotFoundException exception, WebRequest webRequest) {

	    final HttpStatus notFound = HttpStatus.NOT_FOUND;
	    final ErrorDTO errorDto = ErrorDTO.builder()
	        .code(exception.getErrorCode())
	        .httpCode(notFound.value())
	        .message(exception.getMessage())
	        .build();

	    return new ResponseEntity<>(errorDto, notFound);
	  }

	  @ExceptionHandler(InvalidOperationException.class)
	  public ResponseEntity<ErrorDTO> handleException(InvalidOperationException exception, WebRequest webRequest) {

	    final HttpStatus notFound = HttpStatus.BAD_REQUEST;
	    final ErrorDTO errorDto = ErrorDTO.builder()
	        .code(exception.getErrorCode())
	        .httpCode(notFound.value())
	        .message(exception.getMessage())
	        .build();

	    return new ResponseEntity<>(errorDto, notFound);
	  }

	  @ExceptionHandler(InvalidEntityException.class)
	  public ResponseEntity<ErrorDTO> handleException(InvalidEntityException exception, WebRequest webRequest) {
	    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

	    final ErrorDTO errorDto = ErrorDTO.builder()
	        .code(exception.getErrorCode())
	        .httpCode(badRequest.value())
	        .message(exception.getMessage())
	        .errors(exception.getErrors())
	        .build();

	    return new ResponseEntity<>(errorDto, badRequest);
	  }

	  /*@ExceptionHandler(BadCredentialsException.class)
	  public ResponseEntity<ErrorDTO> handleException(BadCredentialsException exception, WebRequest webRequest) {
	    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

	    final ErrorDTO errorDto = ErrorDTO.builder()
	        .code(ErrorCodes.BAD_CREDENTIALS)
	        .httpCode(badRequest.value())
	        .message(exception.getMessage())
	        .errors(Collections.singletonList("Login et / ou mot de passe incorrecte"))
	        .build();

	    return new ResponseEntity<>(errorDto, badRequest);
	  }*/

}
