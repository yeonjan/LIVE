package com.ssafy.live.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.FileRequestDto;
import com.ssafy.live.model.service.FileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

	private ServletContext servletContext;
	private final FileService fileService;

	public FileController(ServletContext servletContext, FileService fileService) {
		super();
		this.servletContext = servletContext;
		this.fileService = fileService;
	}

	@GetMapping("")
	public ResponseEntity<Resource> download(FileRequestDto fileRequest, HttpServletRequest request)
			throws Exception {
		log.debug("요청 파일 :" + fileRequest.toString());

		String realPath = servletContext.getRealPath("/resources/img");
		// String path = "C:/Users/superpil/OneDrive/바탕 화면/file/tistory.PNG";
		FileInfo fileInfo = fileService.getFileInfo(fileRequest);
		String path = realPath + File.separator + fileInfo.getSaveFolder() + File.separator
				+ fileInfo.getSaveFileName();

		log.debug(path);
		Resource resource = new FileSystemResource(path);

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		String fileName=fileInfo.getOriginalFileName();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8")+ "\"")
				.body(resource);
	}
	
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> getFileName(@PathVariable String articleNo)
			throws Exception {
	
		List<String> names=fileService.getFileNames(articleNo);
		log.debug(names.toString());
		return new ResponseEntity<List<String>>(names,HttpStatus.OK);

	}
	

}
