package com.ssafy.live.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;

@Mapper
public interface BoardMapper {
	
	int insertArticle(Board board) throws SQLException;
	void registerFile(Board board) throws Exception;
	List<Board> selectAll(Map<String, Object> map) throws SQLException;
	int getTotalArticleCount(Map<String, Object> map) throws SQLException;
	Board getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(Board boardDto) throws SQLException;
	void deleteFile(int articleNo) throws Exception;
	void deleteArticle(int articleNo) throws SQLException;
	List<FileInfo> fileInfoList(int articleNo) throws Exception;
	List<Board> noticeSelectAll(Map<String, Object> map) throws SQLException;
}
