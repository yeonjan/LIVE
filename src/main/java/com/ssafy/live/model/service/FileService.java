package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.FileRequestDto;

public interface FileService {
	FileInfo getFileInfo(FileRequestDto fileRequest) throws Exception;

	List<String> getFileNames(String articleNo) throws Exception;

	List<FileInfo> saveFileInServer(MultipartFile[] files) throws Exception;

	void deleteFileInServer(int articleNo) throws Exception;

	void deleteFileInServerByUser(List<Integer> articleNo) throws Exception;

}
