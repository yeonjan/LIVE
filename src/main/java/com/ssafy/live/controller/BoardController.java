package com.ssafy.live.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;


import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.service.BoardService;
import com.ssafy.live.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardController {

	private final BoardService boardService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	public BoardController(BoardService boardService) {
		log.info("BoardController 생성자 호출!!!");
		this.boardService = boardService;
	}
	
	
	//글 목록 조회
	@GetMapping("")
	public ResponseEntity<?> getList(@RequestParam Map<String, String> map) throws Exception {
		log.debug("list parameter : {}", map);
		
		List<Board> list = boardService.getArticle(map);
		for(Board b:list) {
			log.debug(b.toString());
		}
		PageNavigation pageNavigation = boardService.makePageNavigation(map);
		
		Map<String, Object> responeMap=new HashMap<String, Object>();
		responeMap.put("articles", list);
		responeMap.put("navigation", pageNavigation);
		//responeMap.put("pgno", map.get("pgno"));
		//responeMap.put("key", map.get("key"));
		//responeMap.put("word", map.get("word"));
		
		
		return new ResponseEntity<Map<String,Object>>(responeMap,HttpStatus.OK);

	}


}
