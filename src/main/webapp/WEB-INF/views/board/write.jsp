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
			<div class="col-lg-10 col-md-10 col-sm-12 mb-5">
				<form id="form-register">
					<input type="hidden" name="pgno" value="1"> 
					<input type="hidden" name="key" value=""> 
					<input type="hidden" name="word" value=""> 
					<select
						style="display: inline; width: auto;" name="bullet"
						class="form-select form-select-sm"
						aria-label=".form-select-sm example">
						<option selected>글머리</option>
						<option value="일반">일반</option>
            <option value="공지">공지사항</option>
						<!-- <c:if test="${sessionScope.manager eq 'T'}"> -->
						<!-- </c:if> -->
					</select>
					<div class="mb-3">
						<label for="subject" class="form-label">제목 : </label> <input
							type="text" class="form-control" id="subject" name="subject"
							placeholder="제목..." />
					</div>

					<div class="mb-3">
						<label for="content" class="form-label">내용 : </label>
						<textarea class="form-control" id="content" name="content"
							rows="7"></textarea>
					</div>
					<div class="form-group" align="left">
						<label for="files">파일:</label> <input id="files" type="file"
							class="form-control border" name="files" multiple="multiple">
					</div>
					<div class="col-auto text-center mt-3">
						<button type="button" id="btn-register"
							class="btn btn-outline-primary mb-3">글작성</button>
						<button type="button" id="btn-list"
							class="btn btn-outline-danger mb-3">목록으로이동...</button>
					</div>
				</form>
			</div>
		</div>
		</div>
		<form id="form-param" method="get" action="">
			<input type="hidden" id="pgno" name="pgno" value="${pgno}"> <input
				type="hidden" id="key" name="key" value="${key}"> <input
				type="hidden" id="word" name="word" value="${word}">
		</form>
		<script>
      
    


      document.querySelector("#btn-register").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {

          let form = document.querySelector("#form-register")
          const boardInfo =new FormData(form);
          /*formData.append('subject',document.getElementById("subject").value);
          formData.append('content',document.getElementById("content").value);
          formData.append()*/


      let config = {
        method: "POST",
   	    body: boardInfo,
   	  };
      console.log(boardInfo);
				
      fetch(`http://localhost:8080/boards`,config)
        .then((response)=> {if(response.status==201){
          window.location=`http://localhost:8080/go/boards`;
        }
        })
        }
      });
      
      document.querySelector("#btn-list").addEventListener("click", function () {
    	if(confirm("취소를 하시면 작성한 글은 삭제됩니다.\n취소하시겠습니까?")) {
  		  let form = document.querySelector("#form-param");
        window.location=`http://localhost:8080/go/boards`;
   	    }
      });
    </script>

		<%@ include file="../include/footer.jsp"%>
</body>
</html>