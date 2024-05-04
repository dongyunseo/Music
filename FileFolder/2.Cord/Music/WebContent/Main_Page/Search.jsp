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
<title>Search</title>
</head>
<link rel="stylesheet" href="../css/Main.css">
<link rel="stylesheet" href="../css/Search.css">
<script src="../script/TopButton.js" defer></script>
<script type="text/javascript">
	function navigateToPage(buttonId) {
		// 클릭된 버튼의 id를 URL 매개변수로 추가하여 새로운 페이지로 이동
		window.location.href = "../Main_Page/Main.do?id=" + buttonId;
	}
</script>
<body>
	<button onclick="backToTop()" id="btn-back-to-top" title="위로 가기">Top</button>
	<div class="backG">
		<div class="container_but">
			<div class="menu">
				<ul>
					<li>
						<button class="nav-link" id="Melon"
							onclick="navigateToPage('Melon')">
							<img src="../icon/Melon_Icon.jpg" style="width: 55px;">
						</button>
					</li>
					<li>
						<button class="nav-link" id="Genie"
							onclick="navigateToPage('Genie')">
							<img src="../icon/Genie_Icon.png"
								style="width: 45px; margin-top: 8px">
						</button>
					</li>
					<li>
						<button class="nav-link" id="Circle"
							onclick="navigateToPage('Circle')">
							<img src="../icon/Circle_Icon.png"
								style="width: 38px; margin-top: 11px">
						</button>
					</li>
					<li>
						<button class="nav-link" id="Youtube"
							onclick="navigateToPage('Youtube')">
							<img src="../icon/Youtube_Icon.png"
								style="width: 40px; margin-top: 10px">
						</button>
					</li>
				</ul>
			</div>
		</div>
		<form method="post" name="search" action="../Main_Page/Search.do" onsubmit="return validateForm()">
			<div class="container_Search">
				<input type="text" placeholder="가수명 또는 제목을 입력하세요" class="search_engine" name="searchQuery" id="search">
				<button type="submit" class="_search">
					<img src="../icon/Search_icon.png" style="width: 20px;"> <i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		<div class="Search_List">
			<table>
				<div class="title_Singer_List">제목 및 가수 유사곡</div>
				<tr>
					<th class="Rank">Number</th>
					<th class="img">Img</th>
					<th class="title">title</th>
					<th class="Singer">Singer</th>
				</tr>
				<c:forEach items="${Title_similar}" var="Titlesimilar">
					<tr>
						<td>${Titlesimilar.ranking }</td>
						<td><img src='${Titlesimilar.albumImgUrls }'
							class="album-image"></td>
						<td class="title"><p>${Titlesimilar.title }</p>${Titlesimilar.genreText }</td>
						<td class="Singer">${Titlesimilar.singer }</td>
					</tr>
				</c:forEach>
			</table>
			<table>
				<div class="Genre_List">장르 유사곡</div>
				<tr>
					<th class="Rank">Number</th>
					<th class="img">Img</th>
					<th class="title">title</th>
					<th class="Singer">Singer</th>
				</tr>
				<c:forEach items="${Genre_Text_similar}" var="GenreTextsimilar">
					<tr>
						<td>${GenreTextsimilar.ranking }</td>
						<td><img src='${GenreTextsimilar.albumImgUrls }'
							class="album-image"></td>
						<td class="title"><p>${GenreTextsimilar.title }</p>${GenreTextsimilar.genreText }</td>
						<td class="Singer">${GenreTextsimilar.singer }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>