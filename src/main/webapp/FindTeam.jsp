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
<link rel="stylesheet" href="FindTeam.css?after">
</head>
<body>

<%
	Team_info[] info=(Team_info[])request.getAttribute("info");
	String search_name= (String)request.getAttribute("search_name");//사용자가 검색바에 입력한 강의이름
	int current_page=(int)request.getAttribute("page");
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
			<button id="contact_us_button" type="button" onclick="alert('전화번호 : 000-0000-0000')">Contact us</button>
		</div>
	</nav>
</header>
<main>
	<div class="search_bar">
		<form action="" method="get" id="search_bar_form">
			<input type="text" name="search_input" id="search_input" value="<%=search_name %>">
			<input type="submit" id="search_submit"  value="">
		</form>
	</div>
	<div class="select_teams">
	<%
		for(int i=4*current_page;i<4*current_page+4;i++ ){
			if(i<info.length){
	%>
	
		<div class="select_team">
			<p class="team_num"><%=info[i].getcount() %>/<%=info[i].gettotal() %></p>
			<p class="class_name"><%=info[i].getclass_name() %><p/>
			<p>팀 소개</p>
			<div class="print_team_introduction"><%=info[i].getintroduction() %></div>
			<p>팀원 조건</p>
			<div class="print_team_requirement"><%=info[i].getrequirement() %></div>
			<%if(info[i].getcheck()) {%>
			<form action="" method="post" class="select_team_form">
				<input type="hidden" value="사용자이름">

				<input type="submit" class="select_team_submit" value="" onclick="submitForm(this);">
			</form>
			<script>
				function submitForm(button){
					var phonenumber=prompt("전화번호를 입력하세요","");
					
					if(phonenumber != null && phonenumber!=""){
						var form = button.closest('form');
						var hiddeninput=document.createElement("input");
						hiddeninput.type="hidden";
						hiddeninput.name="phonenumber";
						hiddeninput.value=phonenumber;
						
						form.appendChild(hiddeninput);
						form.submit();
					}

				}
				
			</script>
			<%
				}
			else{
			%>
			<div class="select_complete">신청 완료</div>
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
		<%
         if(current_page-1>=0){//이전페이지가 있는경우
      %>
      <div class="previous_page" onclick="pagecontrol(-1)">
         &lt 이전페이지   
      </div>
      <form action="" method="post" id="pre">
      	<input type="hidden" value="<%=current_page-1%>">
      	<input type="hidden" value="<%=search_name%>">
      </form>
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
      </div>
      <form action="" method="post" id="next">
      	<input type="hidden" value="<%=current_page+1%>">
      	<input type="hidden" value="<%=search_name%>">
      </form>      
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