package com.ssafy.live.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
		log.debug("로그아웃 페이지 호출 성공!!!!!!!!!");
		session.invalidate();
		return "redirect:/";
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

	// 아파트 매매 정보 페이지 이동
	@GetMapping("/apts")
	public String aptInfo() {
		return "aptInfo";
	}
	
	// 관심 매물 페이지 이동
	@GetMapping("/interests")
	public String interestInfo() {
		return "user/interestArea";
	}

//	@ExceptionHandler

}
