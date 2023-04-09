package com.sportify.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sportify.dto.ErrorResponse;
import com.sportify.exceptions.SportifyAppExceptions;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SportifyAppExceptions.class)
	public ResponseEntity<ErrorResponse> sportifyAppExceptionHandler(SportifyAppExceptions ex){
		ErrorResponse err = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> sportifyAppExceptionHandler(Exception ex){
		ErrorResponse err = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}