<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>마이 페이지</title>
<link rel="stylesheet" href="MyPage.css?after">
</head>

<body>


<header>
	<nav>
		<div class="logo">
			<a href="main.jsp"><img id="logo" src="./image/logo2.png"></a>
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

	<div class="team">
		<div class="class_name">웹프로그래밍</div>
		<div class="exit">
			<form class="exit_form">
				<input type="hidden" value="팀 고유 id" name="team_id">
				<input type="submit" value="탈퇴" name="exit" class="exit_button">
			</form>	
		</div>
		<br><br>
		<div class="for_flex">
		<div class="team_total_member">
			<div class="team_leader">팀장 : 가나다</div>
			<br>
			<div class="team_members">
				<div class="team_member">팀원 1 : <a href="SeeUserInfo.jsp?name=<%="a"%>&major=<%="a"%>&student_num=<%="a"%>&grade=<%="a"%>&self_introduction=<%="a"%>">000</a> hp : 000-0000-0000</div>
				<div class="team_member">팀원 2 : <a>000</a> hp : 000-0000-0000</div>
				<div class="team_member">팀원 3 : <a>000</a> hp : 000-0000-0000</div>
				<div class="team_member">팀원 4 : <a>000</a> hp : 000-0000-0000</div>
				<div class="team_member">팀원 5 : <a>000</a> hp : 000-0000-0000</div>
				<div class="team_member">팀원 6 : <a>000</a> hp : 000-0000-0000</div>
			</div>
		</div>
		<div class="accept_team">
			<div class="title">신청자</div>
			<br>
			<div class="want_team_boader">
				<div class="want_team">
					<div class="want_id">신청자1 : <a href="SeeUserInfo.jsp?name=<%="a"%>&major=<%="a"%>&student_num=<%="a"%>&grade=<%="a"%>&self_introduction=<%="a"%>">ㅁㅁㅁ</a> hp : 000-0000-0000</div>
					<form class="want_form">
						<input type="hidden" value="신청자id" name="want_join_id">
						<input type="hidden" value="팀 고유 id" name="team_id">
						<input type="submit" value="거절" name="choice" class="choice_button">
						<input type="submit" value="수락" name="choice" class="choice_button">
						
					</form>
				</div>
			
				<div class="want_team">
					<div class="want_id">신청자2 : <a>ㅁㅁㅁ</a> hp : 000-0000-0000</div>
					<form class="want_form">
						<input type="hidden" value="신청자id">
						<input type="hidden" value="팀 고유 id" name="team_id">
						<input type="submit" value="거절" name="choice" class="choice_button">
						<input type="submit" value="수락" name="choice" class="choice_button">
						
					</form>
				</div>
			
				<div class="want_team">
					<div class="want_id">신청자3 : <a>ㅁㅁㅁ</a> hp : 000-0000-0000</div>
					<form class="want_form">
						<input type="hidden" value="신청자id">
						<input type="hidden" value="팀 고유 id" name="team_id">
						<input type="submit" value="거절" name="choice" class="choice_button">
						<input type="submit" value="수락" name="choice" class="choice_button">
						
					</form>
				</div>
			
				<div class="want_team">
					<div class="want_id">신청자4 : <a>ㅁㅁㅁ</a> hp : 000-0000-0000</div>
					<form class="want_form">
						<input type="hidden" value="신청자id">
						<input type="hidden" value="팀 고유 id" name="team_id">
						<input type="submit" value="거절" name="choice" class="choice_button">
						<input type="submit" value="수락" name="choice" class="choice_button">
						
					</form>
				</div>
			</div>
		</div>
		</div>
		<br>
	</div>

</main>

</body>
</html>