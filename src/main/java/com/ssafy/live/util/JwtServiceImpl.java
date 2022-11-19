package com.ssafy.live.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.live.exception.UnAuthorizedException;
import com.ssafy.live.model.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

	private static final String SALT = "liveSecretKey";
	private static final int ACCESS_TOKEN_EXPIRE_TIME = 1000*3 ; //test
	//private static final int ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
	private static final int REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 2주
	//private static final int REFRESH_TOKEN_EXPIRE_TIME = 1000 * 6; // test

	@Override
	public <T> String createAccessToken(User user) throws UnsupportedEncodingException {
		String toekn = Jwts.builder()
				// Header : 토큰의 타입, 해쉬 알고리즘
				.setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis()) // 생성 시간
				// Payload : 유효기간(Expiration), 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME)) // 토큰 유효기간
				.setSubject("access-token") // 토큰 제목 설정
				.claim("id", user.getUserId()).claim("name", user.getUserName()).claim("admin", user.getManager())
				// Signature 설정 : secret key를 활용한 암호화.
				.signWith(SignatureAlgorithm.HS256, this.generateKey()).compact(); // 직렬화 처리.
		return toekn;
	}

	@Override
	public <T> String createRefreshToken() throws UnsupportedEncodingException {
		String toekn = Jwts.builder()
				// Header : 토큰의 타입, 해쉬 알고리즘
				.setHeaderParam("typ", "JWT").setHeaderParam("regDate", System.currentTimeMillis()) // 생성 시간
				// Payload : 유효기간(Expiration), 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
				.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME)) // 토큰 유효기간
				.setSubject("refresh-token") // 토큰 제목 설정
				// Signature 설정 : secret key를 활용한 암호화.
				.signWith(SignatureAlgorithm.HS256, this.generateKey()).compact(); // 직렬화 처리.
		return toekn;
	}

	// Signature 설정에 들어갈 secretKey 생성.
	private byte[] generateKey() throws UnsupportedEncodingException {
		return SALT.getBytes("UTF-8");
	}

	// 토큰 유효성 검사
	@Override
	public boolean validateToken(String jwt) {
		log.debug("유효성 검사 "+jwt);
		try {
			// json Web Signature? 서버에서 인증을 근거로 인증정보를 서버의 private key로 서명 한것을 토큰화 한것
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			log.debug("claims: {}", claims);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UnAuthorizedException();
			// return false;
		}
	}

//	@Override
//	public Map<String, Object> get(String key) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//				.getRequest();
//		String jwt = request.getHeader("access-token");
//		Jws<Claims> claims = null;
//		try {
//			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
//		} catch (Exception e) {
////			if (logger.isInfoEnabled()) {
////				e.printStackTrace();
////			} else {
//			log.error(e.getMessage());
////			}
//			throw new UnAuthorizedException();
////			개발환경
////			Map<String,Object> testMap = new HashMap<>();
////			testMap.put("userid", userid);
////			return testMap;
//		}
//		Map<String, Object> value = claims.getBody();
//		log.info("value : {}", value);
//		return value;
//	}

//	@Override
//	public String getUserId() {
//		return (String) this.get("user").get("userid");
//	}

}