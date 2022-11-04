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

					<div class="container mb-5">
						<form id="form-modify">
							<div class="form-group">
								<label for="subject">Title</label>
								<input type="text" name="subject" class="form-control" placeholder="Enter title"
									id="subject" value="">
							</div>
							<br>

							<div class="form-group mb-3">
								<label for="comment">Content:</label>
								<textarea class="form-control" rows="5" id="content" name="content" value=""></textarea>
							</div>
							<button type="submit" class="btn-modify btn btn-secondary"
								style="float: right;">수정하기</button>
						</form>
						<br>
					</div>
					<script>
						//PthVariable 어떻게 받아오는데,,,,,,
						let url = window.location.href.split('/');
						let size = url.length;
						let articelNo = url[size - 1];

						async function getBoardDeatils() {
							let result = await fetch(`http://localhost:8080/boards/` + articelNo);
							let data = await result.json();
							console.log(data);

							document.getElementById("subject").value = data.subject;
							document.getElementById("content").value = data.content;
						}

						getBoardDeatils();

						document.querySelector(".btn-modify").addEventListener("click", (el) => {
							el.preventDefault();

							let boardInfo = {
								subject: document.getElementById("subject").value,
								content: document.getElementById("content").value,
							};
							console.log(boardInfo);
							let config = {
								method: "PUT",
								headers: {
									"Content-Type": "application/json",
								},
								body: JSON.stringify(boardInfo),
							};

							fetch('http://localhost:8080/boards/'+ articelNo, config)
								.then(response => {
									console.log(response.data)
									if (response.status == 200) {
										//window.location = `http://localhost:8080/go/boards`;
									}
								});


						})



					</script>
				</main>
				<%@ include file="../include/footer.jsp" %>
	</body>


	</html>