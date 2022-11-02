<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/head.jsp"%>
<script>
let userid = "${userInfo.userId}";
window.onload = function () {   
	fetch("${root}/users/" + userid)
	 	.then((response) => response.json()) 
		.then((data) => {
			console.log(data.userId);
			document.querySelector("#profile-id").innerText = data.userId;
			document.querySelector("#profile-name").innerText = data.userName;
			document.querySelector("#profile-email").innerText = data.emailId + "@" + data.emailDomain;
			document.querySelector("#profile-joinDate").innerText = data.joinDate;
	});
}    

function updateUser() {
	location.href = "${root}/users/update";
}

function deleteUser() {
	 fetch("${root}/users/"+ userid, {
		 method: "DELETE",
	 }).then((response) => {
	 	  console.log(response);
	 	  if(response.status == "200") {
	 		  alert("회원탈퇴에 성공하셨습니다.");	 	
	 		  location.href = "${root}/users/logout";
	 	  } else {
	 		  alert("회원탈퇴에 실패 하셨습니다.");
	 	  }
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
						<!--회원 정보 확인-->
						<div class="user-profile-container page_item">
							<div>
								<div class="portfolio-info">
									<h3>회원 정보 확인</h3>
									<form action="">							
										<div>
											<strong>아이디</strong> <label for="id-verification"
												id="profile-id" class="profile-item"></label>
										</div>
										<div>
											<strong>이름</strong> <label for="id-verification"
												id="profile-name" class="profile-item"></label>
										</div>
										<div>
											<strong>email</strong> <label for="id-verification"
												id="profile-email" class="profile-item"></label>
										</div>
										<div>
											<strong>가입일</strong> <label for="id-verification"
												id="profile-joinDate" class="profile-item"></label>
										</div>																		
									<button type="button" onclick="updateUser()" class="btn btn-secondary"
										id="btn-move-update" style="margin-right: 10px">
										회원정보 수정</button>
									<button type="button" onclick="deleteUser()" class="btn btn-secondary"
										id="btn-delete">회원탈퇴</button>
									</form>
								</div>
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