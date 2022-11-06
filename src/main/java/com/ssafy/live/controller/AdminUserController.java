package com.ssafy.live.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.service.BoardService;
import com.ssafy.live.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Slf4j
public class AdminUserController {
	private BoardService boardService;

	@Autowired
	public AdminUserController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 공지사항 목록 조회
	@GetMapping("")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
		log.debug("list parameter : {}", map);

		List<Board> list = boardService.getNoticeList(map);
		for (Board b : list) {
			log.debug(b.toString());
		}
		PageNavigation pageNavigation = boardService.makePageNavigation(map);

		Map<String, Object> responeMap = new HashMap<String, Object>();
		responeMap.put("articles", list);
		responeMap.put("navigation", pageNavigation);
		responeMap.put("pgno", map.get("pgno"));

		return new ResponseEntity<Map<String, Object>>(responeMap, HttpStatus.OK);
	}
	

//	@GetMapping(value = "/user")
//	public ResponseEntity<?> userList() {
//		log.debug("userList call");
//		try {
//			List<User> list = userService.listUser(null);
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<User>>(list, HttpStatus.OK);
////				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//
//	}
//
//	@PostMapping(value = "/user")
//	public ResponseEntity<?> userRegister(@RequestBody User user) {
//		log.debug("userRegister memberDto : {}", user);
//		try {
//			userService.joinUser(user);
//			List<User> list = userService.listUser(null);
//			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
////			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//
//	}
//
//	@GetMapping(value = "/user/{userid}")
//	public ResponseEntity<?> userInfo(@PathVariable("userid") String userId) {
//		log.debug("userInfo userid : {}", userId);
//		try {
//			User user = userService.getUser(userId);
//			if (user != null)
//				return new ResponseEntity<User>(user, HttpStatus.OK);
//			else
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@PutMapping(value = "/user")
//	public ResponseEntity<?> userModify(@RequestBody User user) {
//		log.debug("userModify memberDto : {}", user);
//		try {
//			userService.updateUser(user);
//			List<User> list = userService.listUser(null);
//			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@DeleteMapping(value = "/user/{userid}")
//	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
//		log.debug("userDelete userid : {}", userId);
//		try {
//			userService.deleteUser(userId);
//			List<User> list = userService.listUser(null);
//			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
