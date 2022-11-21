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
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.mapper.BoardMapper;
import com.ssafy.live.model.mapper.FileMapper;
import com.ssafy.live.util.PageNavigation;
import com.ssafy.live.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	private FileMapper fileMapper;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper, FileMapper fileMapper) {
		this.boardMapper = boardMapper;
		this.fileMapper = fileMapper;
	}

	@Override
	public int writeArticle(Board board) throws Exception {
		log.debug("boardService : {}",board.toString());
		
		boardMapper.insertArticle(board);
		
		log.debug("글입력 후 dto : " + board);
		List<FileInfo> fileInfos = board.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registerFile(board);
		}
		
		return board.getArticleNo();

	}

	//게시글 목록 조회
	@Override
	public List<Board> getArticleList(String type) throws Exception {
		if (type.equals("공지사항")) {
			return boardMapper.selectNotice();
		}else {
			return boardMapper.selectCommunity();
		}
	}
	//페이지네이션 ver
//	@Override
//	public List<Board> getArticleList(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "b.user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//
//		return boardMapper.selectAll(param);
//	}

	//게시글 조회
	@Override
	public Board getArticle(int articleNo) throws Exception {
		log.debug("boardService");
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);

	}

	@Override
	public void modifyArticle(Board board) throws Exception {
		boardMapper.modifyArticle(board);

	}

	//게시글 삭제
	@Override
	public void deleteArticle(int articleNo) throws Exception {
		fileMapper.deleteFile(articleNo);
		boardMapper.deleteArticle(articleNo);

	}

	//페이지 네비게이션 생성
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
	public List<Board> getNoticeList() throws Exception {
		return boardMapper.selectNotice();
	}

	@Override
	public List<Integer> getArticleNoByUserId(String userId) throws Exception {
		return boardMapper.selectArticleNo(userId);
	}


}
