<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/head.jsp"%>

</head>
<body>
	<header id="header" class="fixed-top header-inner-pages">
		<%@ include file="../include/header.jsp"%>

		<div class="row justify-content-center">
			<div class="mt-5 mb-5"></div>
			<div class="col-lg-10 col-md-10 col-sm-12">
				<div class="row align-self-center mb-2">
					<div class="col-md-2 text-start">
						<button type="button" id="btn-mv-register"
							class="btn btn-outline-primary btn-sm">글쓰기</button>
					</div>
					<div class="col-md-5 offset-5">
						<form class="d-flex" id="form-search" action="">
							<input type="hidden" name="pgno" value="1" /> <select
								class="form-select form-select-sm ms-5 me-1 w-50" id="skey"
								name="key" aria-label="검색조건">

								<option value="" selected>검색조건</option>
								<option value="subject">제목</option>
								<option value="userid">작성자</option>
							</select>
							<div class="input-group input-group-sm">
								<input type="text" class="form-control" id="sword" name="word"
									placeholder="검색어..." />
								<button id="btn-search" class="btn btn-dark" type="button">검색</button>
							</div>
						</form>
					</div>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center">
							<th scope="col">글번호</th>
							<th scope="col">글머리</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">조회수</th>
							<th scope="col">작성일</th>
						</tr>
					</thead>
					<tbody class ="article-list">
					</tbody>
				</table>
			</div>
			<div class="m-3 row" id = "navigator"></div>
		</div>
		</div>
		<form id="form-param" method="get" action="">
			<input type="hidden" id="pgno" name="pgno" value="${pgno}"> <input
				type="hidden" name="key" value="${key}"> <input
				type="hidden" name="word" value="${word}">
		</form>
		<form id="form-no-param" method="get" action="${root}/board/view">
			<input type="hidden" name="pgno" value="${pgno}"> <input
				type="hidden" name="key" value="${key}"> <input
				type="hidden" name="word" value="${word}"> <input
				type="hidden" id="articleno" name="articleno" value="">
		</form>
		
		<%@ include file="../include/footer.jsp"%>
</body>
<script>
	let isbn;
	let navigation;
	let articles;
	let pgno=1;
	getBoards();


	//보드 가져오기
	async function getBoards(){
		let result = await fetch(`http://localhost:8080/boards?pgno=${"${pgno}"}&key=${"${skey.value}"}&word=${"${sword.value}"}`)
		let data = await result.json();
		
		
		navigation=data.navigation;
		articles = data.articles;
		pgno = data.pgno;
		key = data.key;
		word = data.word;

		document.getElementById("navigator").innerHTML=navigation.navigator;
		
		//페이지 네이션
		let pages = document.querySelectorAll(".page-link");
		pages.forEach(function(page) {
			page.addEventListener("click", function() {
				pgno = page.parentNode.getAttribute("data-pg");
				console.log("page 번호:",pgno);
				getBoards();
			});
		});
	


		let tbody = "";

		for(let i = 0; i < articles.length; i++) {
			tbody+=`<tr class="text-center" >
				<th>${"${(pgno-1)*10+(i+1)}"}</th>
				<td>${"${articles[i].bullet}"}</td>
				<td class="text-start"><a href="#" onclick="goDetailBoard(this)"
					class="article-title link-dark" data-no="${'${articles[i].articleNo}'}"
					style="text-decoration: none"> ${"${articles[i].subject}"} </a></td>
				<td style="display: none">${"${articles[i].isbn}"}</td>
				<td>${"${articles[i].userName+'('+articles[i].userId+')'}"}</td>
				<td>${"${articles[i].hit}"}</td>
				<td>${"${articles[i].registerTime}"}</td>				
			</tr>`
		}
		/* for(article of articles){
			tbody+=`<tr class="text-center" >
							<th>${"${article.articleNo}"}</th>
							<td>${"${article.bullet}"}</td>
							<td class="text-start"><a href="#"
								class="article-title link-dark" data-no="${'${article.articleNo}'}"
								style="text-decoration: none"> ${"${article.subject}"} </a></td>
							<td>${"${article.userName+'('+article.userId+')'}"}</td>
							<td>${"${article.hit}"}</td>
							<td>${"${article.registerTime}"}</td>
						</tr>`
		}
*/
		document.getElementsByClassName("article-list")[0].innerHTML = tbody;
		
	}
	
	// let titles = document.querySelectorAll(".article-title");
	// titles.forEach(title => {
	// 	title.addEventListener("click", () => window.location=`http://localhost:8080/go/boards/write/{isbn}` {
	// });

	function goDetailBoard(el) {

		console.log(el.parentNode.nextElementSibling)

		// location.href = "http://localhost:8080/go/boards/view";
	}

	//글쓰기 버튼
	document.querySelector("#btn-mv-register").addEventListener(
			"click", function() {
				window.location=`http://localhost:8080/go/boards/write`;
			});

	//검색 버튼
	document.querySelector("#btn-search").addEventListener("click",
			function() { 
				console.log(key, word);
				getBoards();
				
			});

	

</script>
</html>