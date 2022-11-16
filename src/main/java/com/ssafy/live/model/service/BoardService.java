package com.ssafy.live.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.util.PageNavigation;

public interface BoardService {

	void writeArticle(Board board) throws Exception;

	List<Board> getArticleList() throws Exception;
	//pagenation ver
	//List<Board> getArticleList(Map<String, String> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	Board getArticle(int articleNo) throws Exception;

	void updateHit(int articleNo) throws Exception;

	void modifyArticle(Board boardDto) throws Exception;

	void deleteArticle(int articleNo) throws Exception;

	List<FileInfo> saveFileInServer(MultipartFile[] files) throws Exception;

	void deleteFileInServer(int articleNo) throws Exception;
	// 관리자 기능
	// 공지사항 리스트 가져오기
	List<Board> getNoticeList(Map<String, String> map) throws Exception;
}
