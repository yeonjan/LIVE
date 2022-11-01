
let isUseId = false;
document.querySelector("#userid").addEventListener("keyup", function () {
	 let userid = this.value;
	 let resultDiv = document.querySelector("#idcheck-result");
	 if(userid.length < 5 || userid.length > 16) {
	     resultDiv.setAttribute("class", "mb-3 text-dark");
	     resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
	     isUseId = false;
	 }
//	 } else {
//	    // $는jsp꺼..
//	    fetch("${root}/user?act=idcheck&userid=" + userid)
//	        .then(response => response.text())
//	        .then(data =>{
//		            console.log(data);
//		         if(data == 0) { // data는 DB에 현재 아이디가 몇개 있는지 받은거임
//		           resultDiv.setAttribute("class", "mb-3 text-primary");
//		           resultDiv.textContent = userid + "는 사용할 수 있습니다.";
//		           isUseId = true;
//		         } else {
//		           resultDiv.setAttribute("class", "mb-3 text-danger");
//		             resultDiv.textContent = userid + "는 사용할 수 없습니다.";
//		             isUseId = false;
//		         }
//	        });
//	 }
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
	  let form = document.querySelector("#form-join");
	  form.setAttribute("action", "${root}/user"); // get방식
	      form.submit();
	}
});
