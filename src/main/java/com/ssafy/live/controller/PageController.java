package com.ssafy.live.controller;

import javax.servlet.http.HttpSession;

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

	// 로그아웃 기능
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}

	// 회원 정보 확인 페이지 이동
	@GetMapping("/users/confirm")
	public String confirm() {
		return "user/confirm";
	}

	// 회원 정보 수정 페이지 이동
	@GetMapping("/users/update")
	public String update() {
		return "user/update";
	}

	@GetMapping("/go/boards")
	public String goList() {
		return "board/list";
	}
	
	@GetMapping("/go/boards/write")
	public String goBoardWrile() {
		return "board/write";
	}

//	@ExceptionHandler

}
