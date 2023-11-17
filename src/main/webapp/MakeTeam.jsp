<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 만들기</title>
<link rel="stylesheet" href="MakeTeam.css">

</head>
<body>
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
			<button id="contact_us_button" type="button" onclick="location.href='contactus.jsp'">Contact us</button>
		</div>
	</nav>
</header>
<main class="body">
	<form action="" method="post">
	<div class="class_name">
	<p>수업명</p>
	<input type="text" name="class_name_input" id="class_name_input">
	</div>
	<div class="team_num">
	<p>팀 인원</p>
	<select name="team_num_input" id="team_num_input">
		<option value="2">2명</option>
		<option value="3">3명</option>
		<option value="4">4명</option>
		<option value="5">5명</option>
		<option value="6">6명</option>
		<option value="7">7명</option>
		<option value="8">8명</option>
		<option value="9">9명</option>
	</select>
	</div>
	<div class="introduce_team">
		<p>팀 소개</p>
		<textarea name="introduce_team_input" id="introduce_team_input"></textarea>
	</div>
	<div class="team_requirement">
		<p>팀원 조건</p>
		<textarea name="team_requirement _input" id="team_requirement _input"></textarea>
	</div>
		<input type="submit" value="파티 만들기" id="SubmitMakeTeam">
	</form>

</main>

</body>
</html>