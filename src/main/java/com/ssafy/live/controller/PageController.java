package com.ssafy.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	// 회원가입 페이지 이동
	@GetMapping("/users/join")
	public String join() {
		return "user/join";
	}
	// 로그인 페이지 이동
	@GetMapping("/users/login")
	public String login() {
		return "user/login";
	}
	
//	@ExceptionHandler
	
}
