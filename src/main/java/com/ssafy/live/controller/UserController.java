package com.ssafy.live.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.service.BoardService;
import com.ssafy.live.model.service.FileService;
import com.ssafy.live.model.service.UserService;
import com.ssafy.live.util.JwtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	private final UserService userService;
	private final JwtService jwtService;
	private final BoardService boardService;
	private final FileService fileService;

	@Autowired
	public UserController(UserService userService, JwtService jwtService, BoardService boardService,
			FileService fileService) {
		log.info("UserController 생성자 호출!!!");
		this.jwtService = jwtService;
		this.userService = userService;
		this.boardService = boardService;
		this.fileService = fileService;
	}

	// 회원 비밀번호 찾기
	@PostMapping("/pwd")
	public ResponseEntity<?> delete(@RequestBody User userInfo) throws Exception {
		log.debug(" 회원 비밀번호 찾기 호출 성공 ");
		log.debug(userInfo.toString());
		String pwd = userService.searchPwd(userInfo);
		log.debug(pwd);
		return new ResponseEntity<String>(pwd, HttpStatus.OK);
	}

	// 회원 정보 삭제
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> delete(@PathVariable("userid") String userId) throws Exception {
		log.debug(" 회원 정보 삭제 호출 성공 " + userId);

		List<Integer> articleNoList = boardService.getArticleNoByUserId(userId);
		fileService.deleteFileInServerByUser(articleNoList);
		userService.deleteUser(userId);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

//	// 회원 정보 수정
//	@PutMapping("/{userid}")
//	public ResponseEntity<?> update(@RequestBody User updateUser) throws Exception {
//		log.debug(" 회원 정보 수정 호출 성공 ");
//		log.debug("updateUser : {}", updateUser);
//		userService.updateUser(updateUser);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
	//이메일 수정
	@PutMapping("/{userid}")
	public ResponseEntity<?> updateEmail(@RequestBody User updateUser) throws Exception {
		log.debug(" 이메일 정보 수정 호출 성공 ");
		log.debug("updateUser : {}", updateUser);
		userService.updateUser(updateUser);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 회원 정보 조회
	@GetMapping("/{userId}")
	public ResponseEntity<?> get(@PathVariable String userId) throws Exception {
		log.debug(" 회원 정보 조회 호출 성공 " + userId);
		User user = userService.getUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/id/{userid}")
	public ResponseEntity<?> idCheck(@PathVariable("userid") String userId) throws Exception {
		log.debug("idCheck userid : {}", userId);
		int cnt = userService.idCheck(userId);
		log.debug("" + cnt);
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cnt + "");
		return new ResponseEntity<String>(cnt + "", HttpStatus.OK);
	}

	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user) {
		log.debug("userDto info : {}", user);
		try {
			userService.joinUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return new ResponseEntity<String>("회원 가입 중 문제 발생!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * 로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) throws Exception {

		// 유저 정보 조회
		User userInfo = userService.loginUser(user);

		// 로그인 성공
		if (userInfo != null) {
			String accessToken = jwtService.createAccessToken(userInfo);
			String refreshToken = jwtService.createRefreshToken();
			userService.saveRefreshToken(userInfo.getUserId(), refreshToken);

			Map<String, String> result = new HashMap<String, String>();
			result.put("access-token", accessToken);
			result.put("refresh-token", refreshToken);
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		}
		// 로그인 실패
		else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	/*
	 * 로그아웃
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestParam("userId") String userId) {
		userService.deleteRefreshToken(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*
	 * accessToken 유효성 검증 //200 or 401
	 */
	@GetMapping("/validate/access")
	public ResponseEntity<?> validateAccessToken(HttpServletRequest request) {
		String accessToken = request.getHeader("Authorization");
		log.debug(accessToken);
		if (jwtService.validateToken(accessToken)) {
			log.info("사용 가능한 access토큰");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			log.error("사용 불가능 토큰!!!");
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/validate/refresh")
	public ResponseEntity<?> reIssueRefreshToken(@RequestBody User userInfo, HttpServletRequest request)
			throws Exception {

		String userId = userInfo.getUserId();
		User user = userService.getUser(userId);
		Map<String, Object> resultMap = new HashMap<>();
		String token = request.getHeader("refresh-token");
		log.debug("token : {}, userId : {}", token, user.toString());

		if (jwtService.validateToken(token) && token.equals(userService.getRefreshToken(userId))) {
			String accessToken = jwtService.createAccessToken(user);
			log.debug("token : {}", accessToken);
			log.debug("정상적으로 액세스토큰 재발급");
			resultMap.put("access-token", accessToken);

			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} else {
			log.debug("refresh토큰 유효성 검증 실패 ");
			// userService.deleteRefreshToken(userId);
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

	}
	
	@GetMapping("/mailCheck")
	public ResponseEntity<?> mailCheck(@RequestParam("email") String email){
		log.debug("메일체크"+email);
		String checkNum= userService.joinEmail(email);
		log.debug("인증번호"+checkNum);
		return new ResponseEntity<String>(checkNum,HttpStatus.OK);
		
		
	}

	@GetMapping("/list")
	public String list() {
		return "redirect:/assets/list.html";
	}
}
