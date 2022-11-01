package com.ssafy.live.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.mapper.BoardMapper;
import com.ssafy.live.util.PageNavigation;
import com.ssafy.live.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	@Autowired
	private ServletContext servletContext;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public void writeArticle(Board board) throws Exception {
		boardMapper.insertArticle(board);
		System.out.println("글입력 후 dto : " + board);
		List<FileInfo> fileInfos = board.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registerFile(board);
		}

	}

	// 공지사항 목록 조회
	@Override
	public List<Board> getArticleList(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return boardMapper.selectAll(param);
	}

	@Override
	public Board getArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyArticle(Board boardDto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteArticle(int articleNo, String path) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);

		// param에 key-word 넣기
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		String word = map.get("word");
		// DTO는 userId, DB는 user_id라 DB에 맞게 변경
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", word == null ? "" : map.get("word"));

		// 전체 게시글 수
		int totalCount = boardMapper.getTotalArticleCount(param);
		pageNavigation.setTotalArticleCount(totalCount);

		// 전체 페이지 수
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);

		// 이전, 다음 버튼 활성화 여부
		boolean existNextPage = currentPage <= naviSize;
		pageNavigation.setExistNextPage(existNextPage);
		boolean existPrevPage = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setExistPrevPage(existPrevPage);

		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public List<FileInfo> saveFileInfo(MultipartFile[] files) throws IllegalStateException, IOException {
		// 파일 정보
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		for (MultipartFile file : files) {
			// 파일 이름
			String originalFileName = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String saveFileName = File.separator + uuid + "_" + originalFileName;

			// 파일 저장 경로
			String realPath = servletContext.getRealPath("/resources/img");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			
			String savePath = realPath + File.separator + today;
			log.debug(savePath);
			
			File folder = new File(savePath);
			if (!folder.exists())
				folder.mkdirs();

			// 파일 저장 
			file.transferTo(new File(savePath + saveFileName));

			FileInfo fileInfo = new FileInfo();
			fileInfo.setSaveFolder(today);
			fileInfo.setOriginalFileName(originalFileName);
			fileInfo.setSaveFileName(saveFileName);
			fileInfos.add(fileInfo);

		}
		return fileInfos;
	}

}
