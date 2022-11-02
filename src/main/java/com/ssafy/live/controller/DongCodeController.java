package com.ssafy.live.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.DongCode;
import com.ssafy.live.model.service.DongCodeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dongcodes")
@Slf4j
public class DongCodeController {

	private final DongCodeService dongCodeService;

	@Autowired
	public DongCodeController(DongCodeService dongCodeService) {
		log.info("DongCodeController 생성자 호출!!!");
		this.dongCodeService = dongCodeService;
	}

	// sido Code 가져오기
	@GetMapping("/sido")
	public ResponseEntity<?> sido(@RequestParam String regcode) throws Exception {
		log.debug("sido 정보 메소드 호출");
		List<DongCode> lists = new ArrayList<>();
		lists = dongCodeService.sido();
		Map<String, List<DongCode>> data = new HashMap<>();
		data.put("regcodes", lists);
		
		return new ResponseEntity<Map<String, List<DongCode>>>(data,HttpStatus.OK);
	}

	// gugun Code 가져오기
	@GetMapping("/gugun")
	public ResponseEntity<?> gugn(@RequestParam String regcode) throws Exception {
		log.debug("gugun 정보 메소드 호출");
		List<DongCode> lists = new ArrayList<>(); 
		lists = dongCodeService.gugun(regcode);
		Map<String, List<DongCode>> data = new HashMap<>();
		data.put("regcodes", lists);

		return new ResponseEntity<Map<String, List<DongCode>>>(data,HttpStatus.OK);
	}

	// dong Code 가져오기
	@GetMapping("/dong")
	public ResponseEntity<?> dong(@RequestParam String regcode) throws Exception {
		log.debug("dong 정보 메소드 호출");
		List<DongCode> lists = new ArrayList<>(); 
		lists = dongCodeService.dong(regcode);
		Map<String, List<DongCode>> data = new HashMap<>();
		data.put("regcodes", lists);

		return new ResponseEntity<Map<String, List<DongCode>>>(data,HttpStatus.OK);
	}
}
