<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="include/head.jsp"%>

</head>
<body>
	<header id="header" class="fixed-top header-inner-pages">
	<%@ include file="include/header.jsp"%>
	
	<main id="main">
		<section id="breadcrumbs" class="breadcrumbs">
			<div class="container">
				<ol>
					<li><a href="index.html">아파트 매매</a></li>
					<li>정보</li>
				</ol>
				<h2>아파트 매매 정보 검색</h2>
			</div>
		</section>
		<!-- End Breadcrumbs -->
	
		<section class="inner-page">
			<!-- 중앙 content start -->
			<div class="container">
				<div style="height: 70px"></div>
	
				<div class="row col-md-12 justify-content-center mb-2">
					<div class="form-group col-md-2">
						<select class="form-select bg-secondary text-light" id="sido">
							<option value="">시도선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-secondary text-light" id="gugun">
							<option value="">구군선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-secondary text-light" id="dong">
							<option value="">동선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-dark text-light" id="year"></select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-dark text-light" id="month">
							<option value="">매매월선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<button type="button" id="list-btn" class="btn btn-outline-primary">
							아파트매매정보가져오기</button>
					</div>
				</div>
				<div class="map_main_container" style="display: none">
					<div style="height: 70px"></div>
					<div class="alert alert-primary mt-3">
						<strong>국토교통부</strong> 지역별 아파트매매 거래 정보
					</div>
					<div class="row col-md-12">
						<div class="form-group ms-2 mb-2 text-end">
							<button type="button" id="btn-list" class="btn btn-outline-danger">
								아파트 정보</button>
						</div>
					</div>
					<div id="map" style="width: 100%; height: 500px"></div>
				</div>
				<table class="table table-hover text-center" style="display: none">
					<tr>
						<th>즐겨찾기</th>
						<th>아파트이름</th>
						<th>층</th>
						<th>면적</th>
						<th>법정동</th>
						<th>거래금액</th>
						<th style="display: none">aptNo</th>
					</tr>
					<tbody id="aptlist"></tbody>
				</table>
			</div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d272d2594ec93c9a9fde3053b9523c4c">
	      	</script>
	      	<script>
		   	  // 카카오 맵
		      let map_main_container = document.querySelector(".map_main_container");
		      map_main_container.setAttribute("style", "display: block;");
	
		      var mapContainer = document.getElementById("map"), // 지도를 표시할
		                                                            // div
		        mapOption = {
		          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의
		                                                                // 중심좌표
		          level: 3, // 지도의 확대 레벨
		        };
		      // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
		      var map = new kakao.maps.Map(mapContainer, mapOption);
	      	</script>
			<script src="${root}/assets/js/aptInfo.js"></script>
		</section>
		<div style="height: 453px"></div>
	</main>
	<!-- End #main -->
	
	<%@ include file="include/footer.jsp"%>
</body>
</html>