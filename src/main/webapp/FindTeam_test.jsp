<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<%@ page import="com.findteam.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	
	Team_info[] infoa= new Team_info[6];
	infoa[0] = new Team_info();
	infoa[0].setcheck(true);
	infoa[0].setclass_name("1");
	infoa[0].setcount(0);
	infoa[0].settotal(1);
	infoa[0].setintroduction("1");
	infoa[0].setrequirement("1");
	infoa[0].setuser("1");
	
	infoa[1] = new Team_info();
	infoa[1].setcheck(true);
	infoa[1].setclass_name("2");
	infoa[1].setcount(0);
	infoa[1].settotal(2);
	infoa[1].setintroduction("2");
	infoa[1].setrequirement("2");
	infoa[1].setuser("2");
	
	infoa[2] = new Team_info();
	infoa[2].setcheck(true);
	infoa[2].setclass_name("2");
	infoa[2].setcount(0);
	infoa[2].settotal(2);
	infoa[2].setintroduction("2");
	infoa[2].setrequirement("2");
	infoa[2].setuser("2");
	
	infoa[3] = new Team_info();
	infoa[3].setcheck(true);
	infoa[3].setclass_name("2");
	infoa[3].setcount(0);
	infoa[3].settotal(2);
	infoa[3].setintroduction("2");
	infoa[3].setrequirement("2");
	infoa[3].setuser("2");
	
	infoa[4] = new Team_info();
	infoa[4].setcheck(true);
	infoa[4].setclass_name("2");
	infoa[4].setcount(0);
	infoa[4].settotal(2);
	infoa[4].setintroduction("2");
	infoa[4].setrequirement("2");
	infoa[4].setuser("2");
	
	infoa[5] = new Team_info();
	infoa[5].setcheck(true);
	infoa[5].setclass_name("2");
	infoa[5].setcount(0);
	infoa[5].settotal(2);
	infoa[5].setintroduction("2");
	infoa[5].setrequirement("2");
	infoa[5].setuser("2");
	
	
	
	
	request.setAttribute("page",0);
	request.setAttribute("search_name", "123");
	request.setAttribute("info",infoa);

%>
<jsp:forward page="FindTeam.jsp"></jsp:forward>
</body>
</html>