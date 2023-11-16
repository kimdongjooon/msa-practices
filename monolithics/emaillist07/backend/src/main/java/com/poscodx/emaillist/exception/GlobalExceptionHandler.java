package com.poscodx.emaillist.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.poscodx.emaillist.dto.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JsonResult> handlerException(Exception e) {
		// 1. 로깅(Logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		log.error(errors.toString());
		
		// 2. 응답.	
		JsonResult jsonResult=
				(e instanceof NoHandlerFoundException) ?
						JsonResult.fail("Unknown request"):
						JsonResult.fail(errors.toString());
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(jsonResult);
		
		
	}
}

// .* 는 모든문자라는 뜻. 즉 application/json 양옆의 모든 문자들. 
//if(accept.matches(".*application/json.*")) {
//	
//}
//else {
//	
//}
