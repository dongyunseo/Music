<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-site-verification" content="c7bQdTv6mhb3fOJx_xh6OYmktBlK2FsATf31IF9pk_s" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Responsive Chart</title>
</head>
<meta charset="UTF-8">
<title>Melon Chart</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="../css/Main.css">
<link rel="stylesheet" href="../css/Search_Main.css">
<script src="../script/Ip_Address.js" defer></script>
<script src="../script/TopButton.js" defer></script>
<script src="../script/Top10.js" defer></script>
<script src="../script/Top100.js" defer></script>
<script src="../script/line_chart.js" defer></script>
<script src="../script/Genre_line_chart.js" defer></script>
<body>
	<button onclick="backToTop()" id="btn-back-to-top" title="위로 가기">Top</button>
	<form id="ip_form" action="../Main_Page/Main.do" method="post">
		<input type="hidden" id="ip_address" name="ip_address" value="">
	</form>
<div class="backG">
	<div class="container_but">
		<div class="menu">
			<ul>
				<li>
					<button class="nav-link" id="Melon"><img src="../icon/Melon_Icon.jpg" style="width: 55px;"></button>
				</li>
				<li>
					<button class="nav-link" id="Genie"><img src="../icon/Genie_Icon.png" style="width: 45px; margin-top: 8px"></button>
				</li>
				<li>
					<button class="nav-link" id="Circle"><img src="../icon/Circle_Icon.png" style="width: 38px; margin-top: 11px"></button>
				</li>
				<li>
					<button class="nav-link" id="Youtube"><img src="../icon/Youtube_Icon.png" style="width: 40px; margin-top: 10px"></button>
				</li>
			</ul>
		</div>
	</div>
	<div class="container_Count">
		<div class="Count">
			<ul>
				<li>Today : ${TODAY_VISITORS }</li>
				<li>Yesterday : ${YESTERDAY_VISITORS }</li>
				<li>Week : ${WEEK_VISITORS }</li>
				<li>Month : ${MONTH_VISITORS }</li>
				<li>Total : ${TOTAL_VISITORS }</li>
			</ul>
		</div>
	</div>
	<form id="Search_Ip" method="post" name="search" action="../Main_Page/Search.do" onsubmit="return validateForm()">
			<div class="container_Search">
				<input type="text" placeholder="가수명 또는 제목을 입력하세요" class="search_engine" name="searchQuery" id="search">
				<button type="submit" class="_search">
					<img src="../icon/Search_icon.png" style="width: 20px;"> <i class="fas fa-search"></i>
				</button>
			</div>
	</form>
	<div class="container_G">
			<div class="row">
				<div class="col">
					<canvas id="line-chart" class="line-chart"  height="220px"></canvas>
					<canvas id="line-chart2" class="line-chart2"  height="220px"></canvas>
				</div>
			</div>
	</div>
	<div class="container">
		<h1>Top 10</h1>
		<table>
			<tr>
				<td>
					<div class="image-marquee">
						<div class="image-container marquee-animation" id="imageContainer">
							<!-- 여기에 JavaScript를 사용하여 이미지를 동적으로 추가합니다. -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<div class="container_Top100">
		<h1 class = "New_Chart"></h1>
		<table class='Top100List'>
		
		</table>
	</div>

	<div class="container_Email">	
		문의 : sdy2766@naver.com<br>
		본사 : 충청남도 천안시 서북구 두정동
	</div>
</div>
</body>
</html>

