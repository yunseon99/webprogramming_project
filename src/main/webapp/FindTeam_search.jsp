<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>팀 만들기</title>
<link rel="stylesheet" href="FindTeam.css">
</head>
<body>

<%
	//탐찾기에 들어가면 이 페이지를 거치고 FindTeam.jsp로

%>
<header>
	<nav>
		<div class="logo">
			<a href="main.jsp"><img id="logo" src="./image/logo.png"></a>
		</div>
		<div class="bar">
			<a href="MakeTeam.jsp">파티 만들기</a>
		</div>
		<div class="bar">
			<a href="FindTeam.jsp">파티찾기</a>
		</div>
		<div class="bar">
			<a href="MyPage.jsp">마이페이지</a>
		</div>	
		<div class="bar">
			<button id="contact_us_button" type="button" onclick="location.href='contactus.jsp'">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<div class="search_bar">
		<form action="" method="get" id="search_bar_form">
			<input type="text" name="search_input" id="search_input">
			<input type="submit" id="search_submit"  value="">
		</form>
	</div>
</main>

</body>
</html>