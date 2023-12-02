<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.findteam.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>팀 만들기</title>
<link rel="stylesheet" href="MyPage.css?after">
</head>
<%
	MyPage_info[] info = (MyPage_info[])request.getAttribute("info");
	int applicant_num=info.length;

%>
<body>


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
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
<div class="text_bar">신청자</div><hr>
<div class="applicants">
<%
	for(int i=0;i<applicant_num;i++){
%>

	<div class="applicant_bar">
	<div class="info">
		제목:&nbsp 
		<%= info[i].getclass_name() %>
		&nbsp 아이디: &nbsp 
		<%= info[i].getapplicant() %>
		&nbsp 전화번호: &nbsp
		<%= info[i].getphonenumber() %>
	</div>
		<form action="" method="get" class="plus_team">
			<input type="submit" name="accept"  value="수락" class="submit_button">
			<input type="submit" name="refuse" value="거절" class="submit_button">
		</form>
	</div>

<%
	}
%>
</div>
</main>

</body>
</html>