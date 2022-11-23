package com.ssafy.live.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.util.SearchApiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchApiService searchApiService;

	String clientId = "Q8w1vuz6_cHHgCCRv1aK"; // 애플리케이션 클라이언트 아이디
	String clientSecret = "DDtnQYave0"; // 애플리케이션 클라이언트 시크릿

	@GetMapping("")
	public ResponseEntity<?> getNews() {
		String query = null;
		String display = "4";
//		String sort = null;
		try {
			query = URLEncoder.encode("부동산", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + query + "&display=" + display; // JSON
																												// 결과
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // XML 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = SearchApiService.get(apiURL, requestHeaders);
		responseBody=responseBody.replace("<b>", "");
		responseBody=responseBody.replace("<\\/b>", "");
		responseBody=responseBody.replace("&apos;", "`");
		responseBody=responseBody.replace("&gt;", ">");
		responseBody=responseBody.replace("&lt;", "<");
		responseBody=responseBody.replace("&amp;", "&");
		

		return new ResponseEntity<String>(responseBody, HttpStatus.OK);

	}
}
