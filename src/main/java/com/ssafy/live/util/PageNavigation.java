package com.ssafy.live.util;

import lombok.Data;

@Data
public class PageNavigation {

	private boolean existPrevPage; // 이전 페이지 존재 여부
	private boolean existNextPage; // 다음 페이지 존재 여부
	private int totalArticleCount; // 전체 게시글 수
	private int totalPageCount; // 전체 페이지 수 -> 전체 레코드/화면 당 출력 데이터
	private int currentPage; // 현재 페이지
	private int naviSize; // 네비게이션 사이즈
	private int countPerPage; // ???
	private int newCount; // ???

	private String navigator;

	public void makeNavigator() {
		int startPage = (currentPage - 1) / naviSize * naviSize + 1;
		int endPage = startPage + naviSize - 1;
		if (totalPageCount < endPage)
			endPage = totalPageCount;

		StringBuilder buffer = new StringBuilder();
		buffer.append("		<ul class=\"pagination  justify-content-center\"> \n");
		buffer.append("			<li class=\"page-item\" data-pg=\"1\"> \n");
		buffer.append("				<a href=\"#\" class=\"page-link\">최신</a> \n");
		buffer.append("			</li> \n");
		buffer.append("			<li class=\"page-item\" data-pg=\"" + (this.existPrevPage ? 1 : (startPage - 1))
				+ "\"> \n");
		buffer.append("				<a href=\"#\" class=\"page-link\">이전</a> \n");
		buffer.append("			</li> \n");
		for (int i = startPage; i <= endPage; i++) {
			buffer.append("			<li class=\"" + (currentPage == i ? "page-item active" : "page-item")
					+ "\" data-pg=\"" + i + "\"><a href=\"#\" class=\"page-link\">" + i + "</a></li> \n");
		}
		buffer.append("			<li class=\"page-item\" data-pg=\"" + (this.existNextPage ? endPage : (endPage + 1))
				+ "\"> \n");
		buffer.append("				<a href=\"#\" class=\"page-link\">다음</a> \n");
		buffer.append("			</li> \n");
		buffer.append("			<li class=\"page-item\" data-pg=\"" + totalPageCount + "\"> \n");
		buffer.append("				<a href=\"#\" class=\"page-link\">마지막</a> \n");
		buffer.append("			</li> \n");
		buffer.append("		</ul> \n");
		this.navigator = buffer.toString();
	}

}
