package com.ssafy.live.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler)
			throws Exception {
		
		log.debug("요청 메소드 종류 :{}",request.getMethod());
		//OPTIONS메소드로 넘어오는 preFlight 요청은 true로 넘겨줌
		if (HttpMethod.OPTIONS.matches(request.getMethod())) return true;
		
		final String token = request.getHeader("access-token");
		
		//토큰 존재여부 체크
		if (token==null) {
			log.debug("헤더에 access-token 정보가 없음");
			response.getWriter().append("not Login");
			return false;
		}
		
		//토큰의 유효성 체크
		try {
			Jws<Claims> claims =Jwts.parser()
					.setSigningKey("ssafy".getBytes("UTF-8"))
					.parseClaimsJws(token);	
			log.debug("token이 존재하고 유효하므로 요청 정상 처리");
			return true;
		} catch (Exception e) {
			log.debug("유효하지 않은 access-token : {}",e.getMessage());
			response.getWriter().append("not login");
			return false;
		}
		
	}
}
