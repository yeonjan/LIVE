package com.ssafy.live.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.Apt;
import com.ssafy.live.model.dto.Interest;
import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.service.AptService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/apts")
@Slf4j
public class AptController {
	
	private final AptService aptService;
	
	@Autowired
	public AptController(AptService aptService) {
		log.info("UserController 생성자 호출!!!");
		this.aptService = aptService;
	}
	
	@PostMapping
	public ResponseEntity<?> listApt(@RequestBody Map<String, String> map) throws SQLException {
		log.debug("아파트 조회 호출 !!!!!");
		log.debug(map.toString());
		List<Apt> lists = new ArrayList<Apt>();
		lists = aptService.listApt(map);
		Map<String, List<Apt>> data = new HashMap<>();
		data.put("regcodes", lists);
		return new ResponseEntity<Map<String, List<Apt>>> (data, HttpStatus.OK);
	}
	
	@PostMapping("/detail")
	public ResponseEntity<?> listDetailApt(@RequestBody Map<String, String> map) throws SQLException {
		log.debug("아파트 상세조회 호출 !!!!!");
		log.debug(map.toString());
		List<Apt> lists = new ArrayList<Apt>();
		lists = aptService.listDetailApt(map);
		Map<String, List<Apt>> data = new HashMap<>();
		data.put("regcodes", lists);
		return new ResponseEntity<Map<String, List<Apt>>> (data, HttpStatus.OK);
	}

	@PostMapping("/interest")
	public ResponseEntity<?> registerInterest(@RequestBody Map<String, String> map) throws SQLException {
		log.debug("interest 등록 호출 !!!!!");
		String userId = map.get("userId");
		String aptCode = map.get("aptCode");
		log.debug(userId + "  " + aptCode);
		aptService.registerInterest(userId, aptCode);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@GetMapping("/interest")
	public ResponseEntity<?> viewInterest(HttpSession session) throws SQLException {
		log.debug("viewInterest 호출 !!!!!");
		User user = (User) session.getAttribute("userInfo");
		List<Interest> lists = new ArrayList<Interest>();
		lists = aptService.viewInterest(user.getUserId());
		Map<String, List<Interest>> data = new HashMap<>();
		data.put("regcodes", lists);
		return new ResponseEntity<Map<String, List<Interest>>> (data, HttpStatus.OK);
	}
	
	@DeleteMapping("/interest/{aptCode}")
	public String deleteInterest(@PathVariable("aptCode") String aptCode) throws SQLException {
		log.debug("deleteInterest 호출 !!!!!");
		log.debug(aptCode);
		aptService.deleteInterest(aptCode);
		return "관심매물 삭제 성공";
	}
	
	
}
 