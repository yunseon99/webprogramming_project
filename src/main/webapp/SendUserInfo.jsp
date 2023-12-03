<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="SendUserInfo.css?after">
</head>
<body>
<%
	String user=request.getParameter("user");
	String class_name=request.getParameter("class_name");
%>
<header>
	<nav>
		<div class="logo">
			<a href="main.jsp"><img id="logo" src="image/logo.png"></a>
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
	<p><h2>Request</h2></p>
	<form action="" method="post" class="formcontainer">
		<div class="name">
			<p>이름</p>
			<input type="text" name="name_input" id="name_input">
		</div>
		<div class="major">
			<p>학과</p>
			<input type="text" name="major_input" id="major_input">
		</div>
		<div class="student_num">
			<p>학번</p>
			<input type="text" name="student_num_input" id="student_num_input">
		</div>
		<div class="grade">
			<p>학년</p>
			<input type="text" name="grade_input" id="grade_input">
		</div>

		<div class="self_introduction">
			<p>자기 소개</p>
			<textarea name="self_introduction_input" id="self_introduction_input"></textarea>
		</div>
		<input type="hidden" name="user" value="<%=user%>">
		<input type="hidden" name="class_name" value="<%=class_name%>">
		<input type="submit" value="가입 신청" id="SubmitMakeTeam">
	</form>
</main>

</body>
</html>