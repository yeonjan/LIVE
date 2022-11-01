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
		<!-- End Breadcrumbs --> <!-- ======= Portfolio Details Section ======= -->
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
												type="password" class="form-control" id="userpwd" name="userpwd"
												required />
											<div class="valid-feedback">Valid.</div>
											<div class="invalid-feedback">필수 항목입니다.</div>
										</div>
										<button type="button" id="btn-login" class="btn btn-secondary">로그인</button>					
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		</main>
		<script>
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