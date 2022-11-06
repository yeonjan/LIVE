<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>

		<%@ include file="../include/head.jsp" %>

	</head>

	<body>
		<header id="header" class="fixed-top header-inner-pages">
			<%@ include file="../include/header.jsp" %>

				<main id="main">
					<section id="breadcrumbs" class="breadcrumbs">
						<div class="container">
							<ol>
								<li><a href="index.html">LIVE</a></li>
								<li>정보</li>
							</ol>
							<h2>공지사항</h2>
						</div>
					</section>

					<div class="row justify-content-center">
						<div class="col-lg-8 col-md-10 col-sm-12 mt-2">

						</div>
						<div class="col-lg-8 col-md-10 col-sm-12">
							<div class="row my-2">
								<h2 class="subject text-secondary px-5">1</h2>
							</div>
							<div class="row">
								<div class="col-md-8">
									<div class="clearfix align-content-center">
										<img class="avatar me-2 float-md-start bg-light p-2"
											src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
										<p>
											<span class="userId fw-bold"></span> <br />
											<span class="hit text-secondary fw-light"></span>
										</p>
									</div>
								</div>
								<div class="divider mb-3"></div>
								<div class="content text-secondary"></div>
								<div class="divider mt-3 mb-3"></div>
								<div class="d-flex justify-content-end">
									<button type="button" id="btn-list"
										class="btn btn-outline-primary mb-3">글목록</button>
									<button type="button" id="btn-mv-modify"
										class="btn btn-outline-success mb-3 ms-1">글수정</button>
									<button type="button" id="btn-delete"
										class="btn btn-outline-danger mb-3 ms-1">글삭제</button>

								</div>
							</div>
						</div>
					</div>

					<script>
						let articleNo= "${articleNo}";

						async function getBoardDeatils() {
							let result = await fetch(`http://localhost:8080/boards/` + articleNo);
							let data = await result.json();
							console.log(data);

							document.querySelector(".subject").innerHTML = data.subject;
							document.querySelector(".userId").innerHTML = data.userName + "(" + data.userId + ")";
							document.querySelector(".hit").innerHTML = data.registerTime + "  조회 : " + data.hit;
							document.querySelector(".content").innerHTML = data.content;



						}

						getBoardDeatils();

						//글 목록으로 이동
						document.querySelector("#btn-list").addEventListener("click",
							function () {
								window.location = `http://localhost:8080/go/boards`;

							});

						//게시글 수정 페이지 이동
						document.querySelector("#btn-mv-modify").addEventListener("click",
							function () {
								console.log("수정버튼 누름");
								window.location = `http://localhost:8080/boards/modify/` + articleNo;
							});

						//게시글 삭제
						document.querySelector("#btn-delete").addEventListener(
							"click",
							function () {
								if (confirm("정말 삭제하시겠습니까?")) {
									fetch('http://localhost:8080/boards/' + articelNo, { method: "DELETE" })
										.then(response => {
											if (response.status == 200) {
												window.location = `http://localhost:8080/go/boards`;
											}
										});
								}
							});
					</script>
				</main>
				<%@ include file="../include/footer.jsp" %>
	</body>

	</html>