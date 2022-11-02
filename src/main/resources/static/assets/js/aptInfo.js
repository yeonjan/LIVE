/**
 * 
 */
// /////////////////////// select box 설정 (지역, 매매기간)
// /////////////////////////
let root = "http://localhost:8080"
let date = new Date();

window.onload = function () {
  let yearEl = document.querySelector("#year");
  let yearOpt = `<option value="">매매년도선택</option>`;
  let year = date.getFullYear();
  for (let i = year; i > year - 20; i--) {
    yearOpt += `<option value="${i}">${i}년</option>`;
  }
  yearEl.innerHTML = yearOpt;

  // 브라우저가 열리면 시도정보 얻기.
  sendRequest("sido", "*00000000", "sido");
};

document.querySelector("#year").addEventListener("change", function () {
  let month = date.getMonth() + 1;
  let monthEl = document.querySelector("#month");
  let monthOpt = `<option value="">매매월선택</option>`;
  let yearSel = document.querySelector("#year");
  let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
  for (let i = 1; i < m; i++) {
     monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
  }
  monthEl.innerHTML = monthOpt;
});

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
    sendRequest("gugun", regcode, "gugun");
  } else {
    initOption("gugun");
    initOption("dong");
  }
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
    sendRequest("dong", regcode, "dong");
  } else {
    initOption("dong");
  }
});

function sendRequest(selid, regcode, action) {	
    fetch(`${root}/dongcodes/${action}?regcode=${regcode}`)
	  .then((response) => response.json())
      .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  console.log(data);
  switch (selid) {
    case "sido":
      opt += `<option value="">시도선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `<option value="${regcode.dongCode}">${regcode.sidoName}</option>`;
      });
      break;
    case "gugun":
      opt += `<option value="">구군선택</option>`;
      let name = "";
      data.regcodes.forEach(function (regcode) {
        opt += `<option value="${regcode.dongCode}">${regcode.gugunName}</option>`;
      });
      break;
    case "dong":
      opt += `<option value="">동선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `<option value="${regcode.dongCode}">${regcode.dongName}</option>`;
      });
  }
  document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
  let options = document.querySelector(`#${selid}`);
  options.length = 0;
}

// /////////////////////// 아파트 매매 정보 /////////////////////////
document.querySelector("#list-btn").addEventListener("click", function () {
    console.log("button click");
  let url = "/live/apt";

  let dongSel = document.querySelector("#dong");
  let regCode = dongSel[dongSel.selectedIndex].value;
  console.log(regCode);
  let yearSel = document.querySelector("#year");
  let year = yearSel[yearSel.selectedIndex].value;
  let monthSel = document.querySelector("#month");
  let month = monthSel[monthSel.selectedIndex].value;
  let dealYM = year + month;
  let queryParams = 
      "regcode=" + regCode 
      + "&year=" + year
      + "&month=" + month
      + "&action=apt"; 
 
  fetch(`${url}?${queryParams}`)
    .then((response) => response.json())
    .then((data) => {
        makeList(data);
        registMarker(data);
    });

});

function registMarker(data){

    var positions = [];
    data.regcodes.forEach(function (regcode) {
        var position = {
                title: regcode.apartmentName,
                latlng: new kakao.maps.LatLng(regcode.lat, regcode.lng)
        };
        positions.push(position);
    });

    console.log(positions);

	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	    
	for (var i = 0; i < positions.length; i ++) {
	    
	    // 마커 이미지의 이미지 크기 입니다
	    var imageSize = new kakao.maps.Size(24, 35); 
	    
	    // 마커 이미지를 생성합니다
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
	    
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커를 표시할 위치
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
	        image : markerImage // 마커 이미지
	    });
	}
	
	// 이동할 위도 경도 위치를 생성합니다
	var moveLatLon = positions[0].latlng;
	
	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);    
}

function makeList(data) {
  document.querySelector("table").setAttribute("style", "display: ;");
  let tbody = document.querySelector("#aptlist");
  initTable();
  data.regcodes.forEach(function (regcode) {
    let tr = document.createElement("tr");
    
    let interestTd = document.createElement("td");
    let interestImg = document.createElement("img");
	interestImg.setAttribute("src", "/live/assets/img/icon/star_off.png");
	interestTd.appendChild(interestImg);
	tr.appendChild(interestTd);
	
	interestImg.addEventListener("click", function() {
		const url = "/live/interest";
	    let params = "no=" + regcode.no
	    			 + "&action=register";

		fetch(`${url}?${params}`)
	    .then((response) => response.json())
	    .then((data) => {
	        console.log("성공");
	    });
		
		interestImg.setAttribute("src", "/live/assets/img/icon/star_on.png");
	});
	
	let nameTd = document.createElement("td");
	nameTd.appendChild(document.createTextNode(regcode.apartmentName));
	tr.appendChild(nameTd);
	
	let floorTd = document.createElement("td");
	floorTd.appendChild(document.createTextNode(regcode.floor));
	tr.appendChild(floorTd);
	
	let areaTd = document.createElement("td");
	areaTd.appendChild(document.createTextNode(regcode.area));
	tr.appendChild(areaTd);
	
	let dongTd = document.createElement("td");
	dongTd.appendChild(document.createTextNode(regcode.dong));;
	tr.appendChild(dongTd);
	
	let priceTd = document.createElement("td");
	priceTd.appendChild(document.createTextNode(regcode.dealAmount));
	priceTd.classList.add("text-end");
	tr.appendChild(priceTd);
	
	let noTd = document.createElement("td");
	noTd.appendChild(document.createTextNode(regcode.no));
	noTd.setAttribute("style", "display: none;");
	tr.appendChild(noTd);

	tbody.appendChild(tr);
  });
}

function initTable() {
  let tbody = document.querySelector("#aptlist");
  let len = tbody.rows.length;
  for (let i = len - 1; i >= 0; i--) {
    tbody.deleteRow(i);
  }
}