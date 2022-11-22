package com.ssafy.live.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ssafy.live.exception.UnAuthorizedException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	// 500 서버 에러
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception ex) {
		log.error("Exception 발생 : {}", ex.getMessage());
		return "에러가 발생했습니다.";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		log.error("404 발생 : {}", "404 page not found");
		return "해당 페이지를 찾을 수 없습니다!!!";
	}
	
	/*
    * Authentication 객체가 필요한 권한을 보유하지 않은 경우 (로그인 안함)
    */
   @ExceptionHandler(UnAuthorizedException.class)
   protected ResponseEntity<String> handleUnAuthorizedException(UnAuthorizedException e) {
       log.error("handleUnAuthorizedException");
       return new ResponseEntity<String>("계정 권한이 유효하지 않습니다.\\n다시 로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
   }
   
	
}
