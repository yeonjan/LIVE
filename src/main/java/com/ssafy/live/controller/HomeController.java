package com.ssafy.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
public class HomeController {

	@GetMapping
	public String home() {
		System.out.println("메소드 호출!!!!!!!!!!");
		return "main";
	}
}
 