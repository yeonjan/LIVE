package com.ssafy.live.util;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.ssafy.live.model.dto.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public interface JwtService {

	<T> String createAccessToken(User user) throws UnsupportedEncodingException;
	<T> String createRefreshToken() throws UnsupportedEncodingException;
	boolean validateToken(String jwt);
	//Map<String, Object> get(String key);
	//String getUserId();
	
	
}
