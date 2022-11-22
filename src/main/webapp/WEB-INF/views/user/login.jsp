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
			<!-- ======= Breadcrumbs ======= -->
			<section id="breadcrumbs" class="breadcrumbs">
				<div class="container">
					<ol>
						<li><a href="index.html">Profile</a></li>
						<li>Info</li>
					</ol>
					<h2>Profile</h2>
				</div>
			</section>
			<!-- End Breadcrumbs -->
			<!-- ======= Portfolio Details Section ======= -->
			<section id="portfolio-details" class="portfolio-details">
				<div class="container">
					<div class="page_item">
						<div>
							<div class="row gy-4">
								<div class="col-lg-8">
									<div class="portfolio-details-slider swiper">
										<div class="swiper-wrapper align-items-center">
											<div class="swiper-slide">
												<img src="${root}/assets/img/apt/p1.jpg" alt="#" />
											</div>

											<div class="swiper-slide">
												<img src="${root}/assets/img/apt/p2.jpg" alt="#" />
											</div>

											<div class="swiper-slide">
												<img src="${root}/assets/img/apt/p3.png" alt="#" />
											</div>
										</div>
										<div class="swiper-pagination"></div>
									</div>
								</div>

								<!--로그인-->
								<div class="col-lg-4">
									<div class="portfolio-info">
										<h3>로그인</h3>
										<form action="" method="post">
											<div class="mb-3 mt-3">
												<label for="userid" class="form-label">아이디:</label> <input
													type="text" class="form-control" id="userid" name="userid"
													required />
												<div class="valid-feedback">Valid.</div>
												<div class="invalid-feedback">필수 항목입니다.</div>
											</div>
											<div class="mb-3">
												<label for="userpwd" class="form-label">비밀번호:</label> <input
													type="password" class="form-control" id="userpwd"
													name="userpwd" required />
												<div class="valid-feedback">Valid.</div>
												<div class="invalid-feedback">필수 항목입니다.</div>
											</div>
											<button type="button" id="btn-login"
												class="btn btn-secondary">로그인</button>
											<button type="button" style="float: right"
												data-bs-toggle="modal" data-bs-target="#search-pwd"
												data-bs-whatever="@mdo" id="btn-Search-pwd"
												class="btn btn-dark">비밀번호 찾기</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- section modal -search password -->
				<div class="modal fade" id="search-pwd" tabindex="-1"
					aria-labelledby="searchModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header" style="background: #f8f9fa">
								<h5 class="modal-btn-pwd" id="modal-btn">비밀번호 찾기</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form>
									<div class="mb-3">
										<label for="search-user-id" class="col-form-label">아이디:</label>
										<input type="text" class="form-control" id="search-user-id" required>
									</div>
									<div class="mb-3">
										<label for="search-user-name" class="form-label">이름 : </label> <input
											type="text" class="form-control" id="search-user-name"
											name="search-user-name" required/>
									</div>
									<label for="search-email-id" class="form-label">이메일 : </label>
									<div class="input-group">
										<input type="text" class="form-control" id="search-email-id"
											name="search-email-id" placeholder="이메일아이디" required/> <span
											class="input-group-text">@</span> <select class="form-select"
											id="search-email-domain" name="search-email-domain" aria-label="이메일 도메인 선택">
											<option selected>선택</option>
											<option value="ssafy.com">싸피</option>
											<option value="google.com">구글</option>
											<option value="naver.com">네이버</option>
											<option value="kakao.com">카카오</option>
										</select>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" id="search-btn-pwd" onclick="searchPwd()" class="btn btn-primary">비밀번호 찾기</button>
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>
			</section>
		</main>
		<script>
		// 모달 js
		let root = "http://localhost:8080"
		let searchModal = document.getElementById('search-pwd');
		searchModal.addEventListener('show.bs.modal', function (event) {
		  let button = event.relatedTarget;
		})
		
		function searchPwd() {
			let userinfo = {
			  userId: document.querySelector("#search-user-id").value,
   	          userName: document.querySelector("#search-user-name").value,
   	          emailId: document.querySelector("#search-email-id").value,
   	          emailDomain: document.querySelector("#search-email-domain").value,
   	        };
			let config = {
			method: "POST",
			headers: {
			"Content-Type": "application/json",
			},
			body: JSON.stringify(userinfo),
			};
			fetch(`${root}/users/pwd`, config)
   	          .then((response) => response.json())
   	          .then((data) => {
   	        	  alert("귀하의 비밀번호는 " + data + " 입니다.");		
				  location.reload();
   	          })
   	          .catch((error) => {
				alert("입력하신 정보와 일치하는 비밀번호가 없습니다.");
				location.reload();
   	          });
		}
		
		document.querySelector("#btn-login").addEventListener("click", function () {
	        if (!document.querySelector("#userid").value) {
	          alert("아이디 입력!!");
	          return;
	        } else if (!document.querySelector("#userpwd").value) {
	          alert("비밀번호 입력!!");
	          return;
	        } else {
	        	let loginInfo = {      	          
       	          userId: document.querySelector("#userid").value,
       	          userPwd: document.querySelector("#userpwd").value,
       	        };
       	        let config = {
       	          method: "POST",
       	          headers: {
       	            "Content-Type": "application/json",
       	          },
       	          body: JSON.stringify(loginInfo),
       	        };
       	        
       	        fetch("${root}/users/login", config)
       	          .then((response) => {
       	        	  console.log(response)
       	        	  if(response.status == "200") {
       	        		  location.href = "${root}/";
       	        	  } else {
       	        		  alert("로그인에 실패 하셨습니다.")
       	        	  }
       	      	});
	        }
	    });
		</script>

		<%@ include file="../include/footer.jsp"%>
</body>
</html>