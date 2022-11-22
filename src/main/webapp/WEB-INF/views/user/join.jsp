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
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-10 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="orange">회원가입</mark>
				</h2>
			</div>
			<div class="col-lg-10 col-md-10 col-sm-12">
				<form id="form-join" method="POST" action="">
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> <input
							type="text" class="form-control" id="username" name="userName"
							placeholder="이름..." />
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userId"
							placeholder="아이디..." />
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userPwd"
							placeholder="비밀번호..." />
					</div>
					<div class="mb-3">
						<label for="pwdcheck" class="form-label">비밀번호확인 : </label> <input
							type="password" class="form-control" id="pwdcheck"
							placeholder="비밀번호확인..." />
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="emailid"
								name="emailId" placeholder="이메일아이디" /> <span
								class="input-group-text">@</span> <select class="form-select"
								id="emaildomain" name="emailDomain" aria-label="이메일 도메인 선택">
								<option selected>선택</option>
								<option value="ssafy.com">싸피</option>
								<option value="google.com">구글</option>
								<option value="naver.com">네이버</option>
								<option value="kakao.com">카카오</option>
							</select>
						</div>
					</div>
					<div class="col-auto text-center">
						<button type="button" id="btn-join"
							class="btn btn-outline-primary mb-3">회원가입</button>
						<button type="button" class="btn btn-outline-success mb-3">초기화</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script>
      let isUseId = false;
      document.querySelector("#userid").addEventListener("keyup", function () {
    	 let userid = this.value;
    	 let resultDiv = document.querySelector("#idcheck-result");
    	 if(userid.length < 6 || userid.length > 16) {
    		 resultDiv.setAttribute("class", "mb-3 text-dark");
    		 resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
    		 isUseId = false;
    	 } else {
    		 fetch("${root}/users/id/" + userid)
    		   .then(response => response.json())
    		   .then(data => {
    			   console.log(data);
	    			 if(data == 0) {
	    				 console.log(data);
	    			   resultDiv.setAttribute("class", "mb-3 text-primary");
	    		       resultDiv.textContent = userid + "는 사용할 수 있습니다.";
	    		       isUseId = true;
	    			 } else {
	    			   resultDiv.setAttribute("class", "mb-3 text-danger");
	      		       resultDiv.textContent = userid + "는 사용할 수 없습니다.";
	      		     isUseId = false;
	    			 }
    		   });
    	 }
      });
      
      document.querySelector("#btn-join").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else if (!isUseId) {
          alert("아이디 확인!!");
          return;
        } else {
        	let registerinfo = {
   	          userName: document.querySelector("#username").value,
   	          userId: document.querySelector("#userid").value,
   	          userPwd: document.querySelector("#userpwd").value,
   	          emailId: document.querySelector("#emailid").value,
   	          emailDomain: document.querySelector("#emaildomain").value,
   	        };
   	        let config = {
   	          method: "POST",
   	          headers: {
   	            "Content-Type": "application/json",
   	          },
   	          body: JSON.stringify(registerinfo),
   	        };
   	        
   	        fetch("${root}/users/join", config)
   	          .then((response) => {
   	        	  console.log(response)
   	        	  if(response.status == "200") {
   	        		  alert("회원 가입에 성공하셨습니다.");
   	        		  location.href = "${root}/";
   	        	  } else {
   	        		  alert("회원 가입에 실패 하셨습니다.")
   	        	  }
   	      	});
        }
      });
    </script>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>