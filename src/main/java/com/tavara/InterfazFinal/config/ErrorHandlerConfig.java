package com.tavara.InterfazFinal.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tavara.InterfazFinal.exceptions.GeneralServiceException;
import com.tavara.InterfazFinal.exceptions.NoDataFoundException;
import com.tavara.InterfazFinal.exceptions.ValidateServiceException;
import com.tavara.InterfazFinal.utils.WrapperResponse;

@Slf4j
@ControllerAdvice

public class ErrorHandlerConfig extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e,WebRequest request){
		log.error(e.getMessage(),e);
		WrapperResponse<?> response = new WrapperResponse<>(false,"Erro en el Servidor",null);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateService(ValidateServiceException e,WebRequest request){
		log.info(e.getMessage(),e);
		WrapperResponse<?> response = new WrapperResponse<>(false,e.getMessage(),null);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noData(NoDataFoundException e,WebRequest request){
		log.info(e.getMessage(),e);
		WrapperResponse<?> response = new WrapperResponse<>(false,e.getMessage(),null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GeneralServiceException.class)
	public ResponseEntity<?> generalService(GeneralServiceException e,WebRequest request){
		log.error(e.getMessage(),e);
		WrapperResponse<?> response = new WrapperResponse<>(false,"Erro en el Servidor",null);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
