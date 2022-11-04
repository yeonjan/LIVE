package com.ssafy.live.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.service.BoardService;
import com.ssafy.live.util.PageNavigation;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
		log.debug("list parameter : {}", map);

		List<Board> list = boardService.getArticleList(map);
		for (Board b : list) {
			log.debug(b.toString());
		}
		PageNavigation pageNavigation = boardService.makePageNavigation(map);

		Map<String, Object> responeMap = new HashMap<String, Object>();
		responeMap.put("articles", list);
		responeMap.put("navigation", pageNavigation);
		responeMap.put("pgno", map.get("pgno"));
		responeMap.put("key", map.get("key"));
		responeMap.put("word", map.get("word"));

		return new ResponseEntity<Map<String, Object>>(responeMap, HttpStatus.OK);

	}

	// 글 쓰기
	@PostMapping("")
	public ResponseEntity<Void> write(@RequestBody MultipartFile[] files, @RequestBody Board board, HttpSession session) throws Exception {
		User loginUser = (User) session.getAttribute("userInfo"); 
		board.setUserId(loginUser.getUserId());

		//log.debug("글 입력 전 dto : {}", board.toString());
		
		// 파일 정보
		if (!files[0].isEmpty()) {
			List<FileInfo> fileInfos = boardService.saveFileInServer(files);
			log.debug(board.toString());
			board.setFileInfos(fileInfos);
		}
		
		boardService.writeArticle(board);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	// 게시글 상세 조회
	@GetMapping("/{articleNo}")
	public ResponseEntity<Board> detail(@PathVariable int articleNo) throws Exception {
		boardService.updateHit(articleNo);
		Board board = boardService.getArticle(articleNo);

		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	// 게시글 삭제
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<Void> delete(@PathVariable int articleNo) throws Exception {
		boardService.deleteFileInServer(articleNo);
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	// 게시글 수정
	@PutMapping("/{articleNo}")
	public ResponseEntity<?> modify(@PathVariable int articleNo,@RequestBody Board board) throws Exception {
		log.debug("수정 board dto : {}", board.toString());
		board.setArticleNo(articleNo);
		boardService.modifyArticle(board);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
