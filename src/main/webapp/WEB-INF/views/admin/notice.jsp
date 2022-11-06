<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>

		<%@ include file="../include/head.jsp" %>

	</head>

	<body>
		<header id="header" class="fixed-top header-inner-pages">
			<%@ include file="../include/header.jsp" %>

				<div class="row justify-content-center">
					<div class="mt-5 mb-5"></div>
					<div class="col-lg-10 col-md-10 col-sm-12">
						<div class="row align-self-center mb-2">
							<div class="col-md-2 text-start">
								<button type="button" id="btn-mv-register"
									class="btn btn-outline-primary btn-sm">글쓰기</button>
							</div>		
						</div>
						<table id="board-table" class="table table-hover">
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
							<tbody class="article-list">
							</tbody>
						</table>
					</div>
					<div class="m-3 row" id="navigator"></div>
				</div>
				</div>

				<%@ include file="../include/footer.jsp" %>
	</body>
	<script>
		let isbn;
		let navigation;
		let articles;
		let pgno = 1;
		getBoards();


		//보드 가져오기
		async function getBoards() {
			let result = await fetch(`http://localhost:8080/admin?pgno=${"${pgno}"}`)
			let data = await result.json();

			navigation = data.navigation;
			articles = data.articles;
			pgno = data.pgno;
			key = data.key;
			word = data.word;

			document.getElementById("navigator").innerHTML = navigation.navigator;

			//페이지 네이션
			let pages = document.querySelectorAll(".page-link");
			pages.forEach(function (page) {
				page.addEventListener("click", function () {
					pgno = page.parentNode.getAttribute("data-pg");
					console.log("page 번호:", pgno);
					getBoards();
				});
			});

			let tbody = "";

			for (let i = 0; i < articles.length; i++) {
				tbody += `<tr class="text-center" >
					<td style="display: none">${"${articles[i].articleNo}"}</td>
				<th>${"${(pgno-1)*10+(i+1)}"}</th>
				<td>${"${articles[i].bullet}"}</td>
				<td class="text-start"><a href="#"
					class="article-title link-dark" data-no="${'${articles[i].articleNo}'}"
					style="text-decoration: none"> ${"${articles[i].subject}"} </a></td>
				<td>${"${articles[i].userName+'('+articles[i].userId+')'}"}</td>
				<td>${"${articles[i].hit}"}</td>
				<td>${"${articles[i].registerTime}"}</td>				
			</tr>`
			}

			document.getElementsByClassName("article-list")[0].innerHTML = tbody;
			//글 상세 조회
			let trs = document.querySelectorAll("#board-table tr");
			trs.forEach(tr => {
				tr.addEventListener("click", () => {
					let articleNo = tr.firstElementChild.innerHTML;
					window.location=`http://localhost:8080/boards/view/`+articleNo;
				})
			})

		}

		//글쓰기 버튼
		document.querySelector("#btn-mv-register").addEventListener(
			"click", function () {
				window.location = `http://localhost:8080/go/boards/write`;
			});




	</script>

	</html>