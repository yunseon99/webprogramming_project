<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.teamupmodels.beans.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>팀 찾기</title>
<link rel="stylesheet" href="FindTeam.css?after">

</head>
<body>

<%
	if(session.getAttribute("userid")==null){
		%>
		<script>
		alert("로그인을 먼저 해주세요");
 		location.href= "MakeTeam.jsp";
		</script>
		
		<%
	}
%>

<%
	Teambean[] info=(Teambean[])request.getAttribute("info");
	String search_name = (String)request.getAttribute("search_name");//사용자가 검색바에 입력한 강의이름
	int current_page=(int)request.getAttribute("page");
	boolean checkjoin;
	
	if(request.getAttribute("check") != null){
		checkjoin = (boolean)request.getAttribute("check");
		if(!checkjoin){
		%>
		<script>
		alert("신청할 수 없습니다");
		</script>
		<%
		}
	}
%>
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
			<a href="Logout.jsp">로그아웃</a>
		</div>
		<div class="bar">
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<div class="search_bar">
		<form action="" method="get" id="search_bar_form">
			<input type="hidden" name="page" value="0">
			<input type="text" name="search_input" id="search_input" value="<%=search_name %>">
			<input type="submit" id="search_submit"  value="">
		</form>
	</div>
	<div class="select_teams">
	<%
		for(int i=4*current_page;i<4*current_page+4;i++){
			if(i<info.length){
	%>
	
		<div class="select_team">
			<p class="team_num" style="color: #5F5E61; font-family: 'Poppins';"><%=info[i].getCount() %>/<%=info[i].getTotal() %></p>
			<p class="class_name" style="color: #5F5E61; font-family: 'Poppins';"><%=info[i].getClassName() %><p/>
			<p style="color: #5F5E61; font-family: 'Poppins';">팀 소개</p>
			<div class="print_team_introduction"><%=info[i].getIntro() %></div>
			<p style="color: #5F5E61; font-family: 'Poppins';">팀원 조건</p>
			<div class="print_team_requirement"><%=info[i].getReq() %></div>
			<%if(info[i].getCheck()){//check=false면 신청불가%>
			<form action="SendUserInfo.jsp" method="post" class="select_team_form">
				<input type="hidden" value="<%=info[i].getTeamId() %>" name="team_id">
				<input type="hidden" value="<%=current_page%>" name="page">
				<input type="hidden" value="<%=search_name%>" name="search_input">
				<input type="submit" class="select_team_submit" value="">
			</form>
			<%
				}
			else{
			%>
			<div class="select_complete">신청 불가</div>
			<%
			}
			
			%>
			
		</div>	
	<%
			}
		}
	%>
	</div>
	<div class="page">	
	<p>	
		<%
         if(current_page-1>=0){//이전페이지가 있는경우
      %>
      <div class="previous_page" onclick="pagecontrol(-1)">
         &lt 이전페이지
       <form action="" method="post" id="pre">
      	<input type="hidden" value="<%=current_page-1%>" name="page">
      	<input type="hidden" value="<%=search_name%>" name="search_input">
      </form>     
      </div>
      
      <%
         }
         else{//이전페이지가 없는경우
      %>
      <div class="no_previous_page">
         &lt 이전페이지   
      </div>
      <%
         }
      %>
      <div class="slash">
      &nbsp / &nbsp
      </div>
      <%
         if(4*current_page+4<info.length){//다음페이지가 있는경우
      %>
      <div class="next_page" onclick="pagecontrol(1)">
         다음페이지 &gt
       <form action="" method="post" id="next">
      	<input type="hidden" value="<%=current_page+1%>" name="page">
      	<input type="hidden" value="<%=search_name%>" name="search_input">
      </form>     
      </div>
         
      <%
         }
         else{//다음페이지가 없는경우
            
      %>
      <div class="no_next_page">
         다음페이지 &gt
      </div>
      <%
         }
      %>
      </p>
	</div>
	<script>
		function pagecontrol(int a){
				if(a<0){
					 document.getElementById('previous').submit();
				}
				else if(a>0){
					 document.getElementById('next').submit();
					
				}
		}
				
	</script>
		
</main>

</body>
</html>