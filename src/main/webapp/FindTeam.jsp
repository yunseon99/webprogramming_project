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
	<div class="select_teams">
		<div class="select_team">
			<p class="team_num">1/5</p>
			<p class="class_name">웹프로그래밍(임시)<p/>
			<p>팀 소개</p>
			<div class="print_team_introduction"></div>
			<p>팀원 조건</p>
			<div class="print_team_requirement"></div>
			<form action="" method="post" class="select_team_form">
				<input type="submit" class="select_team_submit" value="">
			</form>
		</div>
		<div class="select_team">
			<p class="team_num">1/5</p>
			<p class="class_name">웹프로그래밍(임시)<p/>
			<p>팀 소개</p>
			<div class="print_team_introduction"></div>
			<p>팀원 조건</p>
			<div class="print_team_requirement"></div>
			<form action="" method="post" class="select_team_form">
				<input type="submit" class="select_team_submit" value="">
			</form>
		</div>
		<div class="select_team">
			<p class="team_num">1/5</p>
			<p class="class_name">웹프로그래밍(임시)<p/>
			<p>팀 소개</p>
			<div class="print_team_introduction"></div>
			<p>팀원 조건</p>
			<div class="print_team_requirement"></div>
			<form action="" method="post" class="select_team_form">
				<input type="submit" class="select_team_submit" value="">
			</form>
		</div>
		<div class="select_team">
			<p class="team_num">1/5</p>
			<p class="class_name">웹프로그래밍(임시)<p/>
			<p>팀 소개</p>
			<div class="print_team_introduction"></div>
			<p>팀원 조건</p>
			<div class="print_team_requirement"></div>
			<form action="" method="post" class="select_team_form">
				<input type="submit" class="select_team_submit" value="">
			</form>
		</div>
	</div>
	<div class="page">
		<div class="previous_page" onclick="">
			&lt 이전페이지	
		</div>
		<div class="slash">
		&nbsp / &nbsp
		</div>
		<div class="next_page" onclick="">
			다음페이지 &gt
		</div>
	</div>
</main>

</body>
</html>