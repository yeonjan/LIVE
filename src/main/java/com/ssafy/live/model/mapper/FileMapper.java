package com.ssafy.live.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.FileRequestDto;

@Mapper
public interface FileMapper {
	
	FileInfo selectFile(FileRequestDto fileRequestDto) throws SQLException;
	List<String> selectFileNameByArticleNo(String articleNo) throws SQLException;
}
