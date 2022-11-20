package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.FileRequestDto;
import com.ssafy.live.model.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public FileInfo getFileInfo(FileRequestDto fileRequestDto) throws SQLException {
		log.debug("파일 서비스 ");
		return fileMapper.selectFile(fileRequestDto);
	}

	@Override
	public List<String> getFileNames(String articleNo) throws SQLException {
		return fileMapper.selectFileNameByArticleNo(articleNo);
	}

}
