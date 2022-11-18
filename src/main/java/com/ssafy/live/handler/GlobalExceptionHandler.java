package com.ssafy.live.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ssafy.live.exception.UnAuthorizedException;

import lombok.extern.slf4j.Slf4j;
import java.io.UnsupportedEncodingException;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

     
    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 (로그인 안함)
     */
    @ExceptionHandler(UnAuthorizedException.class)
    protected ResponseEntity<String> handleUnAuthorizedException(UnAuthorizedException e) {
        log.error("handleAccessDeniedException", e);
        return new ResponseEntity<>("계정 권한이 유효하지 않습니다.\\n다시 로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
    }
    
    /**
     * secretKey 변환시 발생 가
     */
    @ExceptionHandler(UnsupportedEncodingException.class)
    protected ResponseEntity<String> UnsupportedEncodingException(UnsupportedEncodingException e) {
        log.error("handleUnsupportedEncodingException", e);

        return new ResponseEntity<>("Making JWT Key Error :::"+e.getMessage(), HttpStatus.UNAUTHORIZED);
    }



}