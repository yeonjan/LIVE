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
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="sky">글보기</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<div class="row my-2">
					<h2 class="text-secondary px-5">${requestScope.detail.articleNo}.
						${requestScope.detail.subject}</h2>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="clearfix align-content-center">
							<img class="avatar me-2 float-md-start bg-light p-2"
								src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
							<p>
								<span class="fw-bold">${requestScope.detail.userId}</span> <br />
								<span class="text-secondary fw-light">
									${requestScope.detail.registerTime} 조회 :
									${requestScope.detail.hit +1}</span>
							</p>
						</div>
					</div>
					<div class="divider mb-3"></div>
					<div class="text-secondary">${requestScope.detail.content}</div>
					<div class="divider mt-3 mb-3"></div>
					<div class="d-flex justify-content-end">
						<button type="button" id="btn-list"
							class="btn btn-outline-primary mb-3">글목록</button>
						<c:if test="${detail.userId eq userId}">
							<button type="button" id="btn-mv-modify"
								class="btn btn-outline-success mb-3 ms-1">글수정</button>
							<button type="button" id="btn-delete"
								class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<form id="form-param" method="get" action="">
			<input type="hidden" id="act" name="action" value="">
		</form>
		<form id="form-no-param" method="get" action="${root}/board">
			<input type="hidden" id="nact" name="action" value=""> <input
				type="hidden" id="articleno" name="articleno"
				value="${requestScope.detail.articleNo}">
		</form>
		<script>
			document.querySelector("#btn-list").addEventListener("click",
					function() {
						let form = document.querySelector("#form-param");
						document.querySelector("#act").value = "list";
						form.setAttribute("action", "${root}/board");
						form.submit();

					});

			document.querySelector("#btn-mv-modify").addEventListener("click",
					function() {
						console.log("수정버튼 누름");
						let form = document.querySelector("#form-no-param");
						document.querySelector("#nact").value = "view_modify";
						form.setAttribute("action", "${root}/board");
						form.submit();
					});

			document
					.querySelector("#btn-delete")
					.addEventListener(
							"click",
							function() {
								if (confirm("정말 삭제하시겠습니까?")) {
									let form = document
											.querySelector("#form-no-param");
									document.querySelector("#nact").value = "delete"; // 이렇게 하면 BoardController에서 action=delete일 때로 넘어간다.
									form
											.setAttribute("action",
													"${root}/board");
									form.submit();
								}
							});
		</script> </main>
		<%@ include file="../include/footer.jsp"%>
</body>
</html>
