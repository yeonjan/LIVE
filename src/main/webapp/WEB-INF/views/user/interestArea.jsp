<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/head.jsp"%>

<script>
	let root = "http://localhost:8080"
	window.onload = function () {
		fetch(`${root}/apts/interest`)
			.then((response) => response.json())
			.then((data => makeInterestList(data)));
	};

	function makeInterestList(lists) {
		let tbody = ""
		for(list of lists.regcodes) {
			tbody+=`<tr>
				<th>${"${list.name}"}</th>
				<td>${"${list.floor}"}</td>
				<td>${"${list.area}"}</td>
				<td>${"${list.dong}"}</td>
				<td>${"${list.amount}"}</td>
				<td style="display: none">${"${list.no}"}</td>
				<td class="text-center btn-delete-interest">
					<img alt="#" src="/assets/img/icon/delete.png">
				</td>
			</tr>`
		}

		document.getElementById("interest-tbody").innerHTML = tbody;

		let deleteBtn = document.querySelectorAll(".btn-delete-interest"); 
		[].forEach.call(deleteBtn, function(deleteBtn){ 
			deleteBtn.addEventListener("click", function() {
				let config = {
				method: "DELETE",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({aptCode: list.no}),
				};
				
				fetch(`${root}/apts/interest`, config)
				.then((response) => {
					console.log(response);
					if(response.status == "200") {
						console.log("삭제 성공");
						location.href = `${root}/apts/interest`;
					} else {
						console.log("삭제 실패");
					}
				})
			}); 
		});

	};

</script>

</head>
<body>
	<header id="header" class="fixed-top header-inner-pages">
		<%@ include file="../include/header.jsp"%>
		<main id="main"> <!-- ======= Breadcrumbs ======= -->
		<section id="breadcrumbs" class="breadcrumbs">
			<div class="container">
				<ol>
					<li><a href="index.html">Profile</a></li>
					<li>Info</li>
				</ol>
				<h2>Profile</h2>
			</div>
		</section>
		<section id="portfolio-details" class="portfolio-details">
			<div class="container">
				<div class="page_item">
					<div>
						<!--관심지역 등록-->
						<div class="Interest-area-container page_item">
							<div>
								<div class="portfolio-info">
									<h3>관심지역 조회</h3>
									<table class="table table-hover">
										<thead>
											<tr>
												<th>아파트 이름</th>
												<th>층</th>
												<th>면적</th>
												<th>법정동</th>
												<th>거래금액</th>
												<th style="display: none">aptNo</th>
												<th class="text-center">삭제</th>
											</tr>
										</thead>
										<tbody id="interest-tbody">												
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
		</section>
		</main>
		<!-- End Breadcrumbs -->
		<%@ include file="../include/footer.jsp"%>
</body>
</html>