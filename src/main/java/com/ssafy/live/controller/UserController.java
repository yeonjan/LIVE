package com.ssafy.live.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		log.info("UserController 생성자 호출!!!");
		this.userService = userService;
	}
	
	// 회원 정보 삭제
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> delete(HttpSession session) throws Exception {
		log.debug(" 회원 정보 삭제 호출 성공 ");
		User user = (User) session.getAttribute("userInfo");
		userService.deleteUser(user.getUserId());
		
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	// 회원 정보 수정
	@PutMapping("/{userid}")
	public ResponseEntity<?> update(@RequestBody User updateUser) throws Exception {
		log.debug(" 회원 정보 수정 호출 성공 ");
		log.debug("updateUser : {}", updateUser);
		userService.updateUser(updateUser);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	// 회원 정보 조회
	@GetMapping("/{userid}")
	public ResponseEntity<?> get(HttpSession session) throws Exception {
		log.debug(" 회원 정보 조회 호출 성공 ");
		User user = (User) session.getAttribute("userInfo");
		log.debug(" userId : {}", user.getUserId());
		user = userService.getUser(user.getUserId());
		return new ResponseEntity<User> (user ,HttpStatus.OK);
	}
	
	@GetMapping("/id/{userid}")
	public ResponseEntity<?> idCheck(@PathVariable("userid") String userId) throws Exception {
		log.debug("idCheck userid : {}", userId);
		int cnt = userService.idCheck(userId);
		log.debug("" + cnt);
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cnt + "");
		return new ResponseEntity<String> (cnt + "",HttpStatus.OK);
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user, Model model) {
		log.debug("userDto info : {}", user);
		try {
			userService.joinUser(user);
			return new ResponseEntity<Void> (HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return new ResponseEntity<Void> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) throws Exception {
		log.debug("map : {}", map.get("userId"));
		User user = userService.loginUser(map);
		log.debug("user : {}", user);
		if(user != null) {
			session.setAttribute("userInfo", user);
			
			Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
			cookie.setPath("/user");
			if("ok".equals(map.get("saveid"))) {
				cookie.setMaxAge(60*60*24*365*40);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return new ResponseEntity<Void> (HttpStatus.OK);
		} else {
			String msg = "아이디 또는 비밀번호 확인 후 다시 로그인하세요!";
			return new ResponseEntity<String> (msg , HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/list")
	public String list() {
		return "redirect:/assets/list.html";
	}
}

