package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.FileRequestDto;

public interface FileService {
	FileInfo getFileInfo(FileRequestDto fileRequest) throws Exception;
	List<String> getFileNames(String articleNo) throws Exception;
}
