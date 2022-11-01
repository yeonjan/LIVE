package com.ssafy.live.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private ServletContext servletContext;

	@Autowired
	public BoardController(BoardService boardService) {
		log.info("BoardController 생성자 호출!!!");
		this.boardService = boardService;
	}

	// 글 목록 조회
	@GetMapping("")
	public ResponseEntity<?> getList(@RequestParam Map<String, String> map) throws Exception {
		log.debug("list parameter : {}", map);

		List<Board> list = boardService.getArticle(map);
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

//	@PostMapping("/write")
//	public String write(@Value("{$file.path.upload-files}") String filePath, Board board,
//			@RequestParam("upfile") MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttributes)
//			throws Exception {
//		log.debug("write boardDto : {}", board);
//		User memberDto = (User) session.getAttribute("userinfo");
//		board.setUserId(memberDto.getUserId());
//
////		FileUpload 관련 설정.
//		log.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
//		if (!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
////			String realPath = servletContext.getRealPath("/resources/img");
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = filePath + File.separator + today;
//			log.debug("저장 폴더 : {}", saveFolder);
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			List<FileInfo> fileInfos = new ArrayList<FileInfo>();
//			for (MultipartFile mfile : files) {
//				FileInfo fileInfoDto = new FileInfo();
//				String originalFileName = mfile.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = System.nanoTime()
////							UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					log.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
//					mfile.transferTo(new File(folder, saveFileName));
//				}
//				fileInfos.add(fileInfoDto);
//			}
//			board.setFileInfos(fileInfos);
//		}
//		boardService.writeArticle(board);
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
//		return "redirect:/board/list";
//	}

}
