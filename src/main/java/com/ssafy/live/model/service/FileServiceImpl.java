package com.ssafy.live.model.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.FileRequestDto;
import com.ssafy.live.model.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public FileInfo getFileInfo(FileRequestDto fileRequestDto) throws SQLException {
		log.debug("파일 서비스 ");
		return fileMapper.selectFile(fileRequestDto);
	}

	@Override
	public List<String> getFileNames(String articleNo) throws SQLException {
		return fileMapper.selectFileNameByArticleNo(articleNo);
	}
	

	//서버에 파일 저장
	@Override
	public List<FileInfo> saveFileInServer(MultipartFile[] files) throws IllegalStateException, IOException {
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

	//서버에서 파일 삭제
	@Override
	public void deleteFileInServer(int articleNo) throws Exception {

		String realPath = servletContext.getRealPath("/resources/img");

		List<FileInfo> fileInfos = fileMapper.fileInfoList(articleNo);
		for (FileInfo fileInfo : fileInfos) {
			String saveFolder = fileInfo.getSaveFolder();
			String savePath = realPath + File.separator + saveFolder;
			String saveFileName = fileInfo.getSaveFileName();

			log.debug(savePath);
			log.debug(saveFileName);
			File targetFile = new File(savePath, saveFileName);
			targetFile.delete();

		}
	}
	//서버에서 파일 삭제
	@Override
	public void deleteFileInServerByUser(List<Integer> articleNo) throws Exception {

		String realPath = servletContext.getRealPath("/resources/img");

		for(int No : articleNo) {
			deleteFileInServer(No);
		}
		
	}


}
