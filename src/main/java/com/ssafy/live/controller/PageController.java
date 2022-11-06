package com.ssafy.live.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

	// 게시판 페이지 이동
	@GetMapping("/go/boards")
	public String goBoardList() {
		return "board/list";
	}

	// 게시글 쓰기 페이지 이동
	@GetMapping("/go/boards/write")
	public String writeBoard() {
		return "board/write";
	}

	// 게시글 상세조회 페이지 이동
	@GetMapping("/boards/view/{articleNo}")
	public String viewBoard(@PathVariable String articleNo, Model model) {
		model.addAttribute("articleNo", articleNo);
		return "board/view";
	}

	// 게시글 수정 페이지 이동
	@GetMapping("/boards/modify/{articleNo}")
	public String modifyBoard(@PathVariable String articleNo, Model model) {
		log.debug("pageController : modify ");
		model.addAttribute("articleNo", articleNo);
		return "board/modify";

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
	
	// 관리자 공지사항 페이지 이동
	@GetMapping("/admins")
	public String adminPage() {
		return "admin/notice";
	}


//	@ExceptionHandler

}
