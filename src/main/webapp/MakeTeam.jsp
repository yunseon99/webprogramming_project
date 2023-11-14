<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀만들기</title>

</head>
<body>
<div class="header">
	<div class="logo">
		<a href="main.jsp"><img id="logo" src="image/logo.PNG"></a>
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
		<button id="contactusb" type="button" onclick="location.href='contactus.jsp'">Contact us</button>
	</div>
</div>
<div class="body">
	<form action="" method="post">
	
	
		<input type="submit" value="파티만들기" id="SubmitMakeTeam">
	</form>

</div>

</body>
</html>