package com.ssafy.live.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.service.BoardService;
import com.ssafy.live.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardController {

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		log.info("BoardController 생성자 호출!!!");
		this.boardService = boardService;
	}

	// 글 목록 조회
	@GetMapping("")
	public ResponseEntity<?> getList(@RequestParam Map<String, String> map) throws Exception {
		log.debug("list parameter : {}", map);

		List<Board> list = boardService.getArticleList(map);
		for (Board b : list) {
			log.debug(b.toString());
		}
		PageNavigation pageNavigation = boardService.makePageNavigation(map);

		Map<String, Object> responeMap = new HashMap<String, Object>();
		responeMap.put("articles", list);
		responeMap.put("navigation", pageNavigation);
		// responeMap.put("pgno", map.get("pgno"));
		// responeMap.put("key", map.get("key"));
		// responeMap.put("word", map.get("word"));

		return new ResponseEntity<Map<String, Object>>(responeMap, HttpStatus.OK);

	}

	// 글 쓰기
	@PostMapping("")
	public ResponseEntity<Void> write(MultipartFile[] files, Board board, HttpSession session)
			throws Exception {
		log.debug(board.toString());

		// 세션 확인하면 주석 바꾸기
//		User loginUser = (User) session.getAttribute("userInfo"); 
		User loginUser = new User();
		loginUser.setUserId("yjin99");

		board.setUserId(loginUser.getUserId());

		// 파일 정보
		List<FileInfo> fileInfos = boardService.saveFileInfo(files);
		board.setFileInfos(fileInfos);
		boardService.writeArticle(board);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}


}
